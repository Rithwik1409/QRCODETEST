package com.example.dsathyan.qrcodetest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Aboutapp extends Activity {
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_aboutapp);

        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.button14);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Aboutapp.this,
                        launcher.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.downin, R.anim.downout);
                //overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });


    }
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.downin, R.anim.downout);
    }


}