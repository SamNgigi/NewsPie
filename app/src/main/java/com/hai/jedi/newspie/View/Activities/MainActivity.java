package com.hai.jedi.newspie.View.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.util.Log;

import com.hai.jedi.newspie.R;
import com.hai.jedi.newspie.ViewModel.HeadlineViewModel;
import com.hai.jedi.newspie.ViewModel.SourceViewModel;

import java.util.HashMap;
import java.util.Map;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.welcomeText) TextView welcomeText;
    // Tool bar for our menu, to close and open our nav
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    // TAG for Debugging
    public final String TAG = MainActivity.class.getSimpleName().toUpperCase();
    // Our ViewModel class
    SourceViewModel sourceViewModel;
    HeadlineViewModel headlineViewModel;
    // Our News Categories Array
    private String[] categories = new String[]{
            "business", "entertainment", "general", "health", "science", "sports", "technology"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        // Setting up Hamburger menu
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        sourceViewModel = ViewModelProviders.of(this).get(SourceViewModel.class);
        headlineViewModel = ViewModelProviders.of(this).get(HeadlineViewModel.class);
        /* *
        *  Got nullPointer exception when i called the below method because i forgot to initialize
        *  the ViewModel as done above.
        *
        * The call was experiencing a timeout when i was using the telkom wifi.
        * I changed to student wifi and the call was executed just fine.
        * */

        sourceViewModel.sourcesForCategory().observe(
                this, sourcesWrapper -> {
                    Log.d(TAG, String.valueOf(sourcesWrapper.getSource_list()));
                }
        );

        /*sourceViewModel.getHeadlines4Source("bbc-news").observe(
                this, headlinesWrapper -> {
                    Log.d(TAG, headlinesWrapper.getArticles().toString());
                }
        );*/

        headlineViewModel.sourceHeadlines().observe(
                this, headlineWrapper -> {
                    Log.d(TAG, headlineWrapper.getArticles().toString());
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
