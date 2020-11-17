package com.fertifa.app.utils;

import com.fertifa.app.models.Affiliate;
import com.fertifa.app.models.Users;

public class EmailConstants {
    public static String getMultiInvitationBody(Users users) {
        String finalText = "<!doctype html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <title>Simple Transactional Email</title>\n" +
                "    <style>\n" +
                "    /* -------------------------------------\n" +
                "        INLINED WITH htmlemail.io/inline\n" +
                "    ------------------------------------- */\n" +
                "    /* -------------------------------------\n" +
                "        RESPONSIVE AND MOBILE FRIENDLY STYLES\n" +
                "    ------------------------------------- */\n" +
                "    @media only screen and (max-width: 620px) {\n" +
                "      table[class=body] h1 {\n" +
                "        font-size: 28px !important;\n" +
                "        margin-bottom: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] p,\n" +
                "            table[class=body] ul,\n" +
                "            table[class=body] ol,\n" +
                "            table[class=body] td,\n" +
                "            table[class=body] span,\n" +
                "            table[class=body] a {\n" +
                "        font-size: 16px !important;\n" +
                "      }\n" +
                "      table[class=body] .wrapper,\n" +
                "            table[class=body] .article {\n" +
                "        padding: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] .content {\n" +
                "        padding: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .container {\n" +
                "        padding: 0 !important;\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .main {\n" +
                "        border-left-width: 0 !important;\n" +
                "        border-radius: 0 !important;\n" +
                "        border-right-width: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .btn table {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .btn a {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .img-responsive {\n" +
                "        height: auto !important;\n" +
                "        max-width: 100% !important;\n" +
                "        width: auto !important;\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    /* -------------------------------------\n" +
                "        PRESERVE THESE STYLES IN THE HEAD\n" +
                "    ------------------------------------- */\n" +
                "    @media all {\n" +
                "      .ExternalClass {\n" +
                "        width: 100%;\n" +
                "      }\n" +
                "      .ExternalClass,\n" +
                "            .ExternalClass p,\n" +
                "            .ExternalClass span,\n" +
                "            .ExternalClass font,\n" +
                "            .ExternalClass td,\n" +
                "            .ExternalClass div {\n" +
                "        line-height: 100%;\n" +
                "      }\n" +
                "      .apple-link a {\n" +
                "        color: inherit !important;\n" +
                "        font-family: inherit !important;\n" +
                "        font-size: inherit !important;\n" +
                "        font-weight: inherit !important;\n" +
                "        line-height: inherit !important;\n" +
                "        text-decoration: none !important;\n" +
                "      }\n" +
                "      #MessageViewBody a {\n" +
                "        color: inherit;\n" +
                "        text-decoration: none;\n" +
                "        font-size: inherit;\n" +
                "        font-family: inherit;\n" +
                "        font-weight: inherit;\n" +
                "        line-height: inherit;\n" +
                "      }\n table td:hover {background-color:transparent !important}" +
                "    }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body class=\"\" style=\"background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\">\n" +
                "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background-color: #f6f6f6;\">\n" +
                "      <tr>\n" +
                "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "        <td class=\"container\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; Margin: 0 auto; max-width: 580px; padding: 10px; width: 580px;\">\n" +
                "          <div class=\"content\" style=\"box-sizing: border-box; display: block; Margin: 0 auto; max-width: 580px; padding: 10px;\">\n" +
                "\n" +
                "            <!-- START CENTERED WHITE CONTAINER -->\n" +
                "            <table class=\"main\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background: #ffffff; border-radius: 3px;\">\n" +
                "\n" +
                "              <!-- START MAIN CONTENT AREA -->\n" +
                "              <tr>\n" +
                "                <td class=\"wrapper\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "                    <tr>\n" +
                "                      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Dear " + users.getFirstName() + " " + users.getLastName() + "</p>\n" +
                "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; box-sizing: border-box;\">\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td align=\"left\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;\">\n" +
                "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;\">\n" +
                "                                  <tbody>\n" +
                "                                                                     <tr>\n" +
                "                                                                       <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; background-color: #3498db; border-radius: 5px; text-align: center;\">\n" +
                "                                                                        <a href=\"http://second.fertifabenefits.com/confirms.jsp?tokenid=" + users.getToken() + "&first=" + users.getFirstName() + "&last=" + users.getLastName() + "\" target=\"_blank\" style=\"display: inline-block; color: #ffffff;\n" +
                "background-color: #3498db;\n" +
                "border: solid 1px #3498db;\n" +
                "                                                                        border-radius: 5px;\n" +
                "                                                                        box-sizing:\n" +
                "                                                                        border-box;\n" +
                "                                                                        cursor: pointer;\n" +
                "                                                                        text-decoration: none;\n" +
                "                                                                        font-size: 14px; font-weight: bold; margin: 0;\n" +
                "                                                                        padding: 12px 25px; text-transform: capitalize;\n" +
                "                                                                        border-color: #3498db;\">Get started now</a> </td>\n" +
                "                                                                     </tr>\n" +
                "                                                                   </tbody>\n" +
                "\n" +
                "                        </table>\n" +
                "                        <br />\n " +
                "                        <br />\n " +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Welcome to Fertifa! We understand how hard it is to reach out to anyone regarding personal fertility issues, so we commend and thank you for allowing us to support you through your fertility journey!</p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">The Fertifa team was established as a result of own personal fertility experiences - the rollercoaster of emotions, relationship worries and financial pressures that go alongside the (sometimes desperate) need to start a family.  Regardless of where you are in that fertility journey, our mission at Fertifa is to empower you to take control of your fertility.  We will provide you with access to the best fertility practitioners, at top clinics, at the best price.  At the same time, we will provide guidance on your diet, lifestyle and emotional wellbeing so that you are in the best possible condition to bring home a healthy baby.</p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">The first step, once you've completed our Enquiry Form, is a short 15-minute consultation with one of our Fertility Advisors or our in-house Doctor.  We will gain a full picture of your journey so far in order that we can then help you design a bespoke fertility action plan.  The Fertility Advisor will point you in the right direction for the necessary medical expertise, treatment type and any wellbeing support you may require to succeed on your journey, for which there are a wide range of discounts available to you.</p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Fertifa can also help you to understand and be proactive with your fertility health:</p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">- For any tests you may have via Fertifa, we provide you with a comprehensive report to explain the results <br/>\n" +
                "- Our online fertility education material is accessible to all Fertifa members<br/>\n" +
                "- We share the latest fertility research to keep you updated on new developments<br/>\n" +
                "- We provide all the nutrition-based information you may need to get pregnant and maintain a healthy pregnancy<br/>\n" +
                "- Our Fertility Advisors are available 24/7 to support you with any questions during your journey<br/></p>\n" +
                " <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">We do hope you find Fertifa's services beneficial.  If you have any questions or suggestions, we would love to hear them.  You can contact us directly at info@fertifa.com or through our messaging tab in the portal.</p>\n" +
                " <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Thank you again for joining Fertifa!</p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "\n" +
                "            <!-- END MAIN CONTENT AREA -->\n" +
                "            </table>\n" +
                "\n" +
                "            <!-- START FOOTER -->\n" +
                "            <div class=\"footer\" style=\"clear: both; Margin-top: 10px; text-align: center; width: 100%;\">\n" +
                "              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "                <tr>\n" +
                "                  <td class=\"content-block\" style=\"font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;\">\n" +

                "                  </td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </div>\n" +
                "            <!-- END FOOTER -->\n" +
                "\n" +
                "          <!-- END CENTERED WHITE CONTAINER -->\n" +
                "          </div>\n" +
                "        </td>\n" +
                "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </body>\n" +
                "</html>\n";

        return finalText;
    }

