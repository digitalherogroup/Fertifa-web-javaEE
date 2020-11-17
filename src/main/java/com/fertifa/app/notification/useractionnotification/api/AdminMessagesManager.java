package com.fertifa.app.notification.useractionnotification.api;


import com.fertifa.app.models.Users;
import com.fertifa.app.notification.useractionnotification.messagesbody.AdminMessageBodyConstance;

public class AdminMessagesManager {


    public String getRightMessage(MessagesEnum testOrder, Users users, int i) {

        switch (testOrder) {
            case directChat:
                return AdminMessageBodyConstance.DIRECT_CHAT_MESSAGE_BODY(users, i);
            case feedBack:
                return AdminMessageBodyConstance.FEEDBACK_MESSAGE_BODY(users, i);
            case bookingAppointment:
                return AdminMessageBodyConstance.BOOK_APPOINTMENT_MESSAGE_BODY(users,i);
            case testOrder:
                return AdminMessageBodyConstance.TEST_ORDER_MESSAGE_BODY(users,i);
            case register:
                return AdminMessageBodyConstance.NEW_REGISTER_MESSAGE_BODY (users,i);
            case helpandcontact:
                return AdminMessageBodyConstance.HELP_CONTACT_MESSAGE_BODY(users,i);
            case enquiry:
                return AdminMessageBodyConstance.HEALTH_HISTORY_MESSAGE_BODY(users,i);
            case request:
                return AdminMessageBodyConstance.REQUEST_MESSAGE_BODY(users,i);
            default:
                return null;
        }
    }
}
