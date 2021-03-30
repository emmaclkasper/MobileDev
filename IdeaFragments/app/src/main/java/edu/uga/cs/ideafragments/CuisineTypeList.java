package edu.uga.cs.ideafragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class CuisineTypeList extends AppCompatActivity {

    private static final String DEBUG_TAG = "Cuisine";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(DEBUG_TAG, "CuisineList.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cuisinelist);

    }

    // These activity callback methods are not needed and are for educational purposes only
    @Override
    protected void onStart() {
        Log.d(DEBUG_TAG, "CuisineList.onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(DEBUG_TAG, "CuisineList.onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(DEBUG_TAG, "CuisineList.onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(DEBUG_TAG, "CuisineList.onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(DEBUG_TAG, "CuisineList.onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d(DEBUG_TAG, "CuisineList.onRestart()");
        super.onRestart();
    }
}
