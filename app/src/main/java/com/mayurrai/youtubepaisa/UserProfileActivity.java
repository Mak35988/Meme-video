package com.mayurrai.youtubepaisa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Random;


public class UserProfileActivity extends AppCompatActivity {


    ImageView imageView;

    TextView username,useremailid,utubeshortuniqueid;

    Button logout;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        imageView = findViewById(R.id.imageview);
        imageView.setClipToOutline(true);

        username = findViewById(R.id.username);
        useremailid = findViewById(R.id.emailid);
        utubeshortuniqueid = findViewById(R.id.uniqueid);

        logout = findViewById(R.id.logoutbutton);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserProfileActivity.this, "Please update your image in Email Id", Toast.LENGTH_SHORT).show();
            }
        });





        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });


        //////////////////////// for set the data ////////////////////////


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if (acct != null) {

            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();


            if (personPhoto != null) {
//                imageView.setImageURI(personPhoto);
//                Glide.with(this).load(personPhoto).into(imageView);
                Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);

            }

            else{
                Glide.with(this).load(String.valueOf("https://i2.wp.com/www.cssscript.com/wp-content/uploads/2020/12/Customizable-SVG-Avatar-Generator-In-JavaScript-Avataaars.js.png?fit=438%2C408&ssl=1")).into(imageView);

            }


            username.setText(personName);
            useremailid.setText(personEmail);

       //     Toast.makeText(UserProfileActivity.this, " "+ personPhoto, Toast.LENGTH_SHORT).show();

        }


        //////////////////////// for set the data ////////////////////////




    }





    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }





    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(UserProfileActivity.this, "Sign Out", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(UserProfileActivity.this, SigningActivity.class);

                        ////////////////

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        ////////////////////////

                        startActivity(intent);

                    }
                });

    }



}

