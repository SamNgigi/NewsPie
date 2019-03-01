package com.hai.jedi.newspie.View.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hai.jedi.newspie.Constants;
import com.hai.jedi.newspie.R;
import com.hai.jedi.newspie.Services.NewsPieInterface;
import com.hai.jedi.newspie.Services.NewsPieService;
import com.hai.jedi.newspie.Services.SourcesWrapper;
import com.hai.jedi.newspie.ViewModel.SourceViewModel;

import java.util.Objects;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public final String TAG = MainActivity.class.getSimpleName().toUpperCase();

    SourceViewModel sourceViewModel;

    @BindView(R.id.welcomeText) TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        sourceViewModel = ViewModelProviders.of(this).get(SourceViewModel.class);
        /* *
        *  Got nullPointer exception when i called the below method because i forgot to initialize
        *  the ViewModel as done above.
        *
        * The call was experiencing a timeout when i was using the telkom wifi.
        * I changed to student wifi and the call was executed just fine.
        * */
        sourceViewModel.sourcesForCategory("general").observe(
                this, sourcesWrapper -> {
                    Log.d(TAG, String.valueOf(sourcesWrapper.getSource_list()));
                }
        );
    }


}
