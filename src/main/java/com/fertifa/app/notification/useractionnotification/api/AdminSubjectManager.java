package com.fertifa.app.notification.useractionnotification.api;

public class AdminSubjectManager {
    public String getRightMessage(MessagesEnum testOrder, int i) {

        switch (testOrder) {
            case directChat:
                return AdminSubjectConstance.DIRECT_CHAT_MESSAGE_BODY;
            case feedBack:
                return AdminSubjectConstance.FEEDBACK_MESSAGE_BODY;
            case bookingAppointment:
                return AdminSubjectConstance.BOOK_APPOINTMENT_MESSAGE_BODY;
            case testOrder:
                return AdminSubjectConstance.TEST_ORDER_MESSAGE_BODY;
            case register:
                return AdminSubjectConstance.NEW_REGISTER_MESSAGE_BODY;
            case helpandcontact:
                return AdminSubjectConstance.HELP_CONTACT_MESSAGE_BODY;
            default:
                return null;
        }
    }
}
