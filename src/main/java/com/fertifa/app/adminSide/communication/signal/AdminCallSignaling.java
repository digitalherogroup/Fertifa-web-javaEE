package com.fertifa.app.adminSide.communication.signal;

import com.fertifa.app.adminSide.communication.listener.SignalEventListener;
import com.fertifa.app.adminSide.communication.listener.SignalingConnection;
import com.fertifa.app.adminSide.communication.room.SignalRoom;
import io.socket.client.IO;
import io.socket.client.Socket;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import java.io.IOException;
import java.net.SocketException;

public class AdminCallSignaling implements SignalingConnection {

    private String url;
    private SignalEventListener signalEventListener;
    private String eventName = SignalRoom.Event.CALLING_EVENT_NAME.name();
    // socket io client
    private Socket socket;

    @Override
    public void connect(String url) {
        if (url == null || url.isEmpty())
            throw new NullPointerException("URL is required");
        this.url = url;
        //create socket connection
        connectToSocket();
    }

    private void connectToSocket() {
        try {
            IO.Options opts = new IO.Options();
            opts.reconnection = true;
            socket = IO.socket(url, opts);

            // listen server response
            socket.on(Socket.EVENT_CONNECT, args -> {
                // listen connected to room event
                try {
                    signalEventListener.onConnected();
                } catch (IOException | ServletException | NamingException e) {
                    e.printStackTrace();
                }
            }).on(Socket.EVENT_DISCONNECT, args -> {
                // listen disconnect to room event
                if (signalEventListener != null) {
                    signalEventListener.onDisconnected(null);
                }
            }).on(Socket.EVENT_ERROR, args -> {
                if (signalEventListener != null) {
                    signalEventListener.onDisconnected(new SocketException());
                }
            }).on(eventName, args -> {
                // new signal received
                if (signalEventListener != null) {
                    signalEventListener.onMessageReceived(args[0].toString());
                }
            });
            socket.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(Object data) {
        socket.emit(eventName, data);
    }

    @Override
    public void observable(SignalEventListener signalEventListener) {
        this.signalEventListener = signalEventListener;
    }
}
