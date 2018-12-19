package com.prabuddha.poc.thread.Project_1.processor;

import com.prabuddha.poc.thread.Project_1.model.Message;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class DataProcessor implements Callable<String> {

    private List<String> lineText;
    private CountDownLatch countDownLatch;
    private BlockingQueue<Message> queue;

    public DataProcessor(List<String> lineText, CountDownLatch countDownLatch, BlockingQueue<Message> queue) {
        this.countDownLatch = countDownLatch;
        this.lineText = lineText;
        this.queue=queue;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Thread -> "+ Thread.currentThread().getName()+" is started executing");
        lineText.parallelStream().forEach(line->{
            String firstName = Arrays.asList(line.split("\\|")).get(0);
            try {
                queue.put(new Message(firstName,Thread.currentThread().getName(),LocalDateTime.now()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        countDownLatch.countDown();
        System.out.println(Thread.currentThread().getName()+ " >> Value of count down Latch --> "+countDownLatch.getCount());
        return Thread.currentThread().getName();
    }
}
