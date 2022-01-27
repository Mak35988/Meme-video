package com.mayurrai.youtubepaisa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class SigningActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    GoogleSignInClient mGoogleSignInClient;
    String bool ;

    static Boolean good=false;

    private static int RC_SIGN_IN=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signing);


        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();

            }
        });


        ///////////////////////////////////////////// for data refernce in firebase///////////////////////////////////////////////////////////////////////////////



        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference=firebaseDatabase.getReference().child("U");





        ///////////////////////////////////////////// for data refernce in firebase///////////////////////////////////////////////////////////////////////////////

    }



    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        startActivityForResult(signInIntent, RC_SIGN_IN);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {


            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {

            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(SigningActivity.this);



            if (acct != null) {

                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                Toast.makeText(this, " "+ acct.getEmail(), Toast.LENGTH_SHORT).show();


                ///////////////////////////////////////////// for data refernce in firebase///////////////////////////////////////////////////////////////////////////////

             //   String userpics=""+personPhoto;

                HashMap<String,String>usermap= new HashMap<>();

                usermap.put("name",personName);
                usermap.put("Email Id",personEmail);
            //    usermap.put("person photo",userpics);

                databaseReference.push().setValue(usermap);

                ///////////////////////////////////////////// for data refernce in firebase///////////////////////////////////////////////////////////////////////////////



                bool = personEmail.toString();

                good = true;

                Intent intent = new Intent(SigningActivity.this,fakeActivity.class);
                intent.putExtra("message_key", bool);
                startActivity(intent);

            }



        } catch (ApiException e) {

            Toast.makeText(SigningActivity.this, "Error Occurs, Please try again", Toast.LENGTH_SHORT).show();

        }
    }




}