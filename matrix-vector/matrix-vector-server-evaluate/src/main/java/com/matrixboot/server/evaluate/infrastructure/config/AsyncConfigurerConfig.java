package com.matrixboot.server.evaluate.infrastructure.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 线程池的配置类
 * <p>
 * create in 2022/1/21 5:07 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Configuration
public class AsyncConfigurerConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.task.execution")
    public TaskExecutionProperties properties() {
        return new TaskExecutionProperties();
    }

    @Bean
    @ConfigurationProperties("spring.task.execution.vector")
    public TaskExecutionProperties properties1() {
        return new TaskExecutionProperties();
    }

    @Bean
    @ConfigurationProperties("spring.task.execution.kafka")
    public TaskExecutionProperties properties2() {
        return new TaskExecutionProperties();
    }

    @Bean("VectorThreadPool")
    public AsyncConfigurer asyncConfigurer1() {
        return getAsyncConfigurer(properties1());
    }

    @Bean("KafkaThreadPool")
    public AsyncConfigurer asyncConfigurer2() {
        return getAsyncConfigurer(properties2());
    }

    @NotNull
    private AsyncConfigurer getAsyncConfigurer(TaskExecutionProperties properties) {
        return new AsyncConfigurer() {
            @Override
            public Executor getAsyncExecutor() {
                ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
                taskExecutor.setCorePoolSize(properties.getPool().getCoreSize());
                taskExecutor.setMaxPoolSize(properties.getPool().getMaxSize());
                taskExecutor.setQueueCapacity(properties.getPool().getQueueCapacity());
                taskExecutor.initialize();
                return taskExecutor;
            }
        };
    }
}
