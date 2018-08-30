package com.prabuddha.poc.thread.alarm.task;

import java.time.LocalDateTime;

public class StartAlarmTask implements Runnable {

    private int fBeepCount;

    @Override
    public void run() {
        ++fBeepCount;
        System.out.println("Current System Time :: " + LocalDateTime.now() + "  :: beep --> " + fBeepCount);
    }
}
