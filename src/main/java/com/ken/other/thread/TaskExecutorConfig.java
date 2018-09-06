package com.ken.other.thread;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Spring通过任务执行器（TaskExecutor）来实现多线程和并发编程。
 * 使用ThreadPoolTaskExecutor可实现一个基于线程池的TaskExecutor。
 * 在配置类中通过@EnableAsync开启对异步任务的支持，并通过在实际执行的
 * Bean的方法中使用@Async注解来声明其是一个异步任务。
 */
@Configuration
@EnableAsync
public class TaskExecutorConfig implements AsyncConfigurer {

    /**
     * 重写getAsyncExecutor方法，并返回ThreadPoolTaskExecutor,
     * 这样我们就获得了一个基于线程池的TaskExecutor
     *
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.initialize();
        return taskExecutor;
    }


}
