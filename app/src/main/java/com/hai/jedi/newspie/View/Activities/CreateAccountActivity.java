package com.hai.jedi.newspie.View.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

import com.hai.jedi.newspie.Constants;
import com.hai.jedi.newspie.R;

import java.util.Objects;


public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = CreateAccountActivity.class.getSimpleName().toUpperCase();
    private static final int RC_SIGN_IN = 9001;

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth fbAuth;

    private ProgressDialog mAuthProgressDialog;

   @BindView(R.id.signUpButton)
    SignInButton googleBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ButterKnife.bind(this);

        googleBtn.setOnClickListener(this);

        createAuthProgressDialog();


        GoogleSignInOptions gSignInOptions = new GoogleSignInOptions
                                            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                            .requestIdToken("712847015969-mmcfgog0hejf7tkrqpcnmv5s8o8vqtb8.apps.googleusercontent.com")
                                            .requestEmail()
                                            .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gSignInOptions);

        fbAuth = FirebaseAuth.getInstance();

    }

    public void createAuthProgressDialog(){
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

    @Override
    public void onStart(){
        super.onStart();

        /*Check if user is already signed in (non-null) and update UI accordingly*/
        FirebaseUser currentUser = fbAuth.getCurrentUser();
        if(currentUser != null){
            updateUI(currentUser);
        }

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
                fbAuthWithGoogle(Objects.requireNonNull(account));
            } catch(ApiException exception){
                // Google SignIn Failed. Update UI appropriately
                Log.w(TAG, "Google sign in failed", exception);
                exception.printStackTrace();
            }
        }
    }

    private void fbAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG,  String.format("Firebase Auth With Google: %s Name: %s",
                account.getId(), account.getDisplayName()));

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuthProgressDialog.show();
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            /*Sign in successful update UI with the signed in users's information*/
                            FirebaseUser current_user = fbAuth.getCurrentUser();
                            updateUI(current_user);
                            mAuthProgressDialog.dismiss();
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

    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void updateUI(FirebaseUser user){

        String username = user.getDisplayName();
        String img_url = String.valueOf(user.getPhotoUrl());
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("username", username);
        intent.putExtra("image", img_url);
        startActivity(intent);
    }



    @Override
    public void onClick(View view){
        if(view == googleBtn){
            signIn();
        }
    }


}
