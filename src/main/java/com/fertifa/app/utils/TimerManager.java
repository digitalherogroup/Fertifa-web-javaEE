package com.fertifa.app.utils;


import com.fertifa.app.models.Users;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerManager {

    public void checkTask(Users users, String Task) {
        int interval = 100000; // 10 sec
        Date timeToRun = new Date(System.currentTimeMillis() + interval);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                // Task here ...
            }
        }, timeToRun);
    }

}
