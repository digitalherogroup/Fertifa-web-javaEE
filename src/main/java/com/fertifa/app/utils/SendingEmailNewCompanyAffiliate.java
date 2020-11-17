package com.fertifa.app.utils;

import com.fertifa.app.models.Users;
import com.fertifa.app.network.EmailBody;
import com.fertifa.app.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SendingEmailNewCompanyAffiliate {
    static File file = new File("com/fertifa/app/utils/invitation-email.html");
    private static String emailTemplate = null;

    public static void send(String Token, Users users, int affiliateId, HttpServletRequest request) {

        String subject = "Welcome to Fertifa";
        EmailBody emailBody = new EmailBody();
        // create content list
        List<EmailBody.Content> contentList = new ArrayList<EmailBody.Content>();
        EmailBody.Content content = new EmailBody.Content();
        content.setValue(EmailConstants.getInvitationToEmployerAffiliate(Token,users.getId(), users.getComapny(),affiliateId));
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
        toList.add(new EmailBody.To(users.getEmail()));
        personalization.setTo(toList);
        personalizations.add(personalization);
        emailBody.setPersonalizations(personalizations);
        // send email

        sendRequest(emailBody);
    }

    private static void sendRequest(EmailBody emailBody) {
//
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
}
