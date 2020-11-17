package com.fertifa.app.notification.useractionnotification.api;


import com.fertifa.app.models.Users;

import javax.servlet.ServletException;
import java.io.IOException;

public class EmailNotificationManagerForUser {

    public EmailNotificationManagerForUser(NotificationManagerUser notificationManager, Users users, int i) throws IOException, ServletException {

        notificationManager.sendUserMessage(users, i);
    }
}
