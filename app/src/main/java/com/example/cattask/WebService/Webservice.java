package com.example.cattask.WebService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Webservice {

    private static Webservice instance;
    private Api api ;

    public Webservice(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Services.MAIN_URL)
                .build();
        api = retrofit.create(Api.class);
    }

    public static Webservice getInstance(){
        if (instance == null){
            instance = new Webservice();
        }
        return instance;
    }
    public Api getApi(){
        return api;
    }

}
