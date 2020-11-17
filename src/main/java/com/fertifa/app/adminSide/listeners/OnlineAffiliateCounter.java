package com.fertifa.app.adminSide.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineAffiliateCounter  implements HttpSessionListener {
    private static int numberOfUsersOnline;


    public OnlineAffiliateCounter() {
        numberOfUsersOnline = 0;
    }

    public static int getNumberOfUsersOnline() {
        return numberOfUsersOnline;
    }

    public void sessionCreated(HttpSessionEvent event) {
        synchronized (this) {
            numberOfUsersOnline++;
        }

    }

    public void sessionDestroyed(HttpSessionEvent event) {

        synchronized (this) {
            numberOfUsersOnline--;
        }

    }
}
