package com.matrixboot.server.evaluate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <p>
 * create in 2021/9/17 10:51 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */
@EnableAsync
@SpringBootApplication
public class SpringBootEvaluateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEvaluateApplication.class, args);
    }
}
