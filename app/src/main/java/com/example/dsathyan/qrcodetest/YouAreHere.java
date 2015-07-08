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


public class YouAreHere extends Activity {


    public static ImageView image;

    Bundle extras;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_are_here);


        extras = getIntent().getExtras();




        image = (ImageView) findViewById(R.id.image);
        image.setImageResource(getIntent().getIntExtra("myURHResource", R.mipmap.ic_launcher));

    }

}
