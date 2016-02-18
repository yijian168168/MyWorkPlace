package com.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2016/2/18 0018.
 */
public class MyLocker {

    private int i;

    // new ReentrantLock(true)是重载，使用更加公平的加锁机制，
    // 在锁被释放后，会优先给等待时间最长的线程，避免一些线程长期无法获得锁
    private ReentrantLock lock = new ReentrantLock();

    public void addI(){
        lock.lock();
        try {
            i++;
        }catch (Exception e){
            lock.unlock();
        }
    }
}
