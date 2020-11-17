package com.fertifa.app.users.communication.client;

import com.fertifa.app.users.communication.constances.UserVideoConstance;
import com.fertifa.app.users.communication.listener.SignalEventListener;
import com.fertifa.app.users.communication.listener.SignalingConnection;
import com.fertifa.app.users.communication.room.SignalRoom;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicBoolean;


public class EmployeeSignalingClient implements SignalEventListener {
    private static EmployeeSignalingClient instance = null;

    private static final String TAG = EmployeeSignalingClient.class.getName();

    public static String UUID = "USER ID";

    private HttpServletRequest request;
    private HttpServletResponse response;

    private SignalingConnection signalingConnection;
    private static AtomicBoolean isRunning = new AtomicBoolean(false);


    public static synchronized EmployeeSignalingClient getInstance(SignalingConnection signalingConnection) {
        if (instance == null) {
            instance = new EmployeeSignalingClient(signalingConnection);
        }
        return instance;
    }

    public static boolean isRunning() {
        return isRunning.get();
    }

    public static synchronized EmployeeSignalingClient getInstance() {
        if (instance == null) {
            instance = new EmployeeSignalingClient();
        }
        return instance;
    }

    private EmployeeSignalingClient(SignalingConnection signalingConnection) {

        if (signalingConnection == null) {
            throw new NullPointerException("SignalingConnection can't be null");
        }
        this.signalingConnection = signalingConnection;
    }

    private EmployeeSignalingClient() {
    }

    public void start() {

        if (!isRunning()) {
            signalingConnection.observable(this);
            signalingConnection.connect(UserVideoConstance.SOCKET_URL_DEVELOPMENT);
            isRunning.set(true);
        }
    }

    @Override
    public void onConnected() {
        SignalRoom signalRoom = new SignalRoom();
        signalRoom.setFrom(UUID);
        signalRoom.setTo("ADMIN ID");
        signalRoom.setEventType(SignalRoom.Event.PARTICIPANT_CONNECTED.name());
        // send connect data
        sendMessage(signalRoom);
    }

    @Override
    public void onDisconnected(Throwable error) {

        SignalRoom signalRoom = new SignalRoom();
        signalRoom.setFrom(UUID);
        signalRoom.setEventType(SignalRoom.Event.PARTICIPANT_CONNECTED.name());
        sendMessage(signalRoom);
    }

    @Override
    public void onMessageReceived(Object message) {
        System.out.println(TAG + "Object message------>>>>>>>  " + message.toString());
        // convert message to signal room
        SignalRoom signalRoom = new SignalRoom()
                .getSignalRoomFromData(message.toString());
        System.out.println(TAG + "SignalRoom message------>>>>>>>  " + signalRoom.toString());
        if (signalRoom.getEventType().equals(SignalRoom.Event.INCOMING_CALL.name())) {
            System.out.println(TAG + "INCOMING_CALL------>>>>>>>  ");
        } else if (signalRoom.getEventType().equals(SignalRoom.Event.ACCEPT_CALL.name())) {
            System.out.println(TAG + "ACCEPT_CALL------>>>>>>>  ");
        } else if (signalRoom.getEventType().equals(SignalRoom.Event.REJECT_CALL.name())) {
            System.out.println(TAG + "REJECT_CALL------>>>>>>>  ");
        } else if (signalRoom.getEventType().equals(SignalRoom.Event.NO_ANSWER_CALL.name())) {
            System.out.println(TAG + "NO_ANSWER_CALL------>>>>>>>  ");
        } else if (signalRoom.getEventType().equals(SignalRoom.Event.END_CALL.name())) {
            System.out.println(TAG + "END_CALL------>>>>>>>  ");
        } else if (signalRoom.getEventType().equals(SignalRoom.Event.OFFLINE_PARTICIPANT.name())) {
            System.out.println(TAG + "OFFLINE_PARTICIPANT------>>>>>>>  ");
        } else if (signalRoom.getEventType().equals(SignalRoom.Event.ONLINE_PARTICIPANT.name())) {
            System.out.println(TAG + "ONLINE_PARTICIPANT------>>>>>>>  ");
        }
    }

    public void sendMessage(Object message) {
        signalingConnection.send(new Gson().toJson(message));
    }
}
