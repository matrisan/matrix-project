package com.matrixboot.hub.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <p>
 * create in 2021/9/14 2:05 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@EnableAsync
@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringBootManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootManagerApplication.class, args);
    }

}
