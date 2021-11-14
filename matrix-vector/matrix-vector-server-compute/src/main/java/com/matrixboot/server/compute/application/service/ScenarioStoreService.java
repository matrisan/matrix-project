package com.matrixboot.server.compute.application.service;

import com.matrixboot.compute.ScenarioComputeCommand;
import com.matrixboot.server.compute.domain.repository.IScenarioComputeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * create in 2021/11/9 11:20 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Service
@AllArgsConstructor
public class ScenarioStoreService {

    private final IScenarioComputeRepository repository;

    public void save(ScenarioComputeCommand command) {
        repository.findAll(command);


    }

}
