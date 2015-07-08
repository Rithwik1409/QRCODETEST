package com.example.dsathyan.qrcodetest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Map extends Activity {
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_map);

        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.button);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Map.this,
                        Artroom.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });

        button = (Button) findViewById(R.id.button18);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Map.this,
                        Chessroom.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });

        button = (Button) findViewById(R.id.button24);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Map.this,
                        AVroom.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });
        button = (Button) findViewById(R.id.button23);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Map.this,
                        MiniAuditorium.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });
        button = (Button) findViewById(R.id.button22);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Map.this,
                        Dancedoom.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });
        button = (Button) findViewById(R.id.button20);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Map.this,
                        Chamber.class);
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