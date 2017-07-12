package com.africatour.africatour.activities.activities.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.solver.Cache;

import java.io.File;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.MoshiConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Alexandre on 10/07/2017.
 */

public class RetrofitBuilder {
    public static final String BASE_URL = "http://localhost:8081/";
    public static WebService getComplexClient(Context ctx) {
        // get the OkHttp client
        OkHttpClient client = getOkHttpClient(ctx);

        // now it's using the cach
        // Using my HttpClient
        Retrofit raCustom = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                //add your own converter first (declaration order matters)
                //the responsability chain design pattern is behind
                .addConverterFactory(new MyPhotoConverterFactory())
                //You need to add a converter if you want your Json to be automagicly convert
                //into the object
                .addConverterFactory(MoshiConverterFactory.create())
                //then add your own CallAdapter
                .addCallAdapterFactory(new ErrorHandlingCallAdapterFactory())
                .build();
        WebService webServer = raCustom.create(WebService.class);
        return webServer;
    }

    @NonNull
    public static OkHttpClient getOkHttpClient(Context ctx) {
        // Define the OkHttp Client with its cache!
        // Assigning a CacheDirectory
        File myCacheDir=new File(ctx.getCacheDir(),"OkHttpCache");
        // You should create it...
        int cacheSize=1024*1024;
        Cache cacheDir=new Cache(myCacheDir,cacheSize);
        Interceptor customLoggingInterceptor=new CustomLoggingInterceptor();
        HttpLoggingInterceptor httpLogInterceptor=new HttpLoggingInterceptor();
        httpLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder()
                //add a cache
                .cache(cacheDir)
                //add interceptor (here to log the request)
                .addInterceptor(customLoggingInterceptor)
                .addInterceptor(httpLogInterceptor)
                .build();
    }
}
}
