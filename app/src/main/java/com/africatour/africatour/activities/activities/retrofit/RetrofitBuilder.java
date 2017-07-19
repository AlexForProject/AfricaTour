package com.africatour.africatour.activities.activities.retrofit;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.africatour.africatour.R;
import com.africatour.africatour.activities.activities.Bdd.Client;
import com.africatour.africatour.activities.activities.MainActivity;

import java.io.IOException;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Alexandre on 10/07/2017.
 */

public class RetrofitBuilder extends AppCompatActivity{
    private static final String BASE_URL = "http://10.0.2.2:80";
    Context ctx;
    String user;
    String password;

    public RetrofitBuilder(){}

    public RetrofitBuilder(final Context ctx, String user, String password) throws IOException {
        this.ctx = ctx;
        this.password = password;
        this.user = user;
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Authentification(user, password));

        final Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create())
                        ;

        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        Requetes requetes = retrofit.create(Requetes.class);

        Call<List<Client>> call = requetes.getClients();

        call.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                ListView testbdd = (ListView) findViewById(R.id.testbdd);
                List<Client> client = response.body();
                ArrayAdapter<Client> adapter = new ArrayAdapter<Client>(getApplicationContext(), R.layout.adapter, client);
                testbdd.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                CharSequence erreur = t.getMessage();
                Toast.makeText(ctx, erreur, Toast.LENGTH_LONG).show();
                Log.e("ERREUR", t.toString());
            }
        });
    }
}