    public static String getSingleInvitationBody(String firstName, String lastName, String Token) {
        String finalText = "<!doctype html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <title>Simple Transactional Email</title>\n" +
                "    <style>\n" +
                "    /* -------------------------------------\n" +
                "        INLINED WITH htmlemail.io/inline\n" +
                "    ------------------------------------- */\n" +
                "    /* -------------------------------------\n" +
                "        RESPONSIVE AND MOBILE FRIENDLY STYLES\n" +
                "    ------------------------------------- */\n" +
                "    @media only screen and (max-width: 620px) {\n" +
                "      table[class=body] h1 {\n" +
                "        font-size: 28px !important;\n" +
                "        margin-bottom: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] p,\n" +
                "            table[class=body] ul,\n" +
                "            table[class=body] ol,\n" +
                "            table[class=body] td,\n" +
                "            table[class=body] span,\n" +
                "            table[class=body] a {\n" +
                "        font-size: 16px !important;\n" +
                "      }\n" +
                "      table[class=body] .wrapper,\n" +
                "            table[class=body] .article {\n" +
                "        padding: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] .content {\n" +
                "        padding: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .container {\n" +
                "        padding: 0 !important;\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .main {\n" +
                "        border-left-width: 0 !important;\n" +
                "        border-radius: 0 !important;\n" +
                "        border-right-width: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .btn table {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .btn a {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .img-responsive {\n" +
                "        height: auto !important;\n" +
                "        max-width: 100% !important;\n" +
                "        width: auto !important;\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    /* -------------------------------------\n" +
                "        PRESERVE THESE STYLES IN THE HEAD\n" +
                "    ------------------------------------- */\n" +
                "    @media all {\n" +
                "      .ExternalClass {\n" +
                "        width: 100%;\n" +
                "      }\n" +
                "      .ExternalClass,\n" +
                "            .ExternalClass p,\n" +
                "            .ExternalClass span,\n" +
                "            .ExternalClass font,\n" +
                "            .ExternalClass td,\n" +
                "            .ExternalClass div {\n" +
                "        line-height: 100%;\n" +
                "      }\n" +
                "      .apple-link a {\n" +
                "        color: inherit !important;\n" +
                "        font-family: inherit !important;\n" +
                "        font-size: inherit !important;\n" +
                "        font-weight: inherit !important;\n" +
                "        line-height: inherit !important;\n" +
                "        text-decoration: none !important;\n" +
                "      }\n" +
                "      #MessageViewBody a {\n" +
                "        color: inherit;\n" +
                "        text-decoration: none;\n" +
                "        font-size: inherit;\n" +
                "        font-family: inherit;\n" +
                "        font-weight: inherit;\n" +
                "        line-height: inherit;\n" +
                "      }\n table td:hover {background-color:transparent !important}" +
                "    }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body class=\"\" style=\"background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\">\n" +
                "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background-color: #f6f6f6;\">\n" +
                "      <tr>\n" +
                "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "        <td class=\"container\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; Margin: 0 auto; max-width: 580px; padding: 10px; width: 580px;\">\n" +
                "          <div class=\"content\" style=\"box-sizing: border-box; display: block; Margin: 0 auto; max-width: 580px; padding: 10px;\">\n" +
                "\n" +
                "            <!-- START CENTERED WHITE CONTAINER -->\n" +
                "            <table class=\"main\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background: #ffffff; border-radius: 3px;\">\n" +
                "\n" +
                "              <!-- START MAIN CONTENT AREA -->\n" +
                "              <tr>\n" +
                "                <td class=\"wrapper\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "                    <tr>\n" +
                "                      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Dear " + firstName + " " + lastName + "</p>\n" +
                "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; box-sizing: border-box;\">\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td align=\"left\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;\">\n" +
                "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;\">\n" +
                "                                  <tbody>\n" +
                "                                                                     <tr>\n" +
                "                                                                       <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; background-color: #3498db; border-radius: 5px; text-align: center;\">\n" +
                "                                                                        <a href=\"http://second.fertifabenefits.com/confirms.jsp?tokenid=" + Token + "&first=" + firstName + "&last=" + lastName + "\" target=\"blank\" style=\"display: inline-block; color: #ffffff;\n" +
                "background-color: #3498db;\n" +
                "border: solid 1px #3498db;\n" +
                "                                                                        border-radius: 5px;\n" +
                "                                                                        box-sizing:\n" +
                "                                                                        border-box;\n" +
                "                                                                        cursor: pointer;\n" +
                "                                                                        text-decoration: none;\n" +
                "                                                                        font-size: 14px; font-weight: bold; margin: 0;\n" +
                "                                                                        padding: 12px 25px; text-transform: capitalize;\n" +
                "                                                                        border-color: #3498db;\">Get started now</a> </td>\n" +
                "                                                                     </tr>\n" +
                "                                                                   </tbody>\n" +
                "\n" +
                "                        </table>\n" +
                "                        <br />\n " +
                "                        <br />\n " +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Welcome to Fertifa! We understand how hard it is to reach out to anyone regarding personal fertility issues, so we commend and thank you for allowing us to support you through your fertility journey!</p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">The Fertifa team was established as a result of own personal fertility experiences - the rollercoaster of emotions, relationship worries and financial pressures that go alongside the (sometimes desperate) need to start a family.  Regardless of where you are in that fertility journey, our mission at Fertifa is to empower you to take control of your fertility.  We will provide you with access to the best fertility practitioners, at top clinics, at the best price.  At the same time, we will provide guidance on your diet, lifestyle and emotional wellbeing so that you are in the best possible condition to bring home a healthy baby.</p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">The first step, once you've completed our Enquiry Form, is a short 15-minute consultation with one of our Fertility Advisors or our in-house Doctor.  We will gain a full picture of your journey so far in order that we can then help you design a bespoke fertility action plan.  The Fertility Advisor will point you in the right direction for the necessary medical expertise, treatment type and any wellbeing support you may require to succeed on your journey, for which there are a wide range of discounts available to you.</p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Fertifa can also help you to understand and be proactive with your fertility health:</p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">- For any tests you may have via Fertifa, we provide you with a comprehensive report to explain the results <br/>\n" +
                "- Our online fertility education material is accessible to all Fertifa members<br/>\n" +
                "- We share the latest fertility research to keep you updated on new developments<br/>\n" +
                "- We provide all the nutrition-based information you may need to get pregnant and maintain a healthy pregnancy<br/>\n" +
                "- Our Fertility Advisors are available 24/7 to support you with any questions during your journey<br/></p>\n" +
                " <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">We do hope you find Fertifa's services beneficial.  If you have any questions or suggestions, we would love to hear them.  You can contact us directly at info@fertifa.com or through our messaging tab in the portal.</p>\n" +
                " <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Thank you again for joining Fertifa!</p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "\n" +
                "            <!-- END MAIN CONTENT AREA -->\n" +
                "            </table>\n" +
                "\n" +
                "            <!-- START FOOTER -->\n" +
                "            <div class=\"footer\" style=\"clear: both; Margin-top: 10px; text-align: center; width: 100%;\">\n" +
                "              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "                <tr>\n" +
                "                  <td class=\"content-block\" style=\"font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;\">\n" +

                "                  </td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </div>\n" +
                "            <!-- END FOOTER -->\n" +
                "\n" +
                "          <!-- END CENTERED WHITE CONTAINER -->\n" +
                "          </div>\n" +
                "        </td>\n" +
                "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </body>\n" +
                "</html>\n";
        return finalText;
    }

