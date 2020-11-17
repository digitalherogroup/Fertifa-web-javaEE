package com.fertifa.app;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.ChatSessionController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.ChatMessages;
import com.fertifa.app.models.ChatSession;
import com.fertifa.app.models.ChatSessionUsers;
import com.fertifa.app.models.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Path("/myresource")

public class GettingData {
    ChatSessionController chatSessionController = new ChatSessionController();
    private static final String UPLOAD_DIRECTORY = "/upload";

    Map<String, Object> jsonMap = new LinkedHashMap<>();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Map<String, Object> getme(ChatSession chatSession) throws IOException, SQLException {
        new ChatSession();
        chatSession.setChatSessionToken(chatSession.getChatSessionToken());
        chatSession.setChatSessionType(chatSession.getChatSessionType());
        chatSession.setChatFromId(chatSession.getChatFromId());
        chatSession.setChatToId(chatSession.getChatToId());
        chatSession.setChatSessionName(chatSession.getChatSessionName());
        if (chatSessionController.CreateSession(chatSession) > 0) {
            jsonMap = new LinkedHashMap<>();
            jsonMap = sendSuccessObjects(chatSession);
            return jsonMap;
        } else {
            jsonMap = new LinkedHashMap<>();
            jsonMap = sendErrorMessage();
        }
        return jsonMap;

    }

    private Map<String, Object> sendSuccessObjects(ChatSession chatSessionToken) throws JsonProcessingException {
        String roleUser = "";

        int role = 1;
        UsersController usersController = new UsersController();
        if (chatSessionToken.getChatFromId() != 1) {
            role = usersController.getUSerRoleById(chatSessionToken.getChatFromId());
        } else {
            role = usersController.getUSerRoleById(chatSessionToken.getChatToId());
        }

        if (role == 2) {
            roleUser = "employer";
        } else if (role == 3) {
            roleUser = "employee";
        }

        List<ChatSession> list = chatSessionController.getAllByToken(chatSessionToken.getChatSessionToken());
        jsonMap.put("status", "success");
        jsonMap.put("id", list.get(0).getChatSessionId());
        jsonMap.put("role", roleUser);
        jsonMap.put("creation_date", list.get(0).getChatCreationDate());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
        return jsonMap;
    }

    //changing importance of session
    @POST
    @Path("/important")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> importantChange(ChatSession chatSession) throws SQLException, JsonProcessingException {
        new ChatSession();
      String output = Constances.WEBSITE + "/important";

        Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();

        Map<String, String> importantMap = new LinkedHashMap<>();
        chatSession.setChatSessionToken(chatSession.getChatSessionToken());
        chatSession.setChatFromId(chatSession.getChatFromId());
        chatSession.setImportantFor(chatSession.getImportantFor());

        if (checkWhoIsImportant(chatSession.getImportantFor().toLowerCase(), chatSession.getChatSessionToken()) > 0) {
            //success message
            importantMap.put("status", "success");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
            return importantMap;

        } else {
            //error message
            importantMap.put("status", "error");
            System.out.println("inside RX important");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
            return importantMap;
        }
    }

    private int checkWhoIsImportant(String type, String chatSessionId) throws SQLException {
        return chatSessionController.updateImportanceForSession(type, chatSessionId);
    }

    @POST
    @Path("/SessionUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getSessionUser(ChatSessionUsers chatSessionUsers) throws SQLException, JsonProcessingException {
        new ChatSessionUsers();

        chatSessionUsers.setChatUserId(chatSessionUsers.getChatUserId());
        chatSessionUsers.setChatSessionID(chatSessionUsers.getChatSessionID());
        if (chatSessionController.CreatSessionUserId(chatSessionUsers) > 0) {
            jsonMap = new LinkedHashMap<>();
            jsonMap = sendSuccessUsersObjects(chatSessionUsers);
            return jsonMap;
        } else {
            jsonMap = new LinkedHashMap<>();
            jsonMap = sendErrorMessage();
        }
        return jsonMap;

    }

    @POST
    @Path("/CloseSession")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getSessionUserClose(ChatSession chatSession) throws SQLException, JsonProcessingException {
        new ChatSessionUsers();
        chatSession.setChatSessionToken(chatSession.getChatSessionToken());
        String token = chatSession.getChatSessionToken();
        //chatSessionUsers.setChatUserId(chatSessionUsers.getChatUserId());
        //chatSessionUsers.setChatSessionID(chatSessionUsers.getChatSessionID());
        if (chatSessionController.CloseSession(token) > 0) {
            jsonMap = new LinkedHashMap<>();
            jsonMap.put("status", "success");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
            return jsonMap;
        } else {
            jsonMap = new LinkedHashMap<>();
            jsonMap = sendErrorMessage();
        }
        return jsonMap;

    }

    /* private Map<String, Object> sendErrorUsersObjects(ChatSessionUsers chatSessionUsers) throws JsonProcessingException {
         jsonMap = new LinkedHashMap<>();
         jsonMap.put("status", "error");
         jsonMap.put("message", "Something went wrong");
         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
         return jsonMap;
     }
 */
    private Map<String, Object> sendSuccessUsersObjects(ChatSessionUsers chatSessionUsers) throws JsonProcessingException, SQLException {
        int idSessionUsers = chatSessionController.getSessionUsers(chatSessionUsers.getChatUserId(), chatSessionUsers.getChatSessionID());
        jsonMap.put("status", "success");
        jsonMap.put("id", idSessionUsers);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
        return jsonMap;
    }


