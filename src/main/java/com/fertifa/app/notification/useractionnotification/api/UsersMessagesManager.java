package com.fertifa.app.notification.useractionnotification.api;


import com.fertifa.app.models.Users;
import com.fertifa.app.notification.useractionnotification.messagesbody.UsersMessageBodyConstance;

public class UsersMessagesManager {

    public String getRightMessage(MessagesEnum testOrder, Users users, int i) {

        switch (testOrder) {
            case feedBack:
                return UsersMessageBodyConstance.FEEDBACK_MESSAGE_BODY(users, i);
            case directChat:
                return UsersMessageBodyConstance.DIRECT_MESSAGE_BODY(users, i);
            case bookingAppointment:
                return UsersMessageBodyConstance.BOOK_APPOINTMENT_MESSAGE_BODY(users,i);
            case testOrder:
                return UsersMessageBodyConstance.TEST_ORDER_MESSAGE_BODY(users,i);
            case helpandcontact:
                return UsersMessageBodyConstance.HELP_CONTACT_MESSAGE_BODY(users,i);
            case enquiry:
                return UsersMessageBodyConstance.HEALTH_HISTORY_MESSAGE_BODY(users,i);
            case request:
                return UsersMessageBodyConstance.REQUEST_MESSAGE_BODY(users,i);
            default:
                return null;
        }
    }
}
