package com.fertifa.app.com.beautyit.com.messanger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


public class ChatStart {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static void startChat(String jsonBody) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:9090/api/v1/" + jsonBody);
        target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(String.class);
    }
}
