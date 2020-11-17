package com.fertifa.app.testcall;

import com.fertifa.app.adminSide.communication.client.AdminSignalingClient;
import com.fertifa.app.users.communication.client.EmployeeSignalingClient;
import com.fertifa.app.users.communication.room.SignalRoom;
import com.fertifa.app.users.communication.signal.EmployeeCallSignaling;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employee/start_call")
public class Start_call extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        startCall(request, response);
    }

    private void startCall(HttpServletRequest request, HttpServletResponse response) {
        // send incoming call data to signaling server
        EmployeeSignalingClient
                .getInstance(new EmployeeCallSignaling())
                .sendMessage(getIncomingCallMessage());
    }

    private Object getIncomingCallMessage() {
        SignalRoom signalRoom = new SignalRoom();
        signalRoom.setFrom(EmployeeSignalingClient.UUID);
        signalRoom.setTo(AdminSignalingClient.UUID);
        signalRoom.setEventType(SignalRoom.Event.INCOMING_CALL.name());
        return signalRoom;
    }
}
