package com.prabuddha.poc.thread.Project_1;


/*
 This is a sample project that demonstrate advanced java concurrency.
 *   Step 1: Split a List into several sublists in order to process each sublist element in parallel using
 *           Guava library that can partition the List.
 *    Step 2: Process each partitioned sublist applying multithreading mechanism using Java ExecutorService.
 *    Step 3: Once the process is complete, drop the result into a Blocking queue.
 *    Step 4: There is a consumer application that will read the result from the blocking queue.
 */

import com.google.common.collect.Lists;
import com.prabuddha.poc.thread.Project_1.model.Message;
import com.prabuddha.poc.thread.Project_1.processor.DataConsumer;
import com.prabuddha.poc.thread.Project_1.processor.DataProcessor;
import com.prabuddha.poc.thread.Project_1.util.AppUtil;

import java.util.*;
import java.util.concurrent.*;

public class AppStarter {
    public static void main(String[] args) throws Exception {
        AppStarter object=new AppStarter();
        object.execute();
    }

    private void execute() throws Exception {

//        TimerTask repeatedTask = new TimerTask() {
//            public void run() {
//
//            }
//        };
//
//        Timer timer = new Timer("Timer");
//        long delay  = 1000L;
//        long period = 1000L;
//        timer.scheduleAtFixedRate(repeatedTask, delay, period);

        List<String> inputLineList = AppUtil.addEachLineFromFileToList("C:\\A_Prabuddha_Study\\Workspace\\NextGenKB\\java-nextgen\\thread-scheduler\\src\\main\\resources\\Dataset.csv");
        List<List<String>> subInputLineList = Lists.partition(inputLineList, 25);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<String>> resultList = new ArrayList<Future<String>>();


        //Below blocking queue can contain at most 10 message
        BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(10);
        Thread consumer=new Thread(new DataConsumer(queue));
        consumer.start();

        //We need a count down latch so that main thread will be blocked before line number 53
        // in order to complete the data processing before checking the thread completion status using
        // future.isDone()
        CountDownLatch countDownLatch = new CountDownLatch(4);

        subInputLineList.parallelStream().forEach(
                item -> {
                    Callable<String> callable = new DataProcessor(item,countDownLatch,queue);
                    //submit Callable tasks to be executed by thread pool
                    Future<String> future = executor.submit(callable);
                    //add Future to the list, we can get return value using Future
                    resultList.add(future);
                }
        );

        // The main task waits for four threads
        countDownLatch.await();

        resultList.forEach(future->{
            if(!future.isDone()){
                System.out.println("Error Occurred while Processing ..........");
            }else {
                try {
                    System.out.println(future.get() +" is completed successfully ..........");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        //shut down the executor service now
        executor.shutdown();
    }

}
