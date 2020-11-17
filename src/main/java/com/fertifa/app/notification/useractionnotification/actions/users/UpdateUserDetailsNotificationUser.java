package com.fertifa.app.notification.useractionnotification.actions.users;


import com.fertifa.app.models.Users;
import com.fertifa.app.notification.useractionnotification.api.EmailSender;
import com.fertifa.app.notification.useractionnotification.api.MessagesEnum;
import com.fertifa.app.notification.useractionnotification.api.NotificationManagerUser;
import com.fertifa.app.notification.useractionnotification.api.UsersMessagesManager;
import com.fertifa.app.notification.useractionnotification.api.UsersSubjectManager;

import javax.servlet.ServletException;
import java.io.IOException;

public class UpdateUserDetailsNotificationUser implements NotificationManagerUser {

    @Override
    public void sendUserMessage(Users users, int i) throws IOException, ServletException {
        String message = new UsersMessagesManager().getRightMessage(MessagesEnum.updateDetails,users,i);
        String subject = new UsersSubjectManager().getRightMessage(MessagesEnum.updateDetails, i);
        EmailSender.sendToUser(message,users,subject);
    }
}
