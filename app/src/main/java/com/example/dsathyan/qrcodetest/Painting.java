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


public class Painting extends Activity {
    Button button;

    public static ImageView image;
    public static TextView textViewObj;
    public static TextView TextViewObj2;
Bundle extras;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting);



String new_string;
        String new_string_2;
        extras = getIntent().getExtras();
        new_string = extras.getString("Painter");
        new_string_2= extras.getString("Painting");

        textViewObj = (TextView) findViewById(R.id.Painter);
        textViewObj.setText(new_string);
        TextViewObj2 = (TextView) findViewById(R.id.Painting);
        TextViewObj2.setText(new_string_2);

        image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(getIntent().getIntExtra("myImageResource", R.mipmap.ic_launcher));

    }

}
