package com.fertifa.app.notification.useractionnotification.api;

public class AdminMasterEmailInChargeManager {
    public int getRightMessage(MessagesEnum enums) {
        switch (enums) {

            case feedBack:
            case testOrder:
            case helpandcontact:
            case bookingAppointment:
                return 2;
            case register:
            case directChat:
            case updateDetails:
            case enquiry:
            case request:
            case healthHistory:
                return 1;
            default:
                return 0;
        }
    }
}
