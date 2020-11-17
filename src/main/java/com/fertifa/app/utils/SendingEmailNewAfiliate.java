package com.fertifa.app.utils;

import com.fertifa.app.constants.Constances;
import com.fertifa.app.models.Affiliate;
import com.fertifa.app.network.EmailBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SendingEmailNewAfiliate {

    public static boolean sendAffiliate(String Token, Affiliate affiliate, int theAffiliateId, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String subject = "Welcome to Fertifa";
        EmailBody emailBody = new EmailBody();
        // create content list
        List<EmailBody.Content> contentList = new ArrayList<EmailBody.Content>();
        EmailBody.Content content = new EmailBody.Content();
        content.setValue(EmailConstants.getSingleInvitationBodyAffiliate(affiliate, theAffiliateId, Token));
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
        toList.add(new EmailBody.To(affiliate.getEmail()));
        personalization.setTo(toList);
        personalizations.add(personalization);
        emailBody.setPersonalizations(personalizations);
        // init request

        if (sendRequest(emailBody) > 0) {
            return false;
        }
        return true;
    }

    private static int sendRequest(EmailBody emailBody) {
//        final int[] isSent = {0};
//        RetrofitClient.getInstance()
//                .getService("https://api.sendgrid.com/")
//                .sendEmail(emailBody)
//                .enqueue(new Callback<Object>() {
//                    @Override
//                    public void onResponse(Call<Object> call, Response<Object> response) {
//                        isSent[0] = 1;
//                    }
//
//                    @Override
//                    public void onFailure(Call<Object> call, Throwable throwable) {
//                        isSent[0] = 0;
//                    }
//                });
//        return isSent[0];

        //  }
        return 0;
    }
}