    public static String getInvitationToEmployerAffiliate(String Token, int id, String CompanyName, int affiliateId) {
        String finalText = "<!doctype html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <title>Simple Transactional Email</title>\n" +
                "    <style>\n" +
                "    /* -------------------------------------\n" +
                "        INLINED WITH htmlemail.io/inline\n" +
                "    ------------------------------------- */\n" +
                "    /* -------------------------------------\n" +
                "        RESPONSIVE AND MOBILE FRIENDLY STYLES\n" +
                "    ------------------------------------- */\n" +
                "    @media only screen and (max-width: 620px) {\n" +
                "      table[class=body] h1 {\n" +
                "        font-size: 28px !important;\n" +
                "        margin-bottom: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] p,\n" +
                "            table[class=body] ul,\n" +
                "            table[class=body] ol,\n" +
                "            table[class=body] td,\n" +
                "            table[class=body] span,\n" +
                "            table[class=body] a {\n" +
                "        font-size: 16px !important;\n" +
                "      }\n" +
                "      table[class=body] .wrapper,\n" +
                "            table[class=body] .article {\n" +
                "        padding: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] .content {\n" +
                "        padding: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .container {\n" +
                "        padding: 0 !important;\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .main {\n" +
                "        border-left-width: 0 !important;\n" +
                "        border-radius: 0 !important;\n" +
                "        border-right-width: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .btn table {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .btn a {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .img-responsive {\n" +
                "        height: auto !important;\n" +
                "        max-width: 100% !important;\n" +
                "        width: auto !important;\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    /* -------------------------------------\n" +
                "        PRESERVE THESE STYLES IN THE HEAD\n" +
                "    ------------------------------------- */\n" +
                "    @media all {\n" +
                "      .ExternalClass {\n" +
                "        width: 100%;\n" +
                "      }\n" +
                "      .ExternalClass,\n" +
                "            .ExternalClass p,\n" +
                "            .ExternalClass span,\n" +
                "            .ExternalClass font,\n" +
                "            .ExternalClass td,\n" +
                "            .ExternalClass div {\n" +
                "        line-height: 100%;\n" +
                "      }\n" +
                "      .apple-link a {\n" +
                "        color: inherit !important;\n" +
                "        font-family: inherit !important;\n" +
                "        font-size: inherit !important;\n" +
                "        font-weight: inherit !important;\n" +
                "        line-height: inherit !important;\n" +
                "        text-decoration: none !important;\n" +
                "      }\n" +
                "      #MessageViewBody a {\n" +
                "        color: inherit;\n" +
                "        text-decoration: none;\n" +
                "        font-size: inherit;\n" +
                "        font-family: inherit;\n" +
                "        font-weight: inherit;\n" +
                "        line-height: inherit;\n" +
                "      }\n" +
                "      .btn-primary table td:hover {\n" +
                "        background-color: #34495e !important;\n" +
                "      }\n" +
                "      .btn-primary a:hover {\n" +
                "        background-color: #34495e !important;\n" +
                "        border-color: #34495e !important;\n" +
                "      }\n" +
                "    }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body class=\"\" style=\"background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\">\n" +
                "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background-color: #f6f6f6;\">\n" +
                "      <tr>\n" +
                "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "        <td class=\"container\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; Margin: 0 auto; max-width: 580px; padding: 10px; width: 580px;\">\n" +
                "          <div class=\"content\" style=\"box-sizing: border-box; display: block; Margin: 0 auto; max-width: 580px; padding: 10px;\">\n" +
                "\n" +
                "            <!-- START CENTERED WHITE CONTAINER -->\n" +
                "            <table class=\"main\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background: #ffffff; border-radius: 3px;\">\n" +
                "\n" +
                "              <!-- START MAIN CONTENT AREA -->\n" +
                "              <tr>\n" +
                "                <td class=\"wrapper\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "                    <tr>\n" +
                "                      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Hello, <br /></p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Thank you for signing " + CompanyName + " up to Fertifa. Click below to get started and do send a message to info@fertifa.com if you have any issues logging in.<br/></p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Kind regards,</p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">The Fertifa Team</p>\n" +
                "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; box-sizing: border-box;\">\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td align=\"left\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;\">\n" +
                "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;\">\n" +
                "                                  <tbody>\n" +
                "                                                                     <tr>\n" +
                "                                                                       <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; background-color: #3498db; border-radius: 5px; text-align: center;\">\n" +
                "                                                                        <a href=\"http://second.fertifabenefits.com/confirmAf.jsp?tokenid=" + Token + "&Id=" + id + "&AffiliateId=" + affiliateId + "&com.fertifa.app.Company=" + CompanyName + "\" target=\"_blank\" style=\"display: inline-block; color: #ffffff;\n" +
                "background-color: #3498db;\n" +
                "border: solid 1px #3498db;\n" +
                "                                                                        border-radius: 5px;\n" +
                "                                                                        box-sizing:\n" +
                "                                                                        border-box;\n" +
                "                                                                        cursor: pointer;\n" +
                "                                                                        text-decoration: none;\n" +
                "                                                                        font-size: 14px; font-weight: bold; margin: 0;\n" +
                "                                                                        padding: 12px 25px; text-transform: capitalize;\n" +
                "                                                                        border-color: #3498db;\">Register</a> </td>\n" +
                "                                                                     </tr>\n" +
                "                                                                   </tbody>\n" +
                "\n" +
                "                        </table>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "\n" +
                "            <!-- END MAIN CONTENT AREA -->\n" +
                "            </table>\n" +
                "\n" +
                "            <!-- START FOOTER -->\n" +
                "            <div class=\"footer\" style=\"clear: both; Margin-top: 10px; text-align: center; width: 100%;\">\n" +
                "              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "                <tr>\n" +
                "                  <td class=\"content-block\" style=\"font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;\">\n" +

                "                  </td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </div>\n" +
                "            <!-- END FOOTER -->\n" +
                "\n" +
                "          <!-- END CENTERED WHITE CONTAINER -->\n" +
                "          </div>\n" +
                "        </td>\n" +
                "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </body>\n" +
                "</html>\n";
        return finalText;
    }

