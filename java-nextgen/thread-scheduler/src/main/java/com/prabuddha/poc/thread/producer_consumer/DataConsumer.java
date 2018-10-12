package com.prabuddha.poc.thread.producer_consumer;

import java.util.List;
import java.util.Queue;

public class DataConsumer implements Runnable{

    private final Object lock = GlobalLock.getInstance();
    private Queue<Integer> dataStore = DataStorageSingleton.getInstance();

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName()+ " is consumeing Data with size = "+dataStore.size());
                if (dataStore.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    dataStore.remove();
                    lock.notifyAll();
                }
            }
        }
    }
}
