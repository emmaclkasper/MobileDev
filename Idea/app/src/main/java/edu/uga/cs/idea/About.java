package edu.uga.cs.idea;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class About extends AppCompatActivity {

    String buttonSelection;
    String spinnerSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView cuisineType = findViewById(R.id.cuisineType);
        TextView about = findViewById(R.id.about);
        ImageView pic = findViewById(R.id.pic);
        Button backButton = findViewById(R.id.backButton);
        Button forwardButton = findViewById(R.id.forwardButton);

        /**
         * Listeners for button presses
         */
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(About.this, MainActivity.class);
                startActivity(intent);
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(About.this, Restaurants.class);
                intent.putExtra("buttonSelection", spinnerSelection);
                startActivity(intent);
            }
        });

        spinnerSelection = "";
        if (getIntent().hasExtra("spinnerText")) {
            spinnerSelection = getIntent().getStringExtra("spinnerText");
        }

        about.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = new Intent(About.this, Restaurants.class);

        /**
         * Switch statement to print corresponding restaurant recommendations
         */
        switch (spinnerSelection) {

            case "American":
                cuisineType.setText("American");
                setRawText(about, R.raw.aboutamerican);
                pic.setImageResource(R.drawable.american);
                intent.putExtra("buttonSelection", buttonSelection);
                break;

            case "Asian":
                cuisineType.setText("Asian");
                setRawText(about, R.raw.aboutasian);
                pic.setImageResource(R.drawable.asian);
                intent.putExtra("buttonSelection", buttonSelection);
                break;

            case "Italian":
                cuisineType.setText("Italian");
                setRawText(about, R.raw.aboutitalian);
                pic.setImageResource(R.drawable.italian);
                intent.putExtra("buttonSelection", buttonSelection);
                break;

            case "Mexican":
                cuisineType.setText("Mexican");
                setRawText(about, R.raw.aboutamerican);
                pic.setImageResource(R.drawable.mexican);
                intent.putExtra("buttonSelection", buttonSelection);
                break;

            case "Vegetarian":
                cuisineType.setText("Vegetarian");
                setRawText(about, R.raw.aboutvegetarian);
                pic.setImageResource(R.drawable.vegetarian);
                intent.putExtra("buttonSelection", buttonSelection);
                break;

            case "Sweets":
                cuisineType.setText("Sweets");
                setRawText(about, R.raw.aboutdessert);
                pic.setImageResource(R.drawable.dessert);
                intent.putExtra("buttonSelection", buttonSelection);
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
