package com.mayurrai.youtubepaisa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 550;

    GoogleSignInClient mGoogleSignInClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);


        Boolean mayur=true;

        /////////////////////////////////////////////////////////////////////////////////////////////

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if (acct != null) {


            String personEmail = acct.getEmail();


            if (personEmail != null) {


            }


        }
        else {


            mayur=false;

        }


        ///////////////////////////////////////////////////////////////////////////////////////////////////


        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .getBoolean("isFirstRun", true);

            //show start activity


            if (isFirstRun) {

                startActivity(new Intent(SplashActivity.this, SigningActivity.class));
            }

            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putBoolean("isFirstRun", false).commit();


            //////////////////////////////////////////////////////////////////////////////////////////////////

//
//            Toast.makeText(SplashActivity.this, "" + isFirstRun, Toast.LENGTH_LONG)
//                    .show();



            if (isFirstRun == false && mayur==true) {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, fakeActivity.class);

                        startActivity(i);

                        finish();
                    }
                }, SPLASH_SCREEN_TIME_OUT);

            }
            else if (mayur == false) {
                Intent i = new Intent(SplashActivity.this, SigningActivity.class);

                startActivity(i);


            }




    }


}