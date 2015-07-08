package com.example.dsathyan.qrcodetest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class scroll2 extends Activity {
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_scroll2);

        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.button15);

        // Capture button clicks
                button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(scroll2.this,
                        MainActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });
        button = (Button) findViewById(R.id.buttony);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(scroll2.this,
                        Map.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });


        button = (Button) findViewById(R.id.button10);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(scroll2.this,
                        Chamber.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });
        button = (Button) findViewById(R.id.button169);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(scroll2.this,
                        Chessroom.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });

        button = (Button) findViewById(R.id.button12);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(scroll2.this,
                        Chessroom.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });
        button = (Button) findViewById(R.id.button13);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(scroll2.this,
                        AVroom.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });


        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(scroll2.this,
                        MiniAuditorium.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });
        button = (Button) findViewById(R.id.buttonx);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(scroll2.this,
                        Dancedoom.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.rightin, R.anim.rightout);
            return true;
        }
        return false;
    }



}