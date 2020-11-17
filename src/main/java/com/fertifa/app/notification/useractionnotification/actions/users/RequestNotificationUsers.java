package com.fertifa.app.notification.useractionnotification.actions.users;


import com.fertifa.app.models.Users;
import com.fertifa.app.notification.useractionnotification.api.EmailSender;
import com.fertifa.app.notification.useractionnotification.api.MessagesEnum;
import com.fertifa.app.notification.useractionnotification.api.NotificationManager;
import com.fertifa.app.notification.useractionnotification.api.UsersMessagesManager;
import com.fertifa.app.notification.useractionnotification.api.UsersSubjectManager;

import javax.servlet.ServletException;
import java.io.IOException;

public class RequestNotificationUsers implements NotificationManager {

    @Override
    public void sendMessage(Users users, int i) throws IOException, ServletException {
        String message = new UsersMessagesManager().getRightMessage(MessagesEnum.request,users,i);
        String subject = new UsersSubjectManager().getRightMessage(MessagesEnum.request, i);
        EmailSender.sendToUser(message,users,subject);
    }
}
