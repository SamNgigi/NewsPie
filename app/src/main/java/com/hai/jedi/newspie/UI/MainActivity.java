package com.hai.jedi.newspie.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Typeface;

import android.widget.TextView;

import com.hai.jedi.newspie.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.welcomeText) TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Typeface rubikFont = Typeface.createFromAsset(
                                getAssets(), "fonts/rubik-regular.ttf");
        welcomeText.setTypeface(rubikFont);

    }
}
