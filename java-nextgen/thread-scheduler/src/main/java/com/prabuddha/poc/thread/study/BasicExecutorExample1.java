package com.prabuddha.poc.thread.study;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicExecutorExample1 {

    public static void main(String[] args) {

        // Creation of the thread Pool
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        ExecutorService multiThreadPool= Executors.newFixedThreadPool(8);

        // Task that needs to be performed
        Runnable myTask = () -> {
            System.out.println("Hello World"+Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is finished Execution Successfully.");
        };

        // We need to pass the task to the thread pool instead of creating a new Thread Object
        //singleThreadPool.execute(myTask);
        //singleThreadPool.execute(myTask);

        multiThreadPool.execute(myTask);
        multiThreadPool.execute(myTask);
        multiThreadPool.execute(myTask);
        multiThreadPool.execute(myTask);

        singleThreadPool.shutdown();
        multiThreadPool.shutdown();
    }

}
