package com.example.dsathyan.qrcodetest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;


public class Location extends Activity {
    ImageButton button;

    public static ImageView Location;

    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);


        String new_string;
        String new_string_2;
        extras = getIntent().getExtras();
        new_string = extras.getString("Painter");
        new_string_2= extras.getString("Painting");



        Location = (ImageView) findViewById(R.id.Location);
        Location.setImageResource(getIntent().getIntExtra("myAllahResource", R.mipmap.ic_launcher));

    }

}

