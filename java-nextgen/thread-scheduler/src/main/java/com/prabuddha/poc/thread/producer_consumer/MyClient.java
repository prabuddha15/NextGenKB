package com.prabuddha.poc.thread.producer_consumer;

public class MyClient {

    public static void main(String[] args) {
        DataProducer producer=new DataProducer();
        DataConsumer consumer=new DataConsumer();

        Thread producerThread1=new Thread(producer);
        producerThread1.setName("producerThread1");

        Thread producerThread2=new Thread(producer);
        producerThread1.setName("producerThread2");

        producerThread1.start();
        producerThread2.start();

        Thread consumerThread1=new Thread(consumer);
        consumerThread1.setName("consumerThread1");

        Thread consumerThread2=new Thread(consumer);
        consumerThread1.setName("consumerThread1");

        consumerThread1.start();
        consumerThread2.start();

    }
}
