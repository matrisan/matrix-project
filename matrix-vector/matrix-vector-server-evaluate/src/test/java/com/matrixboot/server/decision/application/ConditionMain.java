package com.matrixboot.server.decision.application;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * create in 2021/9/28 12:02 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */


public class ConditionMain {
    private static Lock lock = new ReentrantLock();
    private static Condition take = lock.newCondition();

    int value = 0;

    public static void main(String[] args) {


    }

    public void put() {

    }


    public void take() {
        lock.lock();


    }


}
