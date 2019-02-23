package com.hai.jedi.newspie.Services;

import android.content.Context;
import android.util.Log;

import com.hai.jedi.newspie.Constants;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Objects;

import okhttp3.Callback;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NewsPieService {
    public static final String TAG = NewsPieService.class.getSimpleName().toUpperCase();

    // Making our request
    public static void getNewsCategories(Callback callback){
        // Instantiating OkHttpClient instance
        OkHttpClient client = new OkHttpClient();

        //Constructing the request url
        HttpUrl.Builder url_builder = Objects.requireNonNull(HttpUrl.parse(Constants.NEWS_BASE_URL))
                                             .newBuilder();
        url_builder.addQueryParameter(Constants.COUNTRY_ID_PARAM, "us") // Put "us" here to test
                   .addQueryParameter(Constants.NEWS_CATEGORY_PARAM, "business") // Put "general"
                   .addQueryParameter(Constants.API_KEY_PARAM, Constants.NEWS_API_KEY);

        String request_url = url_builder.build().toString();

        // Preparing our request
        Request request = new Request.Builder()
                                     .url(request_url)
                                     .build();

        // Making our Api call that should receive a callback
        Call call = client.newCall(request);
        // We use enqueue here so that the call is asynchronous.
        call.enqueue(callback);
        // For synchronous we would use call.execute.
    }

    public String processResults(Response response){
        String json_data = "";
        try{
            ResponseBody responseBody = response.body();
            json_data = Objects.requireNonNull(responseBody).string();
            Log.d(TAG, json_data);
        } catch (IOException exception){
            exception.printStackTrace();
        }

        return json_data;
    }
}
