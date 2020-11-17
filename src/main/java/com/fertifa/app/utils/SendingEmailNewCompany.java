package com.fertifa.app.utils;

import com.fertifa.app.network.EmailBody;
import com.fertifa.app.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SendingEmailNewCompany {

    static File file = new File("com/fertifa/app/utils/invitation-email.html");
    private static String emailTemplate = null;

    public static void send(String Token, String CompanyName, String CompanyEmail, HttpServletRequest request) {

        String subject = "Welcome to Fertifa";
        EmailBody emailBody = new EmailBody();
        // create content list
        List<EmailBody.Content> contentList = new ArrayList<EmailBody.Content>();
        EmailBody.Content content = new EmailBody.Content();
        content.setValue(EmailConstants.getInvitationToEmployerBody(Token, CompanyName));
        content.setType("text/html");
        contentList.add(content);
        // add content list to body
        emailBody.setContent(contentList);
        // add from data to body
        emailBody.setFrom(new EmailBody.From("no-reply@fertifa.com"));
        // add send information to body
        List<EmailBody.Personalization> personalizations = new ArrayList<EmailBody.Personalization>();
        EmailBody.Personalization personalization = new EmailBody.Personalization();
        personalization.setSubject(subject);
        List<EmailBody.To> toList = new ArrayList<EmailBody.To>();
        toList.add(new EmailBody.To(CompanyEmail));
        personalization.setTo(toList);
        personalizations.add(personalization);
        emailBody.setPersonalizations(personalizations);
        // send email

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


    public static void sendRecoveryEmail(String emailForPasswordRecovery, HttpServletRequest request) {
        String subject = "Reset your Fertifa account password";
        EmailBody emailBody = new EmailBody();
        // create content list
        List<EmailBody.Content> contentList = new ArrayList<EmailBody.Content>();
        EmailBody.Content content = new EmailBody.Content();
        content.setValue(EmailConstants.getForgotPasswordEmailBody(emailForPasswordRecovery));
        content.setType("text/html");
        contentList.add(content);
        // add content list to body
        emailBody.setContent(contentList);
        // add from data to body
        emailBody.setFrom(new EmailBody.From("no-reply@fertifa.com"));
        // add send information to body
        List<EmailBody.Personalization> personalizations = new ArrayList<EmailBody.Personalization>();
        EmailBody.Personalization personalization = new EmailBody.Personalization();
        personalization.setSubject(subject);
        List<EmailBody.To> toList = new ArrayList<EmailBody.To>();
        toList.add(new EmailBody.To(emailForPasswordRecovery));
        personalization.setTo(toList);
        personalizations.add(personalization);
        emailBody.setPersonalizations(personalizations);
        // send email

        sendRequest(emailBody);
    }

    public static void sendCodeUsers(String tokenCode, String userFirstName, String userLastName, String userEmail, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String subject = "Fertifa Verification code";
        EmailBody emailBody = new EmailBody();
        // create content list
        List<EmailBody.Content> contentList = new ArrayList<EmailBody.Content>();
        EmailBody.Content content = new EmailBody.Content();
        content.setValue(EmailConstants.getSendCodeBody(userFirstName, userLastName, tokenCode));
        content.setType("text/html");
        contentList.add(content);
        // add content list to body
        emailBody.setContent(contentList);
        // add from data to body
        emailBody.setFrom(new EmailBody.From("no-reply@fertifa.com"));
        // add send information to body
        List<EmailBody.Personalization> personalizations = new ArrayList<EmailBody.Personalization>();
        EmailBody.Personalization personalization = new EmailBody.Personalization();
        personalization.setSubject(subject);
        List<EmailBody.To> toList = new ArrayList<EmailBody.To>();
        toList.add(new EmailBody.To(userEmail));
        personalization.setTo(toList);
        personalizations.add(personalization);
        emailBody.setPersonalizations(personalizations);
        // send email
        sendRequest(emailBody);
    }

    public static void sendCodeCompanies(String tokenCode, String userFirstName, String userLastName, String userEmail, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String subject = "Fertifa Verification code";
        EmailBody emailBody = new EmailBody();
        // create content list
        List<EmailBody.Content> contentList = new ArrayList<EmailBody.Content>();
        EmailBody.Content content = new EmailBody.Content();
        content.setValue(EmailConstants.getSendCodeBody(userFirstName, userLastName, tokenCode));
        content.setType("text/html");
        contentList.add(content);
        // add content list to body
        emailBody.setContent(contentList);
        // add from data to body
        emailBody.setFrom(new EmailBody.From("no-reply@fertifa.com"));
        // add send information to body
        List<EmailBody.Personalization> personalizations = new ArrayList<EmailBody.Personalization>();
        EmailBody.Personalization personalization = new EmailBody.Personalization();
        personalization.setSubject(subject);
        List<EmailBody.To> toList = new ArrayList<EmailBody.To>();
        toList.add(new EmailBody.To(userEmail));
        personalization.setTo(toList);
        personalizations.add(personalization);
        emailBody.setPersonalizations(personalizations);
        // send email
        sendRequest(emailBody);
    }
}



