package com.fertifa.app.notification.useractionnotification.api;


import com.fertifa.app.models.Users;
import com.fertifa.app.notification.useractionnotification.sendingemail.SendNotification;

import javax.servlet.ServletException;
import java.io.IOException;

public class EmailSender {

    public static void send(String message,String subject,int masterAdminCount) throws IOException, ServletException {
        sendEmail(message,subject,masterAdminCount);
    }

    public static void sendToUser(String message, Users users, String subject) throws IOException, ServletException {
        sendEmailToUser(message,users,subject);
    }

    private static void sendEmailToUser(String message, Users users,String subject) throws IOException, ServletException {
        SendNotification.sendNotificationToUser(message,users,subject);
    }

    private static void sendEmail(String message, String subject,int masterAdminCount) throws IOException, ServletException {
        SendNotification.sendNotificationAdmin(message,subject,masterAdminCount);
    }

}