    public static String getInvitationToEmployerBody(String Token, String CompanyName) {
        String finalText = "<!doctype html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <title>Simple Transactional Email</title>\n" +
                "    <style>\n" +
                "    /* -------------------------------------\n" +
                "        INLINED WITH htmlemail.io/inline\n" +
                "    ------------------------------------- */\n" +
                "    /* -------------------------------------\n" +
                "        RESPONSIVE AND MOBILE FRIENDLY STYLES\n" +
                "    ------------------------------------- */\n" +
                "    @media only screen and (max-width: 620px) {\n" +
                "      table[class=body] h1 {\n" +
                "        font-size: 28px !important;\n" +
                "        margin-bottom: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] p,\n" +
                "            table[class=body] ul,\n" +
                "            table[class=body] ol,\n" +
                "            table[class=body] td,\n" +
                "            table[class=body] span,\n" +
                "            table[class=body] a {\n" +
                "        font-size: 16px !important;\n" +
                "      }\n" +
                "      table[class=body] .wrapper,\n" +
                "            table[class=body] .article {\n" +
                "        padding: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] .content {\n" +
                "        padding: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .container {\n" +
                "        padding: 0 !important;\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .main {\n" +
                "        border-left-width: 0 !important;\n" +
                "        border-radius: 0 !important;\n" +
                "        border-right-width: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .btn table {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .btn a {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .img-responsive {\n" +
                "        height: auto !important;\n" +
                "        max-width: 100% !important;\n" +
                "        width: auto !important;\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    /* -------------------------------------\n" +
                "        PRESERVE THESE STYLES IN THE HEAD\n" +
                "    ------------------------------------- */\n" +
                "    @media all {\n" +
                "      .ExternalClass {\n" +
                "        width: 100%;\n" +
                "      }\n" +
                "      .ExternalClass,\n" +
                "            .ExternalClass p,\n" +
                "            .ExternalClass span,\n" +
                "            .ExternalClass font,\n" +
                "            .ExternalClass td,\n" +
                "            .ExternalClass div {\n" +
                "        line-height: 100%;\n" +
                "      }\n" +
                "      .apple-link a {\n" +
                "        color: inherit !important;\n" +
                "        font-family: inherit !important;\n" +
                "        font-size: inherit !important;\n" +
                "        font-weight: inherit !important;\n" +
                "        line-height: inherit !important;\n" +
                "        text-decoration: none !important;\n" +
                "      }\n" +
                "      #MessageViewBody a {\n" +
                "        color: inherit;\n" +
                "        text-decoration: none;\n" +
                "        font-size: inherit;\n" +
                "        font-family: inherit;\n" +
                "        font-weight: inherit;\n" +
                "        line-height: inherit;\n" +
                "      }\n" +
                "      .btn-primary table td:hover {\n" +
                "        background-color: #34495e !important;\n" +
                "      }\n" +
                "      .btn-primary a:hover {\n" +
                "        background-color: #34495e !important;\n" +
                "        border-color: #34495e !important;\n" +
                "      }\n" +
                "    }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body class=\"\" style=\"background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\">\n" +
                "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background-color: #f6f6f6;\">\n" +
                "      <tr>\n" +
                "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "        <td class=\"container\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; Margin: 0 auto; max-width: 580px; padding: 10px; width: 580px;\">\n" +
                "          <div class=\"content\" style=\"box-sizing: border-box; display: block; Margin: 0 auto; max-width: 580px; padding: 10px;\">\n" +
                "\n" +
                "            <!-- START CENTERED WHITE CONTAINER -->\n" +
                "            <table class=\"main\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background: #ffffff; border-radius: 3px;\">\n" +
                "\n" +
                "              <!-- START MAIN CONTENT AREA -->\n" +
                "              <tr>\n" +
                "                <td class=\"wrapper\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "                    <tr>\n" +
                "                      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Hello, <br /></p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Thank you for signing " + CompanyName + " up to Fertifa. Click below to get started and do send a message to info@fertifa.com if you have any issues logging in.<br/></p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Kind regards,</p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">The Fertifa Team</p>\n" +
                "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; box-sizing: border-box;\">\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td align=\"left\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;\">\n" +
                "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;\">\n" +
                "                                  <tbody>\n" +
                "                                                                     <tr>\n" +
                "                                                                       <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; background-color: #3498db; border-radius: 5px; text-align: center;\">\n" +
                "                                                                        <a href=\"http://second.fertifabenefits.com/confirm.jsp?tokenid=" + Token  + "&com.fertifa.app.Company=" + CompanyName + "\" target=\"_blank\" style=\"display: inline-block; color: #ffffff;\n" +
                "background-color: #3498db;\n" +
                "border: solid 1px #3498db;\n" +
                "                                                                        border-radius: 5px;\n" +
                "                                                                        box-sizing:\n" +
                "                                                                        border-box;\n" +
                "                                                                        cursor: pointer;\n" +
                "                                                                        text-decoration: none;\n" +
                "                                                                        font-size: 14px; font-weight: bold; margin: 0;\n" +
                "                                                                        padding: 12px 25px; text-transform: capitalize;\n" +
                "                                                                        border-color: #3498db;\">Register</a> </td>\n" +
                "                                                                     </tr>\n" +
                "                                                                   </tbody>\n" +
                "\n" +
                "                        </table>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "\n" +
                "            <!-- END MAIN CONTENT AREA -->\n" +
                "            </table>\n" +
                "\n" +
                "            <!-- START FOOTER -->\n" +
                "            <div class=\"footer\" style=\"clear: both; Margin-top: 10px; text-align: center; width: 100%;\">\n" +
                "              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "                <tr>\n" +
                "                  <td class=\"content-block\" style=\"font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;\">\n" +

                "                  </td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </div>\n" +
                "            <!-- END FOOTER -->\n" +
                "\n" +
                "          <!-- END CENTERED WHITE CONTAINER -->\n" +
                "          </div>\n" +
                "        </td>\n" +
                "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </body>\n" +
                "</html>\n";
        return finalText;
    }
    public static String getForgotPasswordEmailBody(String email) {
        String finalText = "<!doctype html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <title>Simple Transactional Email</title>\n" +
                "    <style>\n" +
                "    /* -------------------------------------\n" +
                "        INLINED WITH htmlemail.io/inline\n" +
                "    ------------------------------------- */\n" +
                "    /* -------------------------------------\n" +
                "        RESPONSIVE AND MOBILE FRIENDLY STYLES\n" +
                "    ------------------------------------- */\n" +
                "    @media only screen and (max-width: 620px) {\n" +
                "      table[class=body] h1 {\n" +
                "        font-size: 28px !important;\n" +
                "        margin-bottom: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] p,\n" +
                "            table[class=body] ul,\n" +
                "            table[class=body] ol,\n" +
                "            table[class=body] td,\n" +
                "            table[class=body] span,\n" +
                "            table[class=body] a {\n" +
                "        font-size: 16px !important;\n" +
                "      }\n" +
                "      table[class=body] .wrapper,\n" +
                "            table[class=body] .article {\n" +
                "        padding: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] .content {\n" +
                "        padding: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .container {\n" +
                "        padding: 0 !important;\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .main {\n" +
                "        border-left-width: 0 !important;\n" +
                "        border-radius: 0 !important;\n" +
                "        border-right-width: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .btn table {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .btn a {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .img-responsive {\n" +
                "        height: auto !important;\n" +
                "        max-width: 100% !important;\n" +
                "        width: auto !important;\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    /* -------------------------------------\n" +
                "        PRESERVE THESE STYLES IN THE HEAD\n" +
                "    ------------------------------------- */\n" +
                "    @media all {\n" +
                "      .ExternalClass {\n" +
                "        width: 100%;\n" +
                "      }\n" +
                "      .ExternalClass,\n" +
                "            .ExternalClass p,\n" +
                "            .ExternalClass span,\n" +
                "            .ExternalClass font,\n" +
                "            .ExternalClass td,\n" +
                "            .ExternalClass div {\n" +
                "        line-height: 100%;\n" +
                "      }\n" +
                "      .apple-link a {\n" +
                "        color: inherit !important;\n" +
                "        font-family: inherit !important;\n" +
                "        font-size: inherit !important;\n" +
                "        font-weight: inherit !important;\n" +
                "        line-height: inherit !important;\n" +
                "        text-decoration: none !important;\n" +
                "      }\n" +
                "      #MessageViewBody a {\n" +
                "        color: inherit;\n" +
                "        text-decoration: none;\n" +
                "        font-size: inherit;\n" +
                "        font-family: inherit;\n" +
                "        font-weight: inherit;\n" +
                "        line-height: inherit;\n" +
                "      }\n" +
                "      .btn-primary table td:hover {\n" +
                "        background-color: #34495e !important;\n" +
                "      }\n" +
                "      .btn-primary a:hover {\n" +
                "        background-color: #34495e !important;\n" +
                "        border-color: #34495e !important;\n" +
                "      }\n" +
                "    }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body class=\"\" style=\"background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\">\n" +
                "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background-color: #f6f6f6;\">\n" +
                "      <tr>\n" +
                "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "        <td class=\"container\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; Margin: 0 auto; max-width: 580px; padding: 10px; width: 580px;\">\n" +
                "          <div class=\"content\" style=\"box-sizing: border-box; display: block; Margin: 0 auto; max-width: 580px; padding: 10px;\">\n" +
                "\n" +
                "            <!-- START CENTERED WHITE CONTAINER -->\n" +

                "            <table class=\"main\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background: #ffffff; border-radius: 3px;\">\n" +
                "\n" +
                "              <!-- START MAIN CONTENT AREA -->\n" +
                "              <tr>\n" +
                "                <td class=\"wrapper\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "                    <tr>\n" +
                "                      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Please click the button below to reset your pasword.</p>\n" +
                "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; box-sizing: border-box;\">\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td align=\"left\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;\">\n" +
                "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;\">\n" +
                "                                  <tbody>\n" +
                "                                                                     <tr>\n" +
                "                                                                       <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; background-color: #3498db; border-radius: 5px; text-align: center;\">\n" +
                "                                                                        <a href=\"http://second.fertifabenefits.com/confirmEmail.jsp?Email=" + email + "\" target=\"_blank\" style=\"display: inline-block; color: #ffffff;\n" +
                "background-color: #3498db;\n" +
                "border: solid 1px #3498db;\n" +
                "                                                                        border-radius: 5px;\n" +
                "                                                                        box-sizing:\n" +
                "                                                                        border-box;\n" +
                "                                                                        cursor: pointer;\n" +
                "                                                                        text-decoration: none;\n" +
                "                                                                        font-size: 14px; font-weight: bold; margin: 0;\n" +
                "                                                                        padding: 12px 25px; text-transform: capitalize;\n" +
                "                                                                        border-color: #3498db;\">Reset your password</a> </td>\n" +
                "                                                                     </tr>\n" +
                "                                                                   </tbody>\n" +
                "\n" +
                "                        </table>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "\n" +
                "            <!-- END MAIN CONTENT AREA -->\n" +
                "            </table>\n" +
                "\n" +
                "            <!-- START FOOTER -->\n" +
                "            <div class=\"footer\" style=\"clear: both; Margin-top: 10px; text-align: center; width: 100%;\">\n" +
                "              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "                <tr>\n" +
                "                  <td class=\"content-block\" style=\"font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;\">\n" +

                "                  </td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </div>\n" +
                "            <!-- END FOOTER -->\n" +
                "\n" +
                "          <!-- END CENTERED WHITE CONTAINER -->\n" +
                "          </div>\n" +
                "        </td>\n" +
                "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </body>\n" +
                "</html>\n";
        return finalText;
    }

