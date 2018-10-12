package com.prabuddha.poc.thread.producer_consumer;

public class GlobalLock {

    private static Object lock;

    private GlobalLock(){

    }

    public static synchronized Object getInstance() {
        if(lock == null){
            lock=new GlobalLock();
        }
        return lock;
    }


}
