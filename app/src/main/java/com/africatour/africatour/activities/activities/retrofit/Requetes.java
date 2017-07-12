package com.africatour.africatour.activities.activities.retrofit;

import com.africatour.africatour.activities.activities.Bdd.Client;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sch-a on 12/07/2017.
 */

public interface Requetes {

    @GET("/requete.php")
    Call<List<Client>> getClients();
}
