package com.mayurrai.youtubepaisa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {


    private RadioGroup radioGroup;
    Button submit, clear;
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButton5,radioButton6,radioButton7,radioButton8,radioButton9,radioButton10;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        submit = findViewById(R.id.submit);
        clear = findViewById(R.id.clear);
        radioGroup = findViewById(R.id.groupradio);


        radioButton1= findViewById(R.id.radia_id1);
        radioButton2= findViewById(R.id.radia_id2);
        radioButton3= findViewById(R.id.radia_id3);
        radioButton4= findViewById(R.id.radia_id4);
        radioButton5= findViewById(R.id.radia_id5);
        radioButton6= findViewById(R.id.radia_id6);
        radioButton7= findViewById(R.id.radia_id7);
        radioButton8= findViewById(R.id.radia_id8);
        radioButton9= findViewById(R.id.radia_id9);
        radioButton10= findViewById(R.id.radia_id10);



        // Uncheck or reset the radio buttons initially;

        int selectedID = radioGroup.getCheckedRadioButtonId();
        radioGroup = findViewById(selectedID);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(radioButton1.isChecked() || radioButton2.isChecked() || radioButton3.isChecked() || radioButton4.isChecked() || radioButton5.isChecked() || radioButton6.isChecked() || radioButton7.isChecked() || radioButton8.isChecked() || radioButton9.isChecked()  || radioButton10.isChecked() ){

                    Intent intent = new Intent(NotificationActivity.this, fakeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);


                    Toast.makeText(NotificationActivity.this, "Report Sent ", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(NotificationActivity.this, "Please select any of them", Toast.LENGTH_SHORT).show();
                }

            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NotificationActivity.this, fakeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);


            }
        });


    }
}
