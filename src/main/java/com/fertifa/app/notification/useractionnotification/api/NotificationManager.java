package com.fertifa.app.notification.useractionnotification.api;


import com.fertifa.app.models.Users;

import javax.servlet.ServletException;
import java.io.IOException;

public interface NotificationManager {
    void sendMessage(Users users, int i) throws IOException, ServletException;

}
