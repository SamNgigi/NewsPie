package com.hai.jedi.newspie.View.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import butterknife.ButterKnife;
import butterknife.BindView;

import com.hai.jedi.newspie.R;


public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = CreateAccountActivity.class.getSimpleName().toUpperCase();
    private static final int RC_SIGN_IN = 1;

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth fbAuth;

   @BindView(R.id.signUpButton)
    SignInButton googleBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ButterKnife.bind(this);

        googleBtn.setOnClickListener(this);

        fbAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN.DEFAULT_SIGN_IN)
                                                                    .requestIdToken(getString(R.string.default_web_client_id))
                                                                    .requestEmail()
                                                                    .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gSignInOptions);
    }

    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*Result returned from launching the Intent from mGoogleSignInApi.getSignInIntent()*/
        if(requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try{
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                assert account != null;
                fbAuthWithGoogle(account);
            } catch(ApiException exception){
                // Google SignIn Failed. Update UI appropriately
                Log.w(TAG, "Google sign in failed", exception);
            }
        }
    }

    @Override
    public void onStart(){
        super.onStart();

        /*Check if user is already signed in (non-null) and update UI accordingly*/
        FirebaseUser currentUser = fbAuth.getCurrentUser();
        assert currentUser != null;
        updateUI(currentUser);
    }

    private void fbAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG,  String.format("Firebase Auth With Google: %s Name: %s",
                    account.getId(), account.getDisplayName()));

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            /*Sign in successful update UI with the signed in users's information*/
                            FirebaseUser current_user = fbAuth.getCurrentUser();
                            updateUI(current_user);
                        } else{
                            /*If sign in fails, display a message to the user*/
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            /* *
                             * TODO
                             * Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.",
                             * Snackbar.LENGTH_SHORT).show();
                             * updateUI(null);
                             * */

                        }
                    }
                });
    }

    @Override
    public void onClick(View view){
        if(view == googleBtn){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void updateUI(FirebaseUser user){

        String username = user.getDisplayName();
        String img_url = String.valueOf(user.getPhotoUrl());
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("username", username);
        intent.putExtra("image", img_url);
        startActivity(intent);
    }


}