    public static String getSendCodeBody(String userFirstName, String userLastName, String tokenCode) {
        String finalText = "<!doctype html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <title>Simple Transactional Email</title>\n" +
                "    <style>\n" +
                "    /* -------------------------------------\n" +
                "        INLINED WITH htmlemail.io/inline\n" +
                "    ------------------------------------- */\n" +
                "    /* -------------------------------------\n" +
                "        RESPONSIVE AND MOBILE FRIENDLY STYLES\n" +
                "    ------------------------------------- */\n" +
                "    @media only screen and (max-width: 620px) {\n" +
                "      table[class=body] h1 {\n" +
                "        font-size: 28px !important;\n" +
                "        margin-bottom: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] p,\n" +
                "            table[class=body] ul,\n" +
                "            table[class=body] ol,\n" +
                "            table[class=body] td,\n" +
                "            table[class=body] span,\n" +
                "            table[class=body] a {\n" +
                "        font-size: 16px !important;\n" +
                "      }\n" +
                "      table[class=body] .wrapper,\n" +
                "            table[class=body] .article {\n" +
                "        padding: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] .content {\n" +
                "        padding: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .container {\n" +
                "        padding: 0 !important;\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .main {\n" +
                "        border-left-width: 0 !important;\n" +
                "        border-radius: 0 !important;\n" +
                "        border-right-width: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .btn table {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .btn a {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .img-responsive {\n" +
                "        height: auto !important;\n" +
                "        max-width: 100% !important;\n" +
                "        width: auto !important;\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    /* -------------------------------------\n" +
                "        PRESERVE THESE STYLES IN THE HEAD\n" +
                "    ------------------------------------- */\n" +
                "    @media all {\n" +
                "      .ExternalClass {\n" +
                "        width: 100%;\n" +
                "      }\n" +
                "      .ExternalClass,\n" +
                "            .ExternalClass p,\n" +
                "            .ExternalClass span,\n" +
                "            .ExternalClass font,\n" +
                "            .ExternalClass td,\n" +
                "            .ExternalClass div {\n" +
                "        line-height: 100%;\n" +
                "      }\n" +
                "      .apple-link a {\n" +
                "        color: inherit !important;\n" +
                "        font-family: inherit !important;\n" +
                "        font-size: inherit !important;\n" +
                "        font-weight: inherit !important;\n" +
                "        line-height: inherit !important;\n" +
                "        text-decoration: none !important;\n" +
                "      }\n" +
                "      #MessageViewBody a {\n" +
                "        color: inherit;\n" +
                "        text-decoration: none;\n" +
                "        font-size: inherit;\n" +
                "        font-family: inherit;\n" +
                "        font-weight: inherit;\n" +
                "        line-height: inherit;\n" +
                "      }\n" +
                "      table td:hover {\n" +
                "        background-color: transparent !important;\n" +
                "      }\n" +
                "    }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body class=\"\" style=\"background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\">\n" +
                "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background-color: #f6f6f6;\">\n" +
                "      <tr>\n" +
                "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "        <td class=\"container\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; Margin: 0 auto; max-width: 580px; padding: 10px; width: 580px;\">\n" +
                "          <div class=\"content\" style=\"box-sizing: border-box; display: block; Margin: 0 auto; max-width: 580px; padding: 10px;\">\n" +
                "\n" +
                "            <!-- START CENTERED WHITE CONTAINER -->\n" +
                "            <table class=\"main\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background: #ffffff; border-radius: 3px;\">\n" +
                "\n" +
                "              <!-- START MAIN CONTENT AREA -->\n" +
                "              <tr>\n" +
                "                <td class=\"wrapper\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "                    <tr>\n" +
                "                      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Dear  " + userFirstName + " " + userLastName + "</p>\n" +
                "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; box-sizing: border-box;\">\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td align=\"left\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;\">\n" +
                "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;\">\n" +
                "                                  <tbody>\n" +
                "                                                                     <tr>\n" +
                "                                                                       <td style=\"font-family: sans-serif; font-size: 15px; vertical-align: top; border-radius: 5px; text-align: center;\">\n" +
                "                                                                        <p> Here is your one time passcode for registration verification: <strong>" + tokenCode + "</strong></td>\n" +
                "                                                                     </tr>\n" +
                "                                                                   </tbody>\n" +
                "\n" +
                "                        </table>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "               <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">If you experience any difficulties in the process, please contact info@fertifa.com</p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "\n" +
                "            <!-- END MAIN CONTENT AREA -->\n" +
                "            </table>\n" +
                "\n" +
                "            <!-- START FOOTER -->\n" +
                "            <div class=\"footer\" style=\"clear: both; Margin-top: 10px; text-align: center; width: 100%;\">\n" +
                "              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "                <tr>\n" +
                "                  <td class=\"content-block\" style=\"font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;\">\n" +

                "                  </td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </div>\n" +
                "            <!-- END FOOTER -->\n" +
                "\n" +
                "          <!-- END CENTERED WHITE CONTAINER -->\n" +
                "          </div>\n" +
                "        </td>\n" +
                "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </body>\n" +
                "</html>\n";
        return finalText;
    }

