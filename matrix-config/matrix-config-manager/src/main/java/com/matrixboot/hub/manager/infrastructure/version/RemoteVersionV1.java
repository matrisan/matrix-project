package com.matrixboot.hub.manager.infrastructure.version;

import com.matrixboot.hub.manager.domain.entity.ConfigEntity;
import com.matrixboot.redis.annotation.RedisStreamClient;
import com.matrixboot.redis.annotation.RedisStreamEndpoint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 * create in 2021/9/22 1:36 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Component("RemoteVersionV1")
public class RemoteVersionV1 implements IRemoteVersion<VersionV1> {

    @Resource
    private IRedisStreamInput redisStreamInput;

    @Override
    public VersionV1 convertor(@NotNull ConfigEntity entity) {
        return VersionV1.builder()
                .domain(entity.getDomain())
                .source(entity.getSource())
                .status(entity.getStatus())
                .build();
    }

    @Override
    public void doTransfer(BaseVersion data) {
        redisStreamInput.stream(data);
    }


    @RedisStreamClient("client")
    interface IRedisStreamInput {

        /**
         * 推送数据
         *
         * @param versioned 版本
         */
        @RedisStreamEndpoint("test_key")
        void stream(BaseVersion versioned);

    }
}


@Slf4j
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class VersionV1 implements BaseVersion {

    String domain;

    String source;

    Integer status;
}

