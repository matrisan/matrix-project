package com.matrixboot.server.decision.application;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * create in 2021/9/27 11:25 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */


public class CountDownMain {

    static CountDownLatch count = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService eService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            eService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "  before");
                int anInt = new Random().nextInt(10);

                count.countDown();
                try {
                    TimeUnit.SECONDS.sleep(anInt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "  after " + anInt);
            });
        }
        count.await();
        eService.shutdown();
        System.out.println("count.await");
    }
}
