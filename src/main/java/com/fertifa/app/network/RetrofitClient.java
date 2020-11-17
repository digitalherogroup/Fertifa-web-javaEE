package com.fertifa.app.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RetrofitClient {

    private final static RetrofitClient INSTANCE = new RetrofitClient();

    public static RetrofitClient getInstance() {
        return INSTANCE;
    }

    public ApiService getService(String baseUrl) {
        return getRetrofit(baseUrl).create(ApiService.class);
    }

    private Retrofit getRetrofit(String baseUrl) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getEmailHeader())
                .build();
    }

    private OkHttpClient getEmailHeader() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // com.fertifa.app.Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer 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"); // <-- this is the important line

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return httpClient.build();
    }
}
