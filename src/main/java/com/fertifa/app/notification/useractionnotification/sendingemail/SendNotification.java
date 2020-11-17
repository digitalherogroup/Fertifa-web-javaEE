package com.fertifa.app.notification.useractionnotification.sendingemail;


import com.fertifa.app.constants.Constances;
import com.fertifa.app.models.Users;
import com.fertifa.app.network.EmailBody;
import com.fertifa.app.network.EmailBody.Content;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SendNotification {

    public static void sendNotificationToUser(String message, Users users, String subject) throws IOException, ServletException {
        System.out.println("sendNotificationToUser" );
        String sub = null;
        sub = subject;
        System.out.println("subject " + subject);
        EmailBody emailBody = new EmailBody();
        // create content list
        List<Content> contentList = new ArrayList<Content>();
        EmailBody.Content content = new EmailBody.Content();
        content.setValue(message);
        System.out.println("message " + message);
        content.setType("text/html");
        contentList.add(content);
        // add content list to body
        emailBody.setContent(contentList);
        // add from data to body
        emailBody.setFrom(new EmailBody.From(Constances.USERNAME));
        System.out.println("EmailBody.From(Constances.USERNAME) " + Constances.USERNAME);
        // add send information to body
        List<EmailBody.Personalization> personalizations = new ArrayList<EmailBody.Personalization>();
        EmailBody.Personalization personalization = new EmailBody.Personalization();
        personalization.setSubject(sub);
        List<EmailBody.To> toList = new ArrayList<EmailBody.To>();
        toList.add(new EmailBody.To(users.getEmail()));
        System.out.println("users.getEmail() " + users.getEmail());
        personalization.setTo(toList);
        personalizations.add(personalization);
        emailBody.setPersonalizations(personalizations);
        // init request

        sendRequest(emailBody);

    }

    public static void sendNotificationAdmin(String message, String subject, int masterAdminsCount) throws IOException, ServletException {
        System.out.println("sendNotificationAdmin" );
        String sub = null;
        sub = subject;
        System.out.println("subject " + subject);
        EmailBody emailBody = new EmailBody();
        // create content list
        List<EmailBody.Content> contentList = new ArrayList<EmailBody.Content>();
        EmailBody.Content content = new EmailBody.Content();
        content.setValue(message);
        System.out.println("message " + message);
        content.setType("text/html");
        contentList.add(content);
        // add content list to body
        emailBody.setContent(contentList);
        // add from data to body
        emailBody.setFrom(new EmailBody.From(Constances.USERNAME));
        System.out.println("EmailBody.From(Constances.USERNAME) " + Constances.USERNAME);
        // add send information to body
        List<EmailBody.Personalization> personalizations = new ArrayList<EmailBody.Personalization>();
        EmailBody.Personalization personalization = new EmailBody.Personalization();
        personalization.setSubject(sub);
        List<EmailBody.To> toList = new ArrayList<EmailBody.To>();
        if (masterAdminsCount == 2) {

                //toList.add(new EmailBody.To("info@fertifa.com"));
            toList.add(new EmailBody.To("skhayalian@gmail.com"));
                //toList.add(new EmailBody.To("info@fertifa.com"));
                personalization.setTo(toList);
                personalizations.add(personalization);
                emailBody.setPersonalizations(personalizations);
                sendRequest(emailBody);


        } else {

            //toList.add(new EmailBody.To("info@fertifa.com"));
            toList.add(new EmailBody.To("skhayalian@gmail.com"));
            personalization.setTo(toList);
            personalizations.add(personalization);
            emailBody.setPersonalizations(personalizations);
            sendRequest(emailBody);
        }
        // init request
    }

    private static void sendRequest(EmailBody emailBody) {
       // SendingEmailNewUser.RetrofitResponse(emailBody);
    }
}
