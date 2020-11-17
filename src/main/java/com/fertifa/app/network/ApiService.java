package com.fertifa.app.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("v3/mail/send")
    Call<Object> sendEmail(@Body EmailBody emailBody);

    @POST("employee/AdminIncomingCallServlet")
    Call<Object> sendIncomingCall();
}
