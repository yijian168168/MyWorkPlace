package com.thread;

/**
 * Created by Administrator on 2016/2/18 0018.
 */
public class ThreadTest {
    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        myThread1.start();
        myThread2.start();
    }
}
