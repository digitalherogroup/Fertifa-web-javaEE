package com.fertifa.app.utils;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.network.EmailBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SendEmailToAdmin {
    public static void send(String sendName, String messageCategory, String messageType, String body, String email) {
        String subject = "Question From" + sendName;

        EmailBody emailBody = new EmailBody();
        // create content list
        List<EmailBody.Content> contentList = new ArrayList<EmailBody.Content>();
        EmailBody.Content content = new EmailBody.Content();
        content.setValue(body + "\n\n\n Sent for Category ՝ "
                + messageCategory + " \nType of the message " + messageType);
        content.setType("text/plain");
        contentList.add(content);
        // add content list to body
        emailBody.setContent(contentList);
        // add from data to body
        emailBody.setFrom(new EmailBody.From(email));
        // add send information to body
        List<EmailBody.Personalization> personalizations = new ArrayList<EmailBody.Personalization>();
        EmailBody.Personalization personalization = new EmailBody.Personalization();
        personalization.setSubject(subject);
        List<EmailBody.To> toList = new ArrayList<EmailBody.To>();
        toList.add(new EmailBody.To(Constances.USERNAME));
        personalization.setTo(toList);
        personalizations.add(personalization);
        emailBody.setPersonalizations(personalizations);
        sendRequest(emailBody);
    }

    private static void sendRequest(EmailBody emailBody) {

//        RetrofitClient.getInstance()
//                .getService("https://api.sendgrid.com/")
//                .sendEmail(emailBody)
//                .enqueue(new Callback<Object>() {
//                    @Override
//                    public void onResponse(Call<Object> call, Response<Object> response) {
//                        //System.out.println("Success request : code = " + response.code());
//                    }
//
//                    @Override
//                    public void onFailure(Call<Object> call, Throwable throwable) {
//                        //System.out.println("Fail request : message = " + throwable.getMessage());
//                    }
//                });
    }

    public static void sendUser(String sendName, String messageCategory, String messageType, String body, String email) {
        String subject = "Question From" + sendName;

        EmailBody emailBody = new EmailBody();
        // create content list
        List<EmailBody.Content> contentList = new ArrayList<EmailBody.Content>();
        EmailBody.Content content = new EmailBody.Content();
        content.setValue(body + "\n\n\n Sent for Category ՝ "
                + messageCategory + " \nType of the message " + messageType);
        content.setType("text/plain");
        contentList.add(content);
        // add content list to body
        emailBody.setContent(contentList);
        // add from data to body
        emailBody.setFrom(new EmailBody.From(email));
        // add send information to body
        List<EmailBody.Personalization> personalizations = new ArrayList<EmailBody.Personalization>();
        EmailBody.Personalization personalization = new EmailBody.Personalization();
        personalization.setSubject(subject);
        List<EmailBody.To> toList = new ArrayList<EmailBody.To>();
        toList.add(new EmailBody.To(Constances.USERNAME));
        personalization.setTo(toList);
        personalizations.add(personalization);
        emailBody.setPersonalizations(personalizations);
        sendRequest(emailBody);
    }

    public static void sendNewCompany(String companyName, HttpServletResponse response) throws IOException {

        String subject = "New com.fertifa.app.Company registered";
        EmailBody emailBody = new EmailBody();
        // create content list
        List<EmailBody.Content> contentList = new ArrayList<EmailBody.Content>();
        EmailBody.Content content = new EmailBody.Content();
        content.setValue("\n\n\n Sent for Web email admin ՝ " +
                " \n you have new com.fertifa.app.Company registered, The name of the company " + companyName);
        content.setType("text/plain");
        contentList.add(content);
        // add content list to body
        emailBody.setContent(contentList);
        // add from data to body
        emailBody.setFrom(new EmailBody.From(Constances.USERNAME));
        // add send information to body
        List<EmailBody.Personalization> personalizations = new ArrayList<EmailBody.Personalization>();
        EmailBody.Personalization personalization = new EmailBody.Personalization();
        personalization.setSubject(subject);
        List<EmailBody.To> toList = new ArrayList<EmailBody.To>();
        toList.add(new EmailBody.To(Constances.USERNAME));
        personalization.setTo(toList);
        personalizations.add(personalization);
        emailBody.setPersonalizations(personalizations);
        sendRequest(emailBody);
    }


    public static void sendNewUser(String userFirstName, String userLastName, String companyName) {
        String subject = "New com.fertifa.app.Company registered";
        EmailBody emailBody = new EmailBody();
        // create content list
        List<EmailBody.Content> contentList = new ArrayList<EmailBody.Content>();
        EmailBody.Content content = new EmailBody.Content();
        content.setValue("\n\n\n Sent for Web email admin ՝ " +
                " \n The user" + userFirstName + " " + userLastName + " from " + companyName + " registered");
        content.setType("text/plain");
        contentList.add(content);
        // add content list to body
        emailBody.setContent(contentList);
        // add from data to body
        emailBody.setFrom(new EmailBody.From(Constances.USERNAME));
        // add send information to body
        List<EmailBody.Personalization> personalizations = new ArrayList<EmailBody.Personalization>();
        EmailBody.Personalization personalization = new EmailBody.Personalization();
        personalization.setSubject(subject);
        List<EmailBody.To> toList = new ArrayList<EmailBody.To>();
        toList.add(new EmailBody.To(Constances.USERNAME));
        personalization.setTo(toList);
        personalizations.add(personalization);
        emailBody.setPersonalizations(personalizations);
        sendRequest(emailBody);
    }

    public static void sendFeedBack(String userFirstName, String userLastName, String ratingStars, String subject, String Detail) {

        EmailBody emailBody = new EmailBody();
        // create content list
        List<EmailBody.Content> contentList = new ArrayList<EmailBody.Content>();
        EmailBody.Content content = new EmailBody.Content();
        content.setValue("Email from " + userFirstName + " " + userLastName + "\n\n" + ratingStars + " \n\n" + Detail);
        content.setType("text/plain");
        contentList.add(content);
        // add content list to body
        emailBody.setContent(contentList);
        // add from data to body
        emailBody.setFrom(new EmailBody.From(Constances.USERNAME));
        // add send information to body
        List<EmailBody.Personalization> personalizations = new ArrayList<EmailBody.Personalization>();
        EmailBody.Personalization personalization = new EmailBody.Personalization();
        personalization.setSubject(subject);
        List<EmailBody.To> toList = new ArrayList<EmailBody.To>();
        toList.add(new EmailBody.To(Constances.USERNAME));
        personalization.setTo(toList);
        personalizations.add(personalization);
        emailBody.setPersonalizations(personalizations);
        sendRequest(emailBody);
    }
}
