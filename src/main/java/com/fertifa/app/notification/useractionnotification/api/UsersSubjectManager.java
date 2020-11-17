package com.fertifa.app.notification.useractionnotification.api;

public class UsersSubjectManager {
    public String getRightMessage(MessagesEnum testOrder, int i) {

        switch (testOrder) {
            case directChat:
                return UsersSubjectConstance.DIRECT_CHAT_MESSAGE_BODY;
            case feedBack:
                return UsersSubjectConstance.FEEDBACK_MESSAGE_BODY;
            case bookingAppointment:
                return UsersSubjectConstance.BOOK_APPOINTMENT_MESSAGE_BODY;
            case testOrder:
                return UsersSubjectConstance.TEST_ORDER_MESSAGE_BODY;
            case helpandcontact:
                return UsersSubjectConstance.HELP_CONTACT_MESSAGE_BODY;
            case enquiry:
                return UsersSubjectConstance.ENQUIRY_MESSAGE_BODY;
            default:
                return null;
        }
    }
}
