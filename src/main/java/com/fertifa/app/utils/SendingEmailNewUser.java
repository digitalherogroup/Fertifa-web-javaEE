package com.fertifa.app.utils;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.models.Users;
import com.fertifa.app.network.EmailBody;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SendingEmailNewUser {

    static File file = new File("com/fertifa/app/utils/invitation-email.html");
    private static String emailTemplate = null;

    public static void sendMuliUser(List<Users> usersList, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException {

        for (int i = 0; i < usersList.size(); i++) {
            new EmailSendThread(usersList.get(i), request, response).start();
        }
    }


    public static void sendUser(String Token, String firstName, String lastName, String CompanyEmail, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String subject = "Welcome to Fertifa";

        EmailBody emailBody = new EmailBody();
        // create content list
        List<EmailBody.Content> contentList = new ArrayList<EmailBody.Content>();
        EmailBody.Content content = new EmailBody.Content();
        content.setValue(EmailConstants.getSingleInvitationBody(firstName, lastName, Token));
        content.setType("text/html");
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
        toList.add(new EmailBody.To(CompanyEmail));
        personalization.setTo(toList);
        personalizations.add(personalization);
        emailBody.setPersonalizations(personalizations);
        // init request

        sendRequest(emailBody);
    }

    public void sendInvoice(String invoiceUrl, String... emails) {
        for (int i = 0; i < emails.length; i++) {
            String subject = "Invoice from Fertifa";
            EmailBody emailBody = new EmailBody();
            // create content list
            List<EmailBody.Content> contentList = new ArrayList<EmailBody.Content>();
            EmailBody.Content content = new EmailBody.Content();
            content.setValue("Invoice url " + invoiceUrl);
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
            toList.add(new EmailBody.To(emails[i]));
            personalization.setTo(toList);
            personalizations.add(personalization);
            emailBody.setPersonalizations(personalizations);
            // init request
            sendRequest(emailBody);
        }
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


    private static class EmailSendThread extends Thread {
        private Users users;
        private HttpServletRequest request;
        private HttpServletResponse response;

        public EmailSendThread(Users users, HttpServletRequest request, HttpServletResponse response) {
            this.request = request;
            this.response = response;
            this.users = users;
        }

        @Override
        public void run() {
            try {
                sendMuliUser();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }


        public void sendMuliUser() throws ServletException, IOException, MessagingException {

            System.out.println("EMAIL ---->>>>>>>>   " + users.getEmail());
            String subject = "Welcome to Fertifa";
            EmailBody emailBody = new EmailBody();
            // create content list
            List<EmailBody.Content> contentList = new ArrayList<EmailBody.Content>();
            EmailBody.Content content = new EmailBody.Content();
            content.setValue(EmailConstants.getMultiInvitationBody(users));
            content.setType("text/html");
            contentList.add(content);
            // add content list to body
            emailBody.setContent(contentList);
            // add from data to bodyÏ
            emailBody.setFrom(new EmailBody.From(Constances.USERNAME));
            // add send information to body
            List<EmailBody.Personalization> personalizations = new ArrayList<EmailBody.Personalization>();
            EmailBody.Personalization personalization = new EmailBody.Personalization();
            personalization.setSubject(subject);
            List<EmailBody.To> toList = new ArrayList<EmailBody.To>();
            toList.add(new EmailBody.To(users.getEmail()));
            personalization.setTo(toList);
            personalizations.add(personalization);
            emailBody.setPersonalizations(personalizations);

            // send grid request
            sendRequest(emailBody);
        }

        private void sendRequest(EmailBody emailBody) {

//            RetrofitClient.getInstance()
//                    .getService("https://api.sendgrid.com/")
//                    .sendEmail(emailBody)
//                    .enqueue(new Callback<Object>() {
//                        @Override
//                        public void onResponse(Call<Object> call, Response<Object> response) {
//                            //System.out.println("Success request : code = " + response.code());
//                        }
//
//                        @Override
//                        public void onFailure(Call<Object> call, Throwable throwable) {
//                            //System.out.println("Fail request : message = " + throwable.getMessage());
//                        }
//                    });
        }
    }
}



