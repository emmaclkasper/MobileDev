package edu.uga.cs.idea;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class Restaurants extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        /**
         * creating views for the input
         */

        Button backButton = findViewById(R.id.backButton);
        TextView restaurantsRec = findViewById(R.id.restaurantRecs);
        TextView recType = findViewById(R.id.recType);


        /**
         * Listeners for button presses
         */
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Restaurants.this, MainActivity.class);
                startActivity(intent);
            }
        });

        String spinnerSelection = "";
        if (getIntent().hasExtra("buttonSelection")) {
            spinnerSelection = getIntent().getStringExtra("buttonSelection");
        }

        restaurantsRec.setMovementMethod(new ScrollingMovementMethod());

        /**
         * Switch statement to print corresponding restaurant recommendations
         */
        switch (spinnerSelection) {

            case "American":
                recType.setText("American");
                setRawText(restaurantsRec, R.raw.americanrec);
                break;

            case "Asian":
                recType.setText("Asian");
                setRawText(restaurantsRec, R.raw.asianrec);
                break;

            case "Italian":
                recType.setText("Italian");
                setRawText(restaurantsRec, R.raw.italianrec);
                break;

            case "Mexican":
                recType.setText("Mexican");
                setRawText(restaurantsRec, R.raw.mexicanrec);
                break;

            case "Vegetarian":
                recType.setText("Vegetarian");
                setRawText(restaurantsRec, R.raw.vegetarianrec);
                break;

            case "Sweets":
                recType.setText("Sweets");
                setRawText(restaurantsRec, R.raw.dessert);
                break;

        }

    }

    /**
     * Sets proper raw file to the textview
     * @param view              textView
     * @param cuisineRawFile    raw file that corresponds to selection
     */
    protected void setRawText(TextView view, @RawRes int cuisineRawFile) {
        try {
            InputStream rawView = this.getResources().openRawResource(cuisineRawFile);
            byte[] cuisineView = new byte[rawView.available()];
            rawView.read(cuisineView);
            view.setText(new String(cuisineView));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
