package com.fertifa.app.notification.useractionnotification.actions.admin;


import com.fertifa.app.models.Users;
import com.fertifa.app.notification.useractionnotification.api.AdminMasterEmailInChargeManager;
import com.fertifa.app.notification.useractionnotification.api.AdminMessagesManager;
import com.fertifa.app.notification.useractionnotification.api.AdminSubjectManager;
import com.fertifa.app.notification.useractionnotification.api.EmailSender;
import com.fertifa.app.notification.useractionnotification.api.MessagesEnum;
import com.fertifa.app.notification.useractionnotification.api.NotificationManager;

import javax.servlet.ServletException;
import java.io.IOException;

public class TestOrderNotification implements NotificationManager {

    @Override
    public void sendMessage(Users users, int i) throws IOException, ServletException {
        String message = new AdminMessagesManager().getRightMessage(MessagesEnum.testOrder,users,i);
        String subject = new AdminSubjectManager().getRightMessage(MessagesEnum.testOrder,i);
        int adminMasterCount = new AdminMasterEmailInChargeManager().getRightMessage(MessagesEnum.testOrder);
        EmailSender.send(message,subject,adminMasterCount);
    }
}
