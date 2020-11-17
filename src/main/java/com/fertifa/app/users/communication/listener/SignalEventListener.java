package com.fertifa.app.users.communication.listener;


import javax.naming.NamingException;
import javax.servlet.ServletException;
import java.io.IOException;

public interface SignalEventListener {
    void onConnected() throws IOException, ServletException, NamingException;
    void onDisconnected( Throwable error);
    void onMessageReceived(Object message);
}
