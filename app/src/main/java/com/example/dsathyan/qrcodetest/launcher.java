package com.example.dsathyan.qrcodetest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class launcher extends Activity {
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_launcher);

        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.button13);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(launcher.this,
                        MainActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
    }
});


        button = (Button) findViewById(R.id.button3);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(launcher.this,
                        scroll2.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });
        button = (Button) findViewById(R.id.button6);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(launcher.this,
                        Map.class);

                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });
        button = (Button) findViewById(R.id.about);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(launcher.this,
                        Aboutapp.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.upin, R.anim.upout);
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