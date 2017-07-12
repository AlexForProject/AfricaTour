package com.africatour.africatour.activities.activities.retrofit;

import com.africatour.africatour.activities.activities.Bdd.Client;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Alexandre on 10/07/2017.
 */

public interface WebService {

    @GET("requete.php")
    Call<Client> getClients();
}
