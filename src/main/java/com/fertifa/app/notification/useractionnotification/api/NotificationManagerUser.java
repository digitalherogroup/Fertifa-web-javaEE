package com.fertifa.app.notification.useractionnotification.api;

import com.fertifa.app.models.Users;

import javax.servlet.ServletException;
import java.io.IOException;

public interface NotificationManagerUser {
    void sendUserMessage(Users users, int i) throws IOException, ServletException;
}
