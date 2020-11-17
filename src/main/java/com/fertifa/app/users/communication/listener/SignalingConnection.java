package com.fertifa.app.users.communication.listener;

public interface SignalingConnection {
    void connect(String url);
    void send(Object data);
    void observable(SignalEventListener signalEventListener);
}