    @POST
    @Path("/Message")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getMessage(ChatMessages chatMessages) throws SQLException, JsonProcessingException {
        new ChatMessages();
        chatMessages.setChatTokenId(chatMessages.getChatTokenId());
        chatMessages.setChatFrom(chatMessages.getChatFrom());
        chatMessages.setChatTo(chatMessages.getChatTo());
        chatMessages.setChatType(chatMessages.getChatType());
        chatMessages.setChatBody(chatMessages.getChatBody());
        int responseId = chatMessages.getChatFrom();
        int reciveid = chatMessages.getChatTo();
        if (responseId == 1) {
            UsersController usersController = new UsersController();
            List<Users> list = usersController.getAllUsersListById(reciveid);

            if (list.get(0).getBranchId() == 3) {
                try {
                    chatSessionController.CreateMessage(chatMessages);
                    jsonMap = new LinkedHashMap<>();
                    jsonMap.put("status", "success");
                    jsonMap.put("url", "conversation?id=" + chatMessages.getChatTokenId());
                    jsonMap.put("messageType", chatMessages.getChatType());
                    jsonMap.put("creation_date", new Timestamp(new Date().getTime()));
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
                    return jsonMap;
                } catch (Exception e) {
                    jsonMap = new LinkedHashMap<>();
                    e.printStackTrace();
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
                    return jsonMap;
                }
            } else if (list.get(0).getBranchId() == 2) {
                try {
                    chatSessionController.CreateMessage(chatMessages);
                    jsonMap = new LinkedHashMap<>();
                    jsonMap.put("status", "success");
                    jsonMap.put("url", "conversation?id=" + chatMessages.getChatTokenId());
                    jsonMap.put("messageType", chatMessages.getChatType());
                    jsonMap.put("creation_date", new Timestamp(new Date().getTime()));
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
                    return jsonMap;

                } catch (Exception e) {
                    jsonMap = new LinkedHashMap<>();
                    e.printStackTrace();
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
                    return jsonMap;
                }

            }
        } else {
            try {
                chatSessionController.CreateMessage(chatMessages);
                jsonMap = new LinkedHashMap<>();
                jsonMap.put("status", "success");
                jsonMap.put("url", "conversation?id=" + chatMessages.getChatTokenId());
                jsonMap.put("messageType", chatMessages.getChatType());
                jsonMap.put("creation_date", new Timestamp(new Date().getTime()));
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
                return jsonMap;

            } catch (Exception e) {
                jsonMap = new LinkedHashMap<>();
                e.printStackTrace();
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
                return jsonMap;
            }
        }
        return jsonMap;
    }
/*
    @POST
    @Path("/CloseSession")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> CloseMessage(ChatMessages chatMessages) throws SQLException, JsonProcessingException {
        chatMessages.setChatTokenId(chatMessages.getChatTokenId());
        String tokenSession = chatMessages.getChatTokenId();

        if (chatSessionController.CloseSession(tokenSession) > 0) {
            jsonMap = new LinkedHashMap<>();
            jsonMap.put("status", "success");
        } else {
            jsonMap = new LinkedHashMap<>();
            jsonMap.put("status", "error");
        }

        return jsonMap;
    }*/


    private Map<String, Object> sendErrorMessage() throws JsonProcessingException {
        jsonMap = new LinkedHashMap<>();
        jsonMap.put("status", "error");
        jsonMap.put("message", "Something went wrong");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
        return jsonMap;
    }

    private Map<String, Object> sendSuccessMessageObjects() throws JsonProcessingException {
        List<ChatMessages> list = new ArrayList<>();
        jsonMap.put("status", "success");
        /*jsonMap.put("id", id);*/
        jsonMap.put("creation_date", new Timestamp(new Date().getTime()));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
        return jsonMap;
    }



    @POST
    @Path("/Upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)


    public Response uploadFile(
            @FormDataParam("image") InputStream uploadedInputStream,
            @FormDataParam("image") FormDataContentDisposition fileDetail) {


        //String uploadedFileLocation = "C:\\com.fertifa.app.Users\\IT-HOME\\Desktop\\Fertifa\\src\\main\\webapp\\upload\\" + fileDetail.getFileName();
        //String uploadedFileLocation = "/main/webapp/upload/" + fileDetail.getFileName();

        String uploadedFileLocation = "/home/fertifabenefits/jvm/apache-tomcat-9.0.27/domains/second.fertifabenefits.com/ROOT/upload/" + fileDetail.getFileName();
        //String uploadedFileLocation = System.getProperty("user.dir").concat("\\src\\main\\webapp\\upload\\") + fileDetail.getFileName();
        //String uploadedFileLocation = "/home/fertifabenefits/public_html/upload" + fileDetail.getFileName();

        // save it
        if (writeToFile(uploadedInputStream, uploadedFileLocation) == 0) {
            //String output = "<a href=\"" + com.fertifa.app.Constances.WEBSITE + "upload/" + fileDetail.getFileName() + "\" target=\"_blank\"";
            String output = Constances.WEBSITE + "upload/" + fileDetail.getFileName();
            System.out.println(output);
            return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .build();
        } else {
            return Response.status(201).entity("error").build();
        }
    }

    // save uploaded file to new location
    private int writeToFile(InputStream uploadedInputStream,
                            String uploadedFileLocation) {
        int i = 0;

        try {
            //OutputStream out = new FileOutputStream(new File("D://timer.pdf"));
            OutputStream out = new FileOutputStream(new File(
                    uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                System.out.println("bytes " + bytes.toString());
                out.write(bytes, 0, read);
            }

            out.flush();
            out.close();
        } catch (IOException e) {
            i = 1;
            e.printStackTrace();
        }
        return i;
    }
}
