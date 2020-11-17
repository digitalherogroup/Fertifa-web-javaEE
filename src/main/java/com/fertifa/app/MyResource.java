package com.fertifa.app;

import com.fertifa.app.models.ChatSession;

import javax.sound.midi.Track;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/json/metallica")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
   /* @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getIt(ChatSession chatSession) {
        System.out.println(chatSession.getChatSessionName());
        System.out.println(chatSession.getChatCreationDate());
        return Response.status(200).entity("Your order is in-progress").build();
    }*/

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public ChatSession getTrackInJSON() {

        ChatSession track = new ChatSession();
        track.setChatFromId(8888);
        track.setChatSessionName("Metallica");

        return track;

    }

    @POST
    @Path("/post/a")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(Track track) {

        String result = "Track saved : " + track;
        return Response.status(201).entity(result).build();

    }
}
