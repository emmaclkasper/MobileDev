/**
 * IDEA
 * Android application find local cuisine suggestions in Athens, Georgia
 *
 * @author Emma Kasper
 * @since 2/21/2020
 */

package edu.uga.cs.idea;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    TextView userPrompt;
    ImageView logo;
    Intent intent;
    String spinnerText;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Inside OnCreate Method");
        Log.i(TAG, "Inside OnCreate Method");
        Log.e(TAG, "Inside OnCreate Method");

        /**
         * Creating views
         */
        userPrompt = findViewById(R.id.userPrompt);
        final Spinner cuisineSpinner = findViewById(R.id.cuisineSpinner);
        logo = findViewById(R.id.logo);
        logo.setImageResource(R.drawable.logo);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.cuisine));

        cuisineSpinner.setAdapter(adapter);
        cuisineSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /**
             * Stores the item selected from the array into spinnerText and starts new activity
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {

                    userPrompt.setText("What would you like to eat?");
                } else {

                    spinnerText = cuisineSpinner.getSelectedItem().toString();
                    intent = new Intent(MainActivity.this, About.class);
                    intent.putExtra("spinnerText", spinnerText);
                    startActivity(intent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


}



