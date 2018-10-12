package com.prabuddha.poc.thread.producer_consumer;

import java.util.List;
import java.util.Queue;

public class DataProducer implements Runnable{

    private final Object lock = GlobalLock.getInstance();
    private Queue<Integer> dataStore = DataStorageSingleton.getInstance();
    private final static int MAX_DATA_STORE_SIZE = 20;

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName()+ " is producing Data with size = "+dataStore.size());
                if (dataStore.size() == MAX_DATA_STORE_SIZE) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    dataStore.add(1);
                    lock.notifyAll();
                }
            }
        }
    }
}