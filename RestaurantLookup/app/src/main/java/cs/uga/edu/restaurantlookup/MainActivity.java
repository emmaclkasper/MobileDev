package cs.uga.edu.restaurantlookup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Restaurant Lookup App - Android Application lists the restaurants in Athnens, GA
 *                      based on the cuisine types
 * @author Shivani Arbat
 * @version 1.0
 * @since 02/21/2019
 */
public class MainActivity extends AppCompatActivity  {

    private static final String DEBUG_TAG = "Restaurant_Lookup_Main";
    protected String spinnerOptionSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* add the image view to display */
        ImageView imageView = findViewById(R.id.mainPageImage);
        imageView.setImageAlpha(50);
        imageView.setImageResource(R.drawable.food_main);

        /* Text Views for the data */
        TextView textViewAppTittle = findViewById(R.id.textViewAppTittle);
        TextView textViewAppOverview = findViewById(R.id.textViewAppOverview);

        /* Spinner */
        final Spinner spinnerCuisine = findViewById(R.id.spinnerCuisine);

        /* Button - More Info */
        Button buttonMoreInfo = findViewById(R.id.buttonMoreInfo);

        /* Button - List Restaurants */
        Button buttonListRestaurants = findViewById(R.id.buttonListRestaurants);

        /* set values to display when application starts */
        try {
            InputStream mainOverviewFile = this.getResources().openRawResource(R.raw.main_page_overview);
            byte[] main_page_overview = new byte[mainOverviewFile.available()];
            mainOverviewFile.read(main_page_overview);
            textViewAppOverview.setText(Html.fromHtml(new String(main_page_overview)));
        } catch (Exception e) {
            Log.i(DEBUG_TAG,"exception:while R/W the raw file");
        }

        /* button click event to go to cuisine overview */
        buttonMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* create a list for spinner items */
                spinnerOptionSelected = getSpinnerSelectedValue(spinnerCuisine);
                Intent myIntent = new Intent(MainActivity.this,
                        Overview_Cuisine.class);
                myIntent.putExtra("spinnerOptionSelected",spinnerOptionSelected);
                startActivity(myIntent);
            }
        });

        /* button click event to go to restaurant list */
        buttonListRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* create a list for spinner items */
                spinnerOptionSelected = getSpinnerSelectedValue(spinnerCuisine);
                Intent myIntent = new Intent(MainActivity.this, ListRestaurants.class);
                myIntent.putExtra("spinnerOptionSelected",spinnerOptionSelected);
                startActivity(myIntent);
            }
        });

    }

    /**
     * Returns string value of the selected from the spinner on the main activity screen
     * @param spinner   input spinner to select input from
     * @return  String  output string from spinner selected option
     */
    protected String getSpinnerSelectedValue(Spinner spinner) {
        String selectedOption = spinner.getSelectedItem().toString();
        return selectedOption;
    }
}
