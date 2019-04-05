package com.hai.jedi.newspie.View.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hai.jedi.newspie.R;
import com.hai.jedi.newspie.ViewModel.HeadlineViewModel;
import com.hai.jedi.newspie.ViewModel.SharedViewModel;
import com.squareup.picasso.Picasso;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity
        extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    //@BindView(R.id.welcomeText) TextView welcomeText;
    // Tool bar for our logout_menu, to close and open our nav
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

    private String username, img_url;
    private Uri photo;

    FirebaseAuth fbAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbAuth = FirebaseAuth.getInstance();

        username = getIntent().getStringExtra("username");
        img_url = getIntent().getStringExtra("image");
        photo = Uri.parse(img_url);

        Log.d(TAG, String.format("Username: %s\n Image url:%s\n", username, img_url));

        ButterKnife.bind(this);
        // Makes sure the nav svg icons have their original color
        navView.setItemIconTintList(null);
        // Listening for selection
        navView.setNavigationItemSelectedListener(this);

        View headerView = navView.getHeaderView(0);
        TextView userTextView = (TextView)  headerView.findViewById(R.id.usernameTxt);
        userTextView.setText(username);
        ImageView userImageView = (ImageView) headerView.findViewById(R.id.usernameImg);
        Picasso.get().load(photo).into(userImageView);

        // Setting up Hamburger logout_menu
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        // Hamburger logout_menu animation
        drawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(drawerToggle);

        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
    }

    private ActionBarDrawerToggle setupDrawerToggle(){
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                                         R.string.drawer_open, R.string.drawer_close);
    }

    // Hamburger logout_menu animation manenos
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

    //Logout logout_menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Hamburger Menu manenos & Logout
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.logout_action:
                logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
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

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser user = fbAuth.getCurrentUser();
        if(user == null){
            Intent intent = new Intent(this, CreateAccountActivity.class);
        }
    }




}
