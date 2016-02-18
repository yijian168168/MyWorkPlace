package com.executor;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * 实现了 Callable 借口的线程可以有返回结果
 *
 * Created by Administrator on 2016/2/18 0018.
 */
public class MyCallable implements Callable<Object> {

    private String taskNum;

    MyCallable(String taskNum) {
        this.taskNum = taskNum;
    }
    public Object call() throws Exception {
        System.out.println(">>>" + taskNum + "任务启动");
        Date dateTmp1 = new Date();
        Thread.sleep(1000);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(">>>" + taskNum + "任务终止");
        return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
    }
}
