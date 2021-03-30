package cs.uga.edu.restaurantlookup;

import android.support.annotation.RawRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class Overview_Cuisine extends AppCompatActivity {

    private static final String DEBUG_TAG = "Restaurant_Lookup_cui";

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview__cuisine);

        /* to set up the back button and title*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        @StringRes int titleListRestaurants = R.string.cuisine_overview;
        getSupportActionBar().setTitle(titleListRestaurants);

        /* get Intent */
        String message = "";
        if(getIntent().hasExtra("spinnerOptionSelected")){
            message = getIntent().getStringExtra("spinnerOptionSelected");
        }

        /* TextView data */
        TextView overviewTitleTextView = findViewById(R.id.overviewTitle);
        TextView cuisineOverviewTextView = findViewById(R.id.cuisineInfo);


        /* add scroll bar for the text view */
        cuisineOverviewTextView.setMovementMethod(new ScrollingMovementMethod());

        /* add the image view to display */
        final ImageView imageView = findViewById(R.id.cuisineImage);

        /* call method to populate text view values based on spinner item selected*/
        callCuisineIntent(message,cuisineOverviewTextView,overviewTitleTextView,imageView);
    }

    /**
     * Methos setOverviewTextViews reads the raw file and displays in the desired text view
     * @param view  textview in which raw file content will be displayed
     * @param cuisineOverviewRawFile   raw file for content
     * @exception Exception exception to handle any excption occuring while reading/writing raw file
     */
    protected void setOverviewTextViews(TextView view, @RawRes int cuisineOverviewRawFile) {
        try {
            InputStream mainOverviewFile = this.getResources().openRawResource(cuisineOverviewRawFile);
            byte[] cuisineOverview = new byte[mainOverviewFile.available()];
            mainOverviewFile.read(cuisineOverview);
            view.setText(new String(cuisineOverview));
        } catch (Exception e) {
            Log.i(DEBUG_TAG,"exception:while R/W the raw file");
        }

    }

    /**
     * Methos setOverviewTitleTextView sets the title in the text view
     * @param view textview in which raw file content needs to be displayed
     * @param cuisineOverviewTitle textview in which string content will be displayed
     */
    protected void setOverviewTitleTextView(TextView view, @StringRes int cuisineOverviewTitle) {
        try {
            view.setText(cuisineOverviewTitle);
        } catch (Exception e) {
            Log.i(DEBUG_TAG,"exception:while R/W the string from spinner");
        }

    }

    /**
     * Method callCuisineIntent populates the value in the textViews and ImageView depending upon
     * the option selected in spinner in the main activity window.
     * @param message   string which is the deciding parameter on what values will be populated in views
     * @param cuisineOverviewTextView   textView to display cuisine overview
     * @param overviewTitleTextView     textView to display cuisine title
     */
    protected void callCuisineIntent(String message,TextView cuisineOverviewTextView,TextView overviewTitleTextView,ImageView imageView){

        switch (message){
            case "Native American":
                imageView.setImageResource(R.drawable.native_american);
                setOverviewTitleTextView(overviewTitleTextView,R.string.native_american_cuisine_title);
                setOverviewTextViews(cuisineOverviewTextView,R.raw.native_american_cuisine_overview);
                break;
            case "Indian":
                imageView.setImageResource(R.drawable.indian_cuisine);
                setOverviewTitleTextView(overviewTitleTextView,R.string.indian_cuisine_title);
                setOverviewTextViews(cuisineOverviewTextView,R.raw.indian_cuisine_overview);
                break;
            case "Thai":
                imageView.setImageResource(R.drawable.thai);
                setOverviewTitleTextView(overviewTitleTextView,R.string.thai_cuisine_title);
                setOverviewTextViews(cuisineOverviewTextView,R.raw.thai_cuisine_overview);
                break;
            case "Italian":
                imageView.setImageResource(R.drawable.italian);
                setOverviewTitleTextView(overviewTitleTextView,R.string.italian_cuisine_title);
                setOverviewTextViews(cuisineOverviewTextView,R.raw.italian_cuisine_overview);
                break;
            case "Chinese":
                imageView.setImageResource(R.drawable.chinese);
                setOverviewTitleTextView(overviewTitleTextView,R.string.chinese_cuisine_title);
                setOverviewTextViews(cuisineOverviewTextView,R.raw.chinese_cuisine_overview);
                break;
            case "Mexican":
                imageView.setImageResource(R.drawable.mexican);
                setOverviewTitleTextView(overviewTitleTextView,R.string.mexican_cuisine_title);
                setOverviewTextViews(cuisineOverviewTextView,R.raw.mexican_cuisine_overview);
                break;
            case "Japanese":
                imageView.setImageResource(R.drawable.japanese);
                setOverviewTitleTextView(overviewTitleTextView,R.string.japanese_cuisine_title);
                setOverviewTextViews(cuisineOverviewTextView,R.raw.japanese_cuisine_overview);
                break;
            case "Armenian":
                imageView.setImageResource(R.drawable.armenian);
                setOverviewTitleTextView(overviewTitleTextView,R.string.armenian_cuisine_title);
                setOverviewTextViews(cuisineOverviewTextView,R.raw.armenian_cuisine_overview);
                break;
        }
    }
}