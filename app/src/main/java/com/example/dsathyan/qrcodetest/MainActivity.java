package com.example.dsathyan.qrcodetest;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Vibrator;



import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

public class MainActivity extends ActionBarActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private Handler autoFocusHandler;

    private Button scanButton;
    private ImageScanner scanner;
    private ImageView Painting;
    private TextView name;

    private boolean barcodeScanned = false;
    private boolean previewing = true;


    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main)
        ;

        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.button2);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        scroll2.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.rightin, R.anim.rightout);
            }
        });
        initControls();
    }

    private void initControls() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        autoFocusHandler = new Handler();
        mCamera = getCameraInstance();

        // Instance barcode scanner
        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);

        mPreview = new CameraPreview(MainActivity.this, mCamera, previewCb,
                autoFocusCB);
        FrameLayout preview = (FrameLayout) findViewById(R.id.cameraPreview);
        preview.addView(mPreview);

        scanButton = (Button) findViewById(R.id.ScanButton);

        scanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (barcodeScanned) {
                    barcodeScanned = false;
                    mCamera.setPreviewCallback(previewCb);
                    mCamera.startPreview();
                    previewing = true;
                    mCamera.autoFocus(autoFocusCB);
                }
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // SCAdminTapToScanScreen.isFromAssetDetail = false;
            releaseCamera();
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
        }
        return c;
    }

    private void releaseCamera() {
        if (mCamera != null) {
            previewing = false;
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing)
                mCamera.autoFocus(autoFocusCB);
        }
    };

    Camera.PreviewCallback previewCb = new Camera.PreviewCallback() {
        public void onPreviewFrame(byte[] data, Camera camera) {
            Camera.Parameters parameters = camera.getParameters();
            Camera.Size size = parameters.getPreviewSize();

            Image barcode = new Image(size.width, size.height, "Y800");
            barcode.setData(data);
            int compResult=-1;
            String scanResult="";
            int result = scanner.scanImage(barcode);

            if (result != 0) {
                previewing = false;
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();

                SymbolSet syms = scanner.getResults();

                for (Symbol sym : syms) {

                    Log.i("<<<<<<Asset Code>>>>> ",
                            "<<<<Bar Code>>> " + sym.getData());
                    scanResult = sym.getData().trim();

                   // Toast.makeText(MainActivity.this, scanResult,
                           // Toast.LENGTH_SHORT).show();


                    barcodeScanned = true;

                    //final Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                    //vibe.vibrate(100);//80 represents the milliseconds (the duration of the vibration)
                    break;
                }

            }

            Log.i("<<<<scanResult>>>>", scanResult);



           // compResult = scanResult.compareTo("1");
            switch(scanResult){
                case "1":
                    Intent act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting1);
                    act2.putExtra("Painter", "Sanskriti");
                    act2.putExtra("Painting", "Lord Ganesha");

                    startActivity(act2);
                    break;

                case "2":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting2);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "3":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting3);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "4":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting4);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "5":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting5);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "6":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting6);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "7":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting7);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "8":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting8);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "9":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting9);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "10":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting10);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "11":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting11);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "12":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting12);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "13":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting13);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "14":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting14);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "15":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting15);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "16":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting16);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "17":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting17);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "18":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting18);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "19":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting19);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "20":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting20);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "21":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting21);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "22":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting22);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "23":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting23);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "24":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting24);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "25":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting25);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "26":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting26);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "27":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting27);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "28":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting28);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "29":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting29);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "30":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting30);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "31":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting31);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "32":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting32);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "33":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting33);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "34":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting34);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "35":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting35);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "36":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting36);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "37":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting37);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "38":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting38);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "39":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting39);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "40":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting40);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "41":
                act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting40);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");

                startActivity(act2);
                break;

                case "42":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting42);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "43":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting43);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "44":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting44);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "45":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting45);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "46":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting46);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "47":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting47);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "48":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting48);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "49":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting49);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "50":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting50);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "51":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting51);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "52":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting52);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "53":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting53);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "54":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting54);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "55":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting55);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "56":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting56);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "57":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting57);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "58":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting58);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "59":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting59);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "60":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting60);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "61":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting61);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "62":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting62);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "63":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting63);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "64":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting64);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "65":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting65);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "66":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting66);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "67":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting67);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "68":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting68);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "69":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting69);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "70":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting70);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "71":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting71);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "72":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting72);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "73":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting73);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "74":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting74);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "75":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting75);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "76":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting76);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "77":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting77);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "78":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting78);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "79":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting79);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "80":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting80);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "81":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting81);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "82":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting82);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "83":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting83);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "84":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting84);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "85":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting85);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "86":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting86);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "87":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting87);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "88":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting88);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "89":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting89);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "90":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting90);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "91":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting91);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "92":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting92);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "93":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting93);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "94":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting94);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "95":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting95);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "96":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting96);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "97":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting97);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "98":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting98);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "99":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting99);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "100":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting100);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;


case "101":
    act2= new Intent(MainActivity.this,Painting.class);
    act2.putExtra("myImageResource", R.drawable.painting101);
    act2.putExtra("Painter", "");
    act2.putExtra("Painting", "");

    break;
                case "102":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting102);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "103":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting103);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "104":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting104);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "105":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting105);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "106":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting106);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "107":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting107);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "108":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting108);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "109":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting109);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "110":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting110);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "111":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting111);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "112":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting112);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "113":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting113);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "114":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting114);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "115":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting115);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "116":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting116);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "117":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting117);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "118":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting118);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "119":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting119);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "120":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting120);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "121":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting121);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "122":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting122);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "123":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting123);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "124":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting124);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "125":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting125);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "126":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting126);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "127":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting127);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "128":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting128);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "129":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting129);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "130":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting130);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "131":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting131);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "132":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting132);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "133":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting133);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "134":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting134);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "135":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting135);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "136":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting136);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "137":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting137);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "138":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting138);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "139":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting139);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break; case "140":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting140);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "141":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting140);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "142":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting142);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "143":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting143);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "144":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting144);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "145":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting145);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "146":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting146);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "147":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting147);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "148":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting148);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "149":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting149);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "150":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting150);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "151":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting151);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "152":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting152);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "153":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting153);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "154":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting154);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "155":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting155);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "156":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting156);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "157":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting157);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "158":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting158);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "159":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting159);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "160":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting160);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "161":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting161);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "162":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting162);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "163":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting163);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "164":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting164);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "165":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting165);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "166":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting166);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "167":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting167);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "168":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting168);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "169":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting169);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "170":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting170);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "171":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting171);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "172":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting172);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "173":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting173);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "174":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting174);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "175":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting175);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");
                    compResult = scanResult.compareTo("ARTROOM");
                    if (compResult == 0) {
                         act2= new Intent(MainActivity.this,Location.class);
                        act2.putExtra("myAllahResource", R.drawable.artroomhere);

                        startActivity(act2);
                    }

                    compResult = scanResult.compareTo("CHESSROOM");
                    if (compResult == 0) {
                        act2= new Intent(MainActivity.this,Location.class);
                        act2.putExtra("myAllahResource", R.drawable.chessroomhere);

                        startActivity(act2);
                    }

                    compResult = scanResult.compareTo("AVROOM");
                    if (compResult == 0) {
                         act2= new Intent(MainActivity.this,Location.class);
                        act2.putExtra("myAllahResource", R.drawable.avroomhere);

                        startActivity(act2);
                    }
                    compResult = scanResult.compareTo("MINIAUDI");
                    if (compResult == 0) {
                         act2= new Intent(MainActivity.this,Location.class);
                        act2.putExtra("myAllahResource", R.drawable.miniaudihere);

                        startActivity(act2);
                    }
                    compResult = scanResult.compareTo("DANCEROOM");
                    if (compResult == 0) {
                         act2= new Intent(MainActivity.this,Location.class);
                        act2.putExtra("myAllahResource", R.drawable.danceroomhere);

                        startActivity(act2);
                    }

/*
                    startActivity(act2);
                    break;case "176":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting176);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "177":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting177);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "178":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting178);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "179":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting179);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "180":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting180);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "181":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting181);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "182":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting182);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "183":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting183);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "184":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting184);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "185":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting185);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "186":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting186);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "187":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting187);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "188":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting188);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "189":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting189);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "190":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting190);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;

                case "191":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting191);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;
                case "192":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.paintin6192);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "193":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting193);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "194":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting194);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "195":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting195);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "196":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting196);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "197":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting197);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "198":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting198);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "199":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting199);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;case "200":
                    act2= new Intent(MainActivity.this,Painting.class);
                    act2.putExtra("myImageResource", R.drawable.painting200);
                    act2.putExtra("Painter", "");
                    act2.putExtra("Painting", "");

                    startActivity(act2);
                    break;



*/








            }

           /* if (compResult == 0) {

                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting1);
                act2.putExtra("Painter", "Sanskriti");
                act2.putExtra("Painting", "Lord Ganesha");

                startActivity(act2);
            }

            compResult = scanResult.compareTo("2");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting2);
                act2.putExtra("Painter", "Sanskriti");
                act2.putExtra("Painting", "Flowers");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("3");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting3);
                act2.putExtra("Painter", "Ritisha");
                act2.putExtra("Painting", "Peacock");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("4");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting4);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "Rain");
                startActivity(act2);
        }

            compResult = scanResult.compareTo("5");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting5);
                act2.putExtra("Painter", "Sona");
                act2.putExtra("Painting", "The Ocean");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("6");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting6);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "Moonlit Valley");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("7");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting7);
                act2.putExtra("Painter", "Akash Singh");
                act2.putExtra("Painting", "Urban chaos");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("8");

            if (compResult == 0) {
                Intent act2= new Intent                (MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting8);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "Mother Teresa");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("9");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting9);
                act2.putExtra("Painter", "Devanshi");
                act2.putExtra("Painting", "Tree Stump");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("10");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting10);
                act2.putExtra("Painter", "Ananya");
                act2.putExtra("Painting", "Lord Buddha");
                startActivity(act2);
            }


            compResult = scanResult.compareTo("11");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting11);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("12");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting12);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "The blessed willow");
                startActivity(act2);
            }


            compResult = scanResult.compareTo("13");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting13);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "Glass Painting");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("14");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting14);
                act2.putExtra("Painter", "Disha");
                act2.putExtra("Painting", "The Goddess");
                startActivity(act2);


            }

            compResult = scanResult.compareTo("15");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting15);
                act2.putExtra("Painter", "Aarshia");
                act2.putExtra("Painting", "The Dancer");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("16");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting16);
                act2.putExtra("Painter", "Sakshi");
                act2.putExtra("Painting", "The Squirrel");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("17");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting17);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("18");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting18);
                act2.putExtra("Painter", "Sai Gayathri");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("19");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting19);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "Sails");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("20");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting20);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "Village");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("21");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting21);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("22");

            if (compResult == 0) {Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting22);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("23");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting23);
                act2.putExtra("Painter", "Arpitaa");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }

  //          compResult = scanResult.compareTo("24");
//
      //      if (compResult == 0) {
    //            Intent act2= new Intent(MainActivity.this,Painting.class);
  //              act2.putExtra("myImageResource", R.mipmap.Painting24);
//              startActivity(act2);
//            }

            compResult = scanResult.compareTo("25");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting25);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("26");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting26);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("27");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting27);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "Candleflame");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("28");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting28);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("29");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting29);
                act2.putExtra("Painter", "Palash Agrawal");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }

            compResult = scanResult.compareTo("30");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting30);
                act2.putExtra("Painter", "Sanskriti");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("31");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting31);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("32");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting32);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("33");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting33);
                act2.putExtra("Painter", "Ananya");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("34");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting34);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "Sachin Tendulkar");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("35");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting35);
                act2.putExtra("Painter", "Meghana");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("36");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting36);
                act2.putExtra("Painter", "Enter Name Here");
                act2.putExtra("Painting", "Radha and Krishna");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("37");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting37);
                act2.putExtra("Painter", "Disha");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("ARTROOM");
            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Location.class);
                act2.putExtra("myAllahResource", R.drawable.artroomhere);

                startActivity(act2);
            }

            compResult = scanResult.compareTo("CHESSROOM");
            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Location.class);
                act2.putExtra("myAllahResource", R.drawable.chessroomhere);

                startActivity(act2);
            }

            compResult = scanResult.compareTo("AVROOM");
            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Location.class);
                act2.putExtra("myAllahResource", R.drawable.avroomhere);

                startActivity(act2);
            }
            compResult = scanResult.compareTo("MINIAUDI");
            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Location.class);
                act2.putExtra("myAllahResource", R.drawable.miniaudihere);

                startActivity(act2);
            }
            compResult = scanResult.compareTo("DANCEROOM");
            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Location.class);
                act2.putExtra("myAllahResource", R.drawable.danceroomhere);

                startActivity(act2);
            }
            compResult = scanResult.compareTo("38");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting38);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("39");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting39);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("40");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting40);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("41");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting41);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("42");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting42);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("43");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting43);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("44");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting44);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("45");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting45);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("46");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting46);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("47");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting47);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("48");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting48);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("49");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting49);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("50");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting50);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("51");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting51);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("52");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting52);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("53");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting53);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("54");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting54);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("55");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting55);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("56");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting56);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("57");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting57);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("58");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting58);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("59");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting59);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("60");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting60);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("61");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting61);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("62");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting62);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("63");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting64);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("64");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting64);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("65");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting65);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("66");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting66);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("67");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting67);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("68");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting68);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("69");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting69);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }
            compResult = scanResult.compareTo("70");

            if (compResult == 0) {
                Intent act2= new Intent(MainActivity.this,Painting.class);
                act2.putExtra("myImageResource", R.drawable.painting70);
                act2.putExtra("Painter", "");
                act2.putExtra("Painting", "");
                startActivity(act2);
            }*/

    }



          /*  compResult = scanResult.compareTo("38");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting38.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("39");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting39.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("40");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting40.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("41");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting41.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("42");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting42.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("43");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting43.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("44");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting44.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("45");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting45.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("46");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting46.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("47");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting47.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("48");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting48.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("49");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting49.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("50");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting50.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("51");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting51.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("52");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting52.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("53");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting53.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("54");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting54.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("55");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting55.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("56");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting56.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("57");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting57.class);
                startActivity(myIntent);

            }
            compResult = scanResult.compareTo("58");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting58.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("59");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting59.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("60");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting60.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("61");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting61.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("62");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting62.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("63");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting63.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("64");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting64.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("65");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting65.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("66");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting66.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("67");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting67.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("68");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting68.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("69");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting69.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("70");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting70.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("71");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting71.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("72");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting72.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("73");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting73.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("74");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting74.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("75");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting75.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("76");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting76.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("77");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting77.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("78");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting78.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("79");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting79.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("80");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting80.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("81");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting81.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("82");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting82.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("83");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting83.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("84");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting84.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("85");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting85.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("86");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting86.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("87");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting87.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("88");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting88.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("89");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting89.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("90");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting90.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("91");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting91.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("92");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting92.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("93");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting93.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("94");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting94.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("95");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting95.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("96");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting96.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("97");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting97.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("98");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting98.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("99");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting99.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("100");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting100.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("101");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting101.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("102");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting102.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("103");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting103.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("104");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting104.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("105");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting105.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("106");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting106.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("107");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting107.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("108");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting108.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("109");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting109.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("110");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting110.class);
                startActivity(myIntent);
            }


            compResult = scanResult.compareTo("111");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting111.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("112");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting112.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("113");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting113.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("114");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting114.class);
                startActivity(myIntent);


            }

            compResult = scanResult.compareTo("115");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting115.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("116");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting116.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("117");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting117.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("118");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting118.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("119");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting119.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("120");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting120.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("121");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting121.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("122");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting122.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("123");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting123.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("124");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting124.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("125");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting125.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("126");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting126.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("127");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting127.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("128");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting128.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("129");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting129.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("130");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting130.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("131");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting131.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("132");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting132.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("133");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting133.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("134");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting134.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("135");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting135.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("136");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting136.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("137");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting137.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("138");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting138.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("139");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting139.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("140");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting140.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("141");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting141.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("142");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting142.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("143");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting143.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("144");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting144.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("145");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting145.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("146");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting146.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("147");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting147.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("148");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting148.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("149");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting149.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("150");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting150.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("151");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting151.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("152");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting152.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("153");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting153.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("154");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting154.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("155");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting155.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("156");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting156.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("157");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting157.class);
                startActivity(myIntent);

            }
            compResult = scanResult.compareTo("158");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting158.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("159");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting159.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("160");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting160.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("161");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting161.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("162");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting162.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("163");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting163.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("164");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting164.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("165");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting165.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("166");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting166.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("167");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting167.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("168");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting168.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("169");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting169.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("170");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting170.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("171");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting171.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("172");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting172.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("173");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting173.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("174");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting174.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("175");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting175.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("176");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting176.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("177");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting177.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("178");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting178.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("179");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting179.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("180");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting180.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("181");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting181.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("182");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting182.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("183");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting183.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("184");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting184.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("185");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting185.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("186");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting186.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("187");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting187.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("188");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting188.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("189");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting189.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("190");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting190.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("191");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting191.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("192");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting192.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("193");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting193.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("194");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting194.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("195");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting195.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("196");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting196.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("197");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting197.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("198");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting198.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("199");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting199.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("200");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting200.class);
                startActivity(myIntent);
            }



            compResult = scanResult.compareTo("201");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting201.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("202");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting202.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("203");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting203.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("204");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting204.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("205");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting205.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("206");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting206.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("207");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting207.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("208");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting208.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("209");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting209.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("210");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting210.class);
                startActivity(myIntent);
            }


            compResult = scanResult.compareTo("211");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting211.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("212");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting212.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("213");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting213.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("214");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting214.class);
                startActivity(myIntent);


            }

            compResult = scanResult.compareTo("215");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting215.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("216");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting216.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("217");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting217.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("218");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting218.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("219");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting219.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("220");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting220.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("221");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting221.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("222");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting222.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("223");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting223.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("224");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting224.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("225");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting225.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("226");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting226.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("227");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting227.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("228");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting228.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("229");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting229.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("230");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting230.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("231");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting231.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("232");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting232.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("233");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting233.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("234");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting234.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("235");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting235.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("236");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting236.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("237");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting237.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("238");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting238.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("239");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting239.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("240");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting240.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("241");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting241.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("242");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting242.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("243");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting243.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("244");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting244.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("245");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting245.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("246");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting246.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("247");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting247.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("248");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting248.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("249");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting249.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("250");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting250.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("251");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting251.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("252");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting252.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("253");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting253.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("254");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting254.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("255");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting255.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("256");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting256.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("257");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting257.class);
                startActivity(myIntent);

            }
            compResult = scanResult.compareTo("258");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting258.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("259");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting259.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("260");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting260.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("261");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting261.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("262");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting262.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("263");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting263.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("264");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting264.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("265");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting265.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("266");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting266.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("267");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting267.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("268");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting268.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("269");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting269.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("270");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting270.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("271");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting271.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("272");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting272.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("273");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting273.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("274");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting274.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("275");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting275.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("276");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting276.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("277");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting277.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("278");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting278.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("279");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting279.class);
                startActivity(myIntent);
            }
            compResult = scanResult.compareTo("280");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting280.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("281");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting281.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("282");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting282.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("283");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting283.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("284");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting284.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("285");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting285.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("286");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting286.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("287");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting287.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("288");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting288.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("289");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting289.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("290");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting290.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("291");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting291.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("292");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting292.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("293");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting293.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("294");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting294.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("295");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting295.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("296");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting296.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("297");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting297.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("298");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting298.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("299");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting299.class);
                startActivity(myIntent);
            }

            compResult = scanResult.compareTo("300");

            if (compResult == 0) {
                Intent myIntent = new Intent(MainActivity.this,
                        Painting300.class);
                startActivity(myIntent);
            }
            */





            /*if (result == 1) {
     Intent myIntent = new Intent(MainActivity.this,
             Chessroom.class);

     overridePendingTransition(R.anim.rightin, R.anim.rightout);
 }
*/
    };

    // Mimic continuous auto-focusing
    Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };


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