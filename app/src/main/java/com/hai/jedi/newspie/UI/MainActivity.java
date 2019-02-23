package com.hai.jedi.newspie.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;

import com.hai.jedi.newspie.R;
import com.hai.jedi.newspie.Services.NewsPieService;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.Call;
import okhttp3.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public final String TAG = MainActivity.class.getSimpleName().toUpperCase();

    @BindView(R.id.welcomeText) TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        newsCategories();
    }

    private void newsCategories(){
        NewsPieService newsPieService = new NewsPieService();
        NewsPieService.getNewsCategories(new Callback(){
            // In case our api call fails
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException exception){
                exception.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response)
            throws IOException{
              String json_response = newsPieService.processResults(response);

              Log.d(TAG, json_response);
            }


        });
    }

}
