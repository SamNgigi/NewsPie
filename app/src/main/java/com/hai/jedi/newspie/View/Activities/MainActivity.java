package com.hai.jedi.newspie.View.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;

import com.hai.jedi.newspie.R;
import com.hai.jedi.newspie.ViewModel.SourceViewModel;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.welcomeText) TextView welcomeText;
    // TAG for Debugging
    public final String TAG = MainActivity.class.getSimpleName().toUpperCase();
    // Our ViewModel class
    SourceViewModel sourceViewModel;
    // Our News Categories Array
    private String[] categories = new String[]{
            "business", "entertainment", "general", "health", "science", "sports", "technology"
    };

    private String[] country_id = new String[]{ "us", "cn", "de", "il", "gb"};

    private Map<String, String> countries = new HashMap<String, String>(){{
        put("U.S.A", "us");
        put("China", "cn");
        put("Germany", "de");
        put("Israel", "il");
        put("U.K", "gb");
    }};



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
