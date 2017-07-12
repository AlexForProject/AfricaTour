package com.africatour.africatour.activities.activities.retrofit;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sch-a on 12/07/2017.
 */

public class Authentification implements Interceptor {

    private String infos;

    public Authentification(String user, String password){
        this.infos = Credentials.basic(user, password);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authentificationRequest = request.newBuilder()
                .header("Authorization", infos).build();
        return chain.proceed(authentificationRequest);
    }
}
