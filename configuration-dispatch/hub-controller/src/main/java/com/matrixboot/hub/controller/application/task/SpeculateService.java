package com.matrixboot.hub.controller.application.task;

import com.matrixboot.hub.controller.domain.repository.ISpeculateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * create in 2021/9/17 9:45 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Service
public class SpeculateService {

    @Resource
    private ISpeculateRepository repository;


}
