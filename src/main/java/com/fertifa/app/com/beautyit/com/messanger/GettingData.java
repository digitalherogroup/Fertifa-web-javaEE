package com.fertifa.app.com.beautyit.com.messanger;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.controllers.ChatSessionController;
import com.fertifa.app.controllers.UsersController;
import com.fertifa.app.models.ChatMessages;
import com.fertifa.app.models.ChatSession;
import com.fertifa.app.models.ChatSessionUsers;
import com.fertifa.app.models.Users;
import com.fertifa.app.notification.useractionnotification.api.EmailNotificationManager;
import com.fertifa.app.notification.useractionnotification.api.EmailNotificationManagerForUser;
import com.fertifa.app.utils.ChangeDateOfSession;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Path("/myresource")

public class GettingData {
    private EmailNotificationManager emailNotificationManager;
    private EmailNotificationManagerForUser emailNotificationManagerUser;
    ChatSessionController chatSessionController = new ChatSessionController();
    private static final String UPLOAD_DIRECTORY = "/upload";
    private ChangeDateOfSession changeDateOfSession = new ChangeDateOfSession();
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

    public Map<String, Object> getme(ChatSession chatSession) throws Exception {
        new ChatSession();
        chatSession.setChatSessionToken(chatSession.getChatSessionToken());
        chatSession.setChatSessionType(chatSession.getChatSessionType());
        chatSession.setChatFromId(chatSession.getChatFromId());
        chatSession.setChatToId(chatSession.getChatToId());
        chatSession.setChatSessionName(chatSession.getChatSessionName());
        if (chatSessionController.CreateSession(chatSession) > 0) {
            changeDateOfSession.changeDateOfMessageDate(new Timestamp(new Date().getTime()), chatSession.getChatSessionToken());
            jsonMap = new LinkedHashMap<>();
            jsonMap = sendSuccessObjects(chatSession);
            return jsonMap;
        } else {
            jsonMap = new LinkedHashMap<>();
            jsonMap = sendErrorMessage();
        }
        return jsonMap;

    }

    private Map<String, Object> sendSuccessObjects(ChatSession chatSessionToken) throws Exception {
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
        if (list == null || list.isEmpty() || list.size() == 0) {
            jsonMap.put("status", "error");
            jsonMap.put("message", "Something went wrong");
            throw new Exception("Something went wrong");
        } else {
            jsonMap.put("status", "success");
            jsonMap.put("role", roleUser);
            jsonMap.put("id", list.get(0).getChatSessionId());
            jsonMap.put("creation_date", new Timestamp(new Date().getTime()));
        }
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

        Response.status(200)
            .entity(output).header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-GettersMethod", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
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

    private Map<String, Object> sendErrorMessage() throws JsonProcessingException {
        jsonMap = new LinkedHashMap<>();
        jsonMap.put("status", "error");
        jsonMap.put("message", "Something went wrong");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
        return jsonMap;
    }

    private Map<String, Object> sendSuccessMessageObjects() throws JsonProcessingException {

        jsonMap.put("status", "success");
        /*jsonMap.put("id", id);*/
        jsonMap.put("creation_date", new Timestamp(new Date().getTime()));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
        return jsonMap;
    }

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
                    jsonMap.put("url", "chat/conversation?id=" + chatMessages.getChatTokenId());
                    jsonMap.put("messageType", chatMessages.getChatType());
                    jsonMap.put("creation_date", new Timestamp(new Date().getTime()));
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);

                    changeDateOfSession.changeDateOfMessage(new Timestamp(new Date().getTime()), chatMessages);
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
                    jsonMap.put("url", "chat/conversation?id=" + chatMessages.getChatTokenId());
                    jsonMap.put("messageType", chatMessages.getChatType());
                    jsonMap.put("creation_date", new Timestamp(new Date().getTime()));
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
                    changeDateOfSession.changeDateOfMessage(new Timestamp(new Date().getTime()), chatMessages);
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
                jsonMap.put("url", "chat?id=" + chatMessages.getChatTokenId());
                jsonMap.put("messageType", chatMessages.getChatType());
                jsonMap.put("creation_date", new Timestamp(new Date().getTime()));
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
                changeDateOfSession.changeDateOfMessage(new Timestamp(new Date().getTime()), chatMessages);
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

    @POST
    @Path("/Upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
        @FormDataParam("image") InputStream uploadedInputStream,
        @FormDataParam("image") FormDataContentDisposition fileDetail) {


        //String uploadedFileLocation = "C:\\Users\\IT-HOME\\Desktop\\Fertifa\\src\\main\\webapp\\upload\\" + fileDetail.getFileName();
        //String uploadedFileLocation = "/main/webapp/upload/" + fileDetail.getFileName();

        String uploadedFileLocation = "/home/fertifabenefits/jvm/apache-tomcat-9.0.27/domains/fertifabenefits.com/ROOT/upload/" + fileDetail.getFileName();
        //String uploadedFileLocation = System.getProperty("user.dir").concat("\\src\\main\\webapp\\upload\\") + fileDetail.getFileName();
        //String uploadedFileLocation = "/home/fertifabenefits/public_html/upload" + fileDetail.getFileName();

        // save it
        if (writeToFile(uploadedInputStream, uploadedFileLocation) == 0) {
            //String output = "<a href=\"" + Constances.WEBSITE + "upload/" + fileDetail.getFileName() + "\" target=\"_blank\"";
            System.out.println("==========>>>>>   SUCCESS");
            String output = "https://second.fertifabenefits.com/upload/" + fileDetail.getFileName();
            System.out.println(output);
            return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-GettersMethod", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
        } else {
            System.out.println("==========>>>>>   Failure");
            return Response.status(201).entity("==========>>>>>   Failure").build();
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