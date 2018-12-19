package com.prabuddha.poc.thread.Project_1.processor;

import com.prabuddha.poc.thread.Project_1.model.Message;

import java.util.concurrent.BlockingQueue;

public class DataConsumer  implements Runnable{

    private BlockingQueue<Message> queue;

    public DataConsumer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            Message msg;
            //consuming messages until exit message is received
            while((msg = queue.take()).getMessage() !="exit"){
                System.out.println("Consumed --> "+msg.toString());
                Thread.sleep(1000);
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
