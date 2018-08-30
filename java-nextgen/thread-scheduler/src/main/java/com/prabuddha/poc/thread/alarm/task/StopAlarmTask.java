package com.prabuddha.poc.thread.alarm.task;

import com.prabuddha.poc.thread.alarm.AlarmClock;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class StopAlarmTask implements Runnable {

    private ScheduledFuture<?> fScheduledFuture;
    private ScheduledExecutorService fScheduler;

    public StopAlarmTask(ScheduledFuture fScheduledFuture, ScheduledExecutorService fScheduler) {
        this.fScheduledFuture = fScheduledFuture;
        this.fScheduler = fScheduler;
    }


    @Override
    public void run() {
        fScheduledFuture.cancel(AlarmClock.DONT_INTERRUPT_IF_RUNNING);
        fScheduler.shutdown();
        System.out.println("Good Bye");
    }
}
