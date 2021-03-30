/*
* Used Android fragments from class as a base to build this application
* */

package edu.uga.cs.ideafragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import edu.uga.cs.ideafragments.CuisineTypeList;
import edu.uga.cs.ideafragments.R;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    private final int SPLASH_DURATION = 3000;
    private static final String DEBUG_TAG = "Cuisine";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // this call will create the UI based on the screen in portrait orientation.
        // /res/layout/activity_android_versions_main.xml will be used;
        // in landscape orientation /res/layout-land/activity_android_versions_main.xml will be used
        setContentView(R.layout.splash);
        logo = findViewById(R.id.logo);
        logo.setImageResource(R.drawable.logo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mainIntent = new Intent(MainActivity.this, CuisineTypeList.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, SPLASH_DURATION);
    }

    // These activity callback methods are not needed and are for edational purposes only
    @Override
    protected void onStart() {
        Log.d(DEBUG_TAG, "MainActivity.onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(DEBUG_TAG, "MainActivity.onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(DEBUG_TAG, "MainActivity.onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(DEBUG_TAG, "MainActivity.onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(DEBUG_TAG, "MainActivity.onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d(DEBUG_TAG, "MainActivity.onRestart()");
        super.onRestart();
    }

}