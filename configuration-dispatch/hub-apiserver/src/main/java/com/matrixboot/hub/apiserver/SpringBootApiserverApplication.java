package com.matrixboot.hub.apiserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <p>
 * create in 2021/9/14 2:05 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringBootApiserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApiserverApplication.class, args);
    }

}
