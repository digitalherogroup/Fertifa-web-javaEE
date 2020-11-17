package com.fertifa.app.notification.useractionnotification.api;


import com.fertifa.app.models.Users;

import javax.servlet.ServletException;
import java.io.IOException;

public class EmailNotificationManager{

    public EmailNotificationManager(NotificationManager notificationManager, Users users, int i  ) throws IOException, ServletException {
        notificationManager.sendMessage(users, i);
    }
}
