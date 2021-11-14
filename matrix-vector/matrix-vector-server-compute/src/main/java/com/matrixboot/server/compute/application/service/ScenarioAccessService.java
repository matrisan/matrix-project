package com.matrixboot.server.compute.application.service;

import com.matrixboot.compute.ScenarioComputeCommand;
import com.matrixboot.server.compute.application.ComputeEntityFactory;
import com.matrixboot.server.compute.domain.entity.ComputeEntity;
import com.matrixboot.server.compute.domain.entity.ScenarioComputeEntity;
import com.matrixboot.server.compute.domain.repository.IScenarioComputeRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * <p>
 * create in 2021/11/9 11:20 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Service
@AllArgsConstructor
public class ScenarioAccessService {

    private final IScenarioComputeRepository repository;

    private final RedisTemplate<String, Integer> redisTemplate;

    public Map<String, Integer> getAllValue(ScenarioComputeCommand command) {
        ComputeEntity computeEntity = ComputeEntityFactory.from(command);
        List<ScenarioComputeEntity> computeEntityList = repository.findAll(computeEntity);
        List<ComputeEntity> collect = computeEntityList.stream().map(compute -> compute.mapKey(command)).collect(Collectors.toList());
        return getCounts(collect);
    }

    private Map<String, Integer> getCounts(List<ComputeEntity> collect) {
        List<Object> objectList = redisTemplate.executePipelined(getCallback(collect));
        List<Integer> list = objectList.stream().map(Integer.class::cast).collect(Collectors.toList());
        return zipCountResult(collect, list);
    }

    @NotNull
    private RedisCallback<Integer> getCallback(List<ComputeEntity> collect) {
        return connection -> {
            collect.forEach(compute -> collect.forEach(one -> ((StringRedisConnection) connection).evalSha(one.getScriptSha(), ReturnType.INTEGER, 1, one.getArgs())));
            return null;
        };
    }

    private Map<String, Integer> zipCountResult(List<ComputeEntity> collect, List<Integer> list) {
        Map<String, Integer> map = new HashMap<>(16);
        AtomicInteger index = new AtomicInteger(0);
        collect.forEach(computeEntity -> map.put(computeEntity.getCode(), list.get(index.getAndIncrement())));
        return map;
    }

}
