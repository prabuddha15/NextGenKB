package com.prabuddha.poc.thread.alarm;


import com.prabuddha.poc.thread.alarm.task.StartAlarmTask;
import com.prabuddha.poc.thread.alarm.task.StopAlarmTask;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class AlarmClock {

    private final ScheduledExecutorService fScheduler;
    private final long fAlarmStartTime;
    private final long fDelayBetweenBeeps;
    private final long fShutdownAlarmAfter;
    public static final int NUM_THREADS = 1;
    public static final boolean DONT_INTERRUPT_IF_RUNNING = false;

    public AlarmClock(long fAlarmStartTime, long fDelayBetweenBeeps, long fShutdownAlarmAfter) {
        this.fAlarmStartTime = fAlarmStartTime;
        this.fDelayBetweenBeeps = fDelayBetweenBeeps;
        this.fShutdownAlarmAfter = fShutdownAlarmAfter;
        fScheduler = Executors.newScheduledThreadPool(NUM_THREADS);
    }

    public void activateAlarmThenStop() {
        Runnable startAlarm = new StartAlarmTask();
        System.out.println("Current System Time is  " + LocalDateTime.now() + ". Set the alarm after " + fAlarmStartTime + " Seconds .");
        ScheduledFuture<?> soundAlarmFuture = fScheduler.scheduleWithFixedDelay(startAlarm, fAlarmStartTime, fDelayBetweenBeeps, TimeUnit.SECONDS);
        System.out.println("Current System Time is  " + LocalDateTime.now() + " . Stop the Alarm after " + fShutdownAlarmAfter + " Seconds.");
        Runnable stopAlarm = new StopAlarmTask(soundAlarmFuture, fScheduler);
        fScheduler.schedule(stopAlarm, fShutdownAlarmAfter, TimeUnit.SECONDS);

    }

    public static void main(String[] args) {
        System.out.println("Alarm Clock Application :: ");
        AlarmClock alarmClock = new AlarmClock(5, 1, 60);
        alarmClock.activateAlarmThenStop();
    }

}
