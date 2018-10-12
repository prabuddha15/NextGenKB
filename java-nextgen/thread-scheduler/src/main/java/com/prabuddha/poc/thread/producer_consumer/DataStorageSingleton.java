package com.prabuddha.poc.thread.producer_consumer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DataStorageSingleton implements Serializable {
    private static Queue<Integer> dataList;
    private DataStorageSingleton(){}

    public static synchronized Queue<Integer> getInstance() {
        if(dataList == null){
            dataList=new LinkedList<>();
        }
        return dataList;
    }
}
