package com.prabuddha.poc.thread.study;

import java.util.concurrent.*;

public class BasicExecutorExample2 {

    public static void main(String[] args) {

        // Creation of the thread Pool
        ExecutorService multiThreadPool= Executors.newFixedThreadPool(2);

        // Task that needs to be performed
        Callable<String> myTask1 = () -> {
            System.out.println("Hello World"+Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is finished Execution Successfully.");

            return Thread.currentThread().getName()+" >> SUCCESS";
        };

        // Task that needs to be performed
        Callable<String> myTask2 = () -> {
            throw new Exception(Thread.currentThread().getName()+" >> Custom Exception");
        };

        // We need to pass the task to the thread pool instead of creating a new Thread Object
        Future<String> future1 = multiThreadPool.submit(myTask1);
        Future<String> future2 = multiThreadPool.submit(myTask2);

        try {
            System.out.println("The status of the Task = "+future1.get());
            System.out.println("The status of the Task = "+future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }finally {
            multiThreadPool.shutdown();
        }
    }
}