    public static String getSingleInvitationBodyAffiliate(Affiliate affiliate, int theAffiliateId, String Token) {
        String finalText = "<!doctype html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <title>Simple Transactional Email</title>\n" +
                "    <style>\n" +
                "    /* -------------------------------------\n" +
                "        INLINED WITH htmlemail.io/inline\n" +
                "    ------------------------------------- */\n" +
                "    /* -------------------------------------\n" +
                "        RESPONSIVE AND MOBILE FRIENDLY STYLES\n" +
                "    ------------------------------------- */\n" +
                "    @media only screen and (max-width: 620px) {\n" +
                "      table[class=body] h1 {\n" +
                "        font-size: 28px !important;\n" +
                "        margin-bottom: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] p,\n" +
                "            table[class=body] ul,\n" +
                "            table[class=body] ol,\n" +
                "            table[class=body] td,\n" +
                "            table[class=body] span,\n" +
                "            table[class=body] a {\n" +
                "        font-size: 16px !important;\n" +
                "      }\n" +
                "      table[class=body] .wrapper,\n" +
                "            table[class=body] .article {\n" +
                "        padding: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] .content {\n" +
                "        padding: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .container {\n" +
                "        padding: 0 !important;\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .main {\n" +
                "        border-left-width: 0 !important;\n" +
                "        border-radius: 0 !important;\n" +
                "        border-right-width: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .btn table {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .btn a {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .img-responsive {\n" +
                "        height: auto !important;\n" +
                "        max-width: 100% !important;\n" +
                "        width: auto !important;\n" +
                "      }\n" +
                "    }\n" +
                "\n" +
                "    /* -------------------------------------\n" +
                "        PRESERVE THESE STYLES IN THE HEAD\n" +
                "    ------------------------------------- */\n" +
                "    @media all {\n" +
                "      .ExternalClass {\n" +
                "        width: 100%;\n" +
                "      }\n" +
                "      .ExternalClass,\n" +
                "            .ExternalClass p,\n" +
                "            .ExternalClass span,\n" +
                "            .ExternalClass font,\n" +
                "            .ExternalClass td,\n" +
                "            .ExternalClass div {\n" +
                "        line-height: 100%;\n" +
                "      }\n" +
                "      .apple-link a {\n" +
                "        color: inherit !important;\n" +
                "        font-family: inherit !important;\n" +
                "        font-size: inherit !important;\n" +
                "        font-weight: inherit !important;\n" +
                "        line-height: inherit !important;\n" +
                "        text-decoration: none !important;\n" +
                "      }\n" +
                "      #MessageViewBody a {\n" +
                "        color: inherit;\n" +
                "        text-decoration: none;\n" +
                "        font-size: inherit;\n" +
                "        font-family: inherit;\n" +
                "        font-weight: inherit;\n" +
                "        line-height: inherit;\n" +
                "      }\n table td:hover {background-color:transparent !important}" +
                "    }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body class=\"\" style=\"background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\">\n" +
                "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background-color: #f6f6f6;\">\n" +
                "      <tr>\n" +
                "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "        <td class=\"container\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; Margin: 0 auto; max-width: 580px; padding: 10px; width: 580px;\">\n" +
                "          <div class=\"content\" style=\"box-sizing: border-box; display: block; Margin: 0 auto; max-width: 580px; padding: 10px;\">\n" +
                "\n" +
                "            <!-- START CENTERED WHITE CONTAINER -->\n" +
                "            <table class=\"main\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background: #ffffff; border-radius: 3px;\">\n" +
                "\n" +
                "              <!-- START MAIN CONTENT AREA -->\n" +
                "              <tr>\n" +
                "                <td class=\"wrapper\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "                    <tr>\n" +
                "                      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Dear " + affiliate.getFirstName() + " " + affiliate.getLastName() + "</p>\n" +
                "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; box-sizing: border-box;\">\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td align=\"left\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;\">\n" +
                "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;\">\n" +
                "                                  <tbody>\n" +
                "                                                                     <tr>\n" +
                "                                                                       <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; background-color: #3498db; border-radius: 5px; text-align: center;\">\n" +
                "                                                                        <a href=\"http://second.fertifabenefits.com/confirmsAffiliate.jsp?token=" + Token + "\" target=\"blank\" style=\"display: inline-block; color: #ffffff;\n" +
                "background-color: #3498db;\n" +
                "border: solid 1px #3498db;\n" +
                "                                                                        border-radius: 5px;\n" +
                "                                                                        box-sizing:\n" +
                "                                                                        border-box;\n" +
                "                                                                        cursor: pointer;\n" +
                "                                                                        text-decoration: none;\n" +
                "                                                                        font-size: 14px; font-weight: bold; margin: 0;\n" +
                "                                                                        padding: 12px 25px; text-transform: capitalize;\n" +
                "                                                                        border-color: #3498db;\">Get started now</a> </td>\n" +
                "                                                                     </tr>\n" +
                "                                                                   </tbody>\n" +
                "\n" +
                "                        </table>\n" +
                "                        <br />\n " +
                "                        <br />\n " +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Welcome to Fertifa! We understand how hard it is to reach out to anyone regarding personal fertility issues, so we commend and thank you for allowing us to support you through your fertility journey!</p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">The Fertifa team was established as a result of own personal fertility experiences - the rollercoaster of emotions, relationship worries and financial pressures that go alongside the (sometimes desperate) need to start a family.  Regardless of where you are in that fertility journey, our mission at Fertifa is to empower you to take control of your fertility.  We will provide you with access to the best fertility practitioners, at top clinics, at the best price.  At the same time, we will provide guidance on your diet, lifestyle and emotional wellbeing so that you are in the best possible condition to bring home a healthy baby.</p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">The first step, once you've completed our Enquiry Form, is a short 15-minute consultation with one of our Fertility Advisors or our in-house Doctor.  We will gain a full picture of your journey so far in order that we can then help you design a bespoke fertility action plan.  The Fertility Advisor will point you in the right direction for the necessary medical expertise, treatment type and any wellbeing support you may require to succeed on your journey, for which there are a wide range of discounts available to you.</p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Fertifa can also help you to understand and be proactive with your fertility health:</p>\n" +
                "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">- For any tests you may have via Fertifa, we provide you with a comprehensive report to explain the results <br/>\n" +
                "- Our online fertility education material is accessible to all Fertifa members<br/>\n" +
                "- We share the latest fertility research to keep you updated on new developments<br/>\n" +
                "- We provide all the nutrition-based information you may need to get pregnant and maintain a healthy pregnancy<br/>\n" +
                "- Our Fertility Advisors are available 24/7 to support you with any questions during your journey<br/></p>\n" +
                " <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">We do hope you find Fertifa's services beneficial.  If you have any questions or suggestions, we would love to hear them.  You can contact us directly at info@fertifa.com or through our messaging tab in the portal.</p>\n" +
                " <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;\">Thank you again for joining Fertifa!</p>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "\n" +
                "            <!-- END MAIN CONTENT AREA -->\n" +
                "            </table>\n" +
                "\n" +
                "            <!-- START FOOTER -->\n" +
                "            <div class=\"footer\" style=\"clear: both; Margin-top: 10px; text-align: center; width: 100%;\">\n" +
                "              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "                <tr>\n" +
                "                  <td class=\"content-block\" style=\"font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;\">\n" +

                "                  </td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </div>\n" +
                "            <!-- END FOOTER -->\n" +
                "\n" +
                "          <!-- END CENTERED WHITE CONTAINER -->\n" +
                "          </div>\n" +
                "        </td>\n" +
                "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </body>\n" +
                "</html>\n";
        return finalText;
    }
}
