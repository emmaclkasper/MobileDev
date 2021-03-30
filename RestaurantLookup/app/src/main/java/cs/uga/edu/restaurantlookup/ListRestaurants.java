package cs.uga.edu.restaurantlookup;

import android.support.annotation.RawRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import java.io.InputStream;

public class ListRestaurants extends AppCompatActivity {

    TextView restaurantList;
    private static final String DEBUG_TAG = "Restaurant_List";

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurants);

        /* to set up the back button */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* instanstiate and add scroll bar for the text view */
        restaurantList = findViewById(R.id.restaurantList);
        restaurantList.setMovementMethod(new ScrollingMovementMethod());

        /* get Intent */
        String message = "";
        if(getIntent().hasExtra("spinnerOptionSelected")){
            message = getIntent().getStringExtra("spinnerOptionSelected");
        }

        /* call list restaurants */
        callListRestaurants(message,restaurantList);
    }

    /**
     * Methos setOverviewTextViews reads the raw file and displays in the desired text view
     * @param view  textview in which raw file content needs to be displayed
     * @param restListOverviewRawFile   raw file for content
     * @exception Exception exception to handle any excption occuring while reading/writing raw file
     */
    protected void setOverviewTextViews(TextView view, @RawRes int restListOverviewRawFile) {
        try {
            InputStream mainOverviewFile = this.getResources().openRawResource(restListOverviewRawFile);
            byte[] cuisineOverview = new byte[mainOverviewFile.available()];
            mainOverviewFile.read(cuisineOverview);
            view.setText(Html.fromHtml(new String(cuisineOverview)));
        } catch (Exception e) {
            Log.i(DEBUG_TAG,"exception:while R/W the raw file");
        }

    }

    /**
     * Method callListRestaurants populates the value in the textViews depending upon the option selected
     * in spinner in the main activity window. Also the title of the action bar is also updated upon the
     * selected spinner option.
     * @param message   string which is the deciding parameter on what values will be populated in views
     * @param textView  textView to display list of restaurants
     */
    protected void callListRestaurants(String message,TextView textView) {
        switch (message) {
            case "Native American":
                @StringRes int nativeamerican = R.string.native_american_res_title;
                getSupportActionBar().setTitle(nativeamerican);
                setOverviewTextViews(textView,R.raw.native_american_restlist);
                break;
            case "Indian":
                @StringRes int indian = R.string.indian_res_title;
                getSupportActionBar().setTitle(indian);
                setOverviewTextViews(textView,R.raw.indian_restlist);
                break;
            case "Thai":
                @StringRes int thai = R.string.thai_res_title;
                getSupportActionBar().setTitle(thai);
                setOverviewTextViews(textView,R.raw.thai_restlist);
                break;
            case "Italian":
                @StringRes int italian = R.string.italian_res_title;
                getSupportActionBar().setTitle(italian);
                setOverviewTextViews(textView,R.raw.italian_restist);
                break;
            case "Chinese":
                @StringRes int chinese = R.string.chinese_res_title;
                getSupportActionBar().setTitle(chinese);
                setOverviewTextViews(textView,R.raw.chinese_restlist);
                break;
            case "Mexican":
                @StringRes int mexican = R.string.mexican_res_title;
                getSupportActionBar().setTitle(mexican);
                setOverviewTextViews(textView,R.raw.mexican_restlist);
                break;
            case "Japanese":
                @StringRes int japanese = R.string.japanese_res_title;
                getSupportActionBar().setTitle(japanese);
                setOverviewTextViews(textView,R.raw.japanese_restlist);
                break;
            case "Armenian":
                @StringRes int armenian = R.string.armenian_res_title;
                getSupportActionBar().setTitle(armenian);
                setOverviewTextViews(textView,R.raw.armenian_restlist);
                break;
        }
    }
}
