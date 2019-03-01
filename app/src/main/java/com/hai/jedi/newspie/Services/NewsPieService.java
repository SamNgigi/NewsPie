package com.hai.jedi.newspie.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hai.jedi.newspie.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsPieService {
    public static final String TAG = NewsPieService.class.getSimpleName().toUpperCase();

    public static NewsPieInterface newApiCall(){
        Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
                        .create();

        OkHttpClient okHttpClient = new OkHttpClient
                                        .Builder()
                                        .connectTimeout(1, TimeUnit.MINUTES)
                                        .readTimeout(30, TimeUnit.SECONDS)
                                        .build();

        Retrofit retrofit = new Retrofit
                                .Builder()
                                .baseUrl(Constants.NEWS_BASE_URL)
                                .client(okHttpClient)
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .build();
        return retrofit.create(NewsPieInterface.class);
    }
}
