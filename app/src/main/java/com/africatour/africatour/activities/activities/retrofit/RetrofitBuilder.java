package com.africatour.africatour.activities.activities.retrofit;


import android.support.v7.app.AppCompatActivity;

import com.africatour.africatour.activities.activities.Bdd.Client;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Alexandre on 10/07/2017.
 */

public class RetrofitBuilder extends AppCompatActivity {
    private final String BASE_URL = "http://10.0.2.2:8081/";

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    );

    Retrofit retrofit = builder.client(httpClient.build())
            .build();

    Requetes service = retrofit.create(Requetes.class);

    Call<List<Client>> call = service.getClients();

}

