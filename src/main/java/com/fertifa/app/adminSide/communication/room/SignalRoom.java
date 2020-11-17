package com.fertifa.app.adminSide.communication.room;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.codec.binary.Base64;

public class SignalRoom {

    @SerializedName("fr")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("s")
    @Expose
    private String eventType;

    public SignalRoom() {
    }

    public SignalRoom(String from, String to, String eventType) {
        this.from = from;
        this.to = to;
        this.eventType = eventType;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

   /* public String roomId() {
        String signalRoomJson = new Gson().toJson(this);
        // encode signalRoom json object and return it
        return encodeData(signalRoomJson);
    }*/

    public String roomId() {
        return new Gson().toJson(this);
    }

    public SignalRoom getSignalRoomFromData(String signalRoomData) {
        return new Gson().fromJson(signalRoomData, SignalRoom.class);
    }

   /* public SignalRoom getSignalRoomFromData(String signalRoomData) {
        String mSignalRoomJson = decodeData(signalRoomData);
        return new Gson().fromJson(mSignalRoomJson, SignalRoom.class);
    }*/

    private String encodeData(String data) {
        //encoding  byte array into base 64
        byte[] encoded = Base64.encodeBase64(data.getBytes());
        return new String(encoded);
    }

    private String decodeData(String data) {
        //decoding byte array into base64
        byte[] decoded = Base64.decodeBase64(data);
        return new String(decoded);
    }

    @Override
    public String toString() {
        return "SignalRoom{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", eventType='" + eventType + '\'' +
                '}';
    }

    public static enum Event{
        CALLING_EVENT_NAME,
        INCOMING_CALL,
        REJECT_CALL,
        NO_ANSWER_CALL,
        ACCEPT_CALL,
        ONLINE_PARTICIPANT,
        OFFLINE_PARTICIPANT,
        END_CALL,
        // Socket connection Event
        PARTICIPANT_CONNECTED,
        PARTICIPANT_LEAVE,
        PARTICIPANTS_ROOM
    }


}
