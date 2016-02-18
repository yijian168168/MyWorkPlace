package com.executor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * ExecutorService、Callable、Future这个对象实际上都是属于Executor框架中的功能类。
 * 想要详细了解Executor框架的可以访问http://www.javaeye.com/topic/366591 ，这里面对该框架做了很详细的解释。
 * 返回结果的线程是在JDK1.5中引入的新特征，确实很实用，
 * 有了这种特征我就不需要再为了得到返回值而大费周折了，而且即便实现了也可能漏洞百出。
 * 可返回值的任务必须实现Callable接口，类似的，无返回值的任务必须Runnable接口。
 * 执行Callable任务后，可以获取一个Future的对象，在该对象上调用get就可以获取到Callable任务返回的Object了
 * ，再结合线程池接口ExecutorService就可以实现传说中有返回结果的多线程了。
 * 下面提供了一个完整的有返回结果的多线程测试例子，在JDK1.5下验证过没问题可以直接使用。
 * Created by Administrator on 2016/2/18 0018.
 */
public class ExecutorTest {

    public static void main(String[] args) {
        System.out.println("----程序开始运行----");
        Date date1 = new Date();

        int taskSize = 5;//线程池大小
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        /**
         * 上述代码中Executors类，提供了一系列工厂方法用于创先线程池，返回的线程池都实现了ExecutorService接口。
         *一、public static ExecutorService newFixedThreadPool(int nThreads)
         *      创建固定数目线程的线程池。
         *二、 public static ExecutorService newCachedThreadPool()
         *      创建一个可缓存的线程池，调用execute 将重用以前构造的线程（如果线程可用）。如果现有线程没有可用的，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程。
         *三、public static ExecutorService newSingleThreadExecutor()
         *      创建一个单线程化的Executor。
         *四、public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize)
         *      创建一个支持定时及周期性的任务执行的线程池，多数情况下可用来替代Timer类。
         * */
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < 10; i++) {
            Callable c = new MyCallable(i + " ");
            // 执行任务并获取Future对象
            Future f = pool.submit(c);
            // System.out.println(">>>" + f.get().toString());
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();

        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            //ExecutoreService提供了submit()方法，传递一个Callable，或Runnable，返回Future。
            //如果Executor后台线程池还没有完成Callable的计算，
            //这调用返回Future对象的get()方法，会阻塞直到计算完成。
            try {
                System.out.println(">>>" + f.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【" + (date2.getTime() - date1.getTime()) + "毫秒】");
    }
}
