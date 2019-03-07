package com.hai.jedi.newspie.View.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.util.Log;

import com.google.android.material.navigation.NavigationView;
import com.hai.jedi.newspie.R;
import com.hai.jedi.newspie.ViewModel.HeadlineViewModel;
import com.hai.jedi.newspie.ViewModel.SharedViewModel;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity
        extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    //@BindView(R.id.welcomeText) TextView welcomeText;
    // Tool bar for our menu, to close and open our nav
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.nav_view) NavigationView navView;
    // TAG for Debugging
    public final String TAG = MainActivity.class.getSimpleName().toUpperCase();
    // Our ViewModel class
    // SourceViewModel sourceViewModel;
    HeadlineViewModel headlineViewModel;
    SharedViewModel sharedViewModel;

    //Animating the NavigationView
    private ActionBarDrawerToggle drawerToggle;
    // Our News Categories Array
    private String[] categories = new String[]{
            "business", "entertainment", "general", "health", "science", "sports", "technology"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        // Makes sure the nav svg icons have their original color
        navView.setItemIconTintList(null);
        // Listening for selection
        navView.setNavigationItemSelectedListener(this);
        // Setting up Hamburger menu
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        // Hamburger menu animation
        drawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(drawerToggle);

        headlineViewModel = ViewModelProviders.of(this).get(HeadlineViewModel.class);
        headlineViewModel.sourceHeadlines().observe(
                this, headlineWrapper -> {
                    Log.d(TAG, headlineWrapper.getArticles().toString());
                }
        );

        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
    }

    private ActionBarDrawerToggle setupDrawerToggle(){
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                                         R.string.drawer_open, R.string.drawer_close);
    }

    // Hamburger menu animation manenos
    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    // Hamburger Menu manenos
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Nav item clicked manenos
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem navItem){
        // set navItem as selected to persist highlight
        navItem.setChecked(true);
        // close drawer when item is tapped
        drawerLayout.closeDrawers();

        switch(navItem.getItemId()){
            case R.id.nav_general:
                sharedViewModel.setSelected_category();
                break;
            case R.id.nav_business:
                sharedViewModel.setSelected_category((String) navItem.getTitle());
                break;
            case R.id.nav_sports:
                sharedViewModel.setSelected_category((String) navItem.getTitle());
                break;
            case R.id.nav_tech:
                sharedViewModel.setSelected_category((String) navItem.getTitle());
                break;
            case R.id.nav_health:
                sharedViewModel.setSelected_category((String) navItem.getTitle());
                break;
            case R.id.nav_science:
                sharedViewModel.setSelected_category((String) navItem.getTitle());
                break;
            case R.id.nav_entertainment:
                sharedViewModel.setSelected_category((String) navItem.getTitle());
                break;

        }

        return true;
    }


}
