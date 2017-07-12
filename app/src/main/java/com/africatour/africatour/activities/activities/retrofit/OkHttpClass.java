package com.africatour.africatour.activities.activities.retrofit;

import android.support.v7.app.AppCompatActivity;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by sch-a on 12/07/2017.
 */

public class OkHttpClass extends AppCompatActivity{

    OkHttpClient client=null;

    public OkHttpClass(){
        if(client==null) {
            //Assigning a CacheDirectory
            File myCacheDir = new File(getCacheDir(), "OkHttpCache");
            //you should create it...
            int cacheSize = 1024 * 1024;
            Cache cacheDir = new Cache(myCacheDir, cacheSize);
            client = new OkHttpClient.Builder()
                    .cache(cacheDir)
                    .build();
        }
    }
}
