package edu.uga.cs.ideafragments;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.RawRes;
import androidx.fragment.app.Fragment;

import java.io.InputStream;

public class RestaurantListFragment extends Fragment {
    private static final String TAG = "Cuisine";

    // This method is similar to the factory method design pattern
    // to create new instances of this fragment.
    // There is a specific reason for having this method, though.  We want to send some data (VersionIndex, here) into the
    // new fragment.  Android disallows overloaded constructors for fragments, and so we can't create a Fragment with
    // the versionIndex as argument.  But we can use the Bundle and send the data this way.  Also, the setArguments call
    // must happen BEFORE the fragment is attached an activity.
    public static RestaurantListFragment newInstance(int cuisineIndex) {
        Log.d(TAG, "RestaurantListFragment.newInstance(): versionIndex: " + cuisineIndex);

        // this uses the default constructor (not defined in this class, but Java-supplied)
        RestaurantListFragment fragment = new RestaurantListFragment();
        Log.d(TAG, "RestaurantListFragment.newInstance(): fragment: " + fragment);

        // save the selected list versionIndex in the new fragment's Bundle data
        // the AndroidVersionInfoFragment needs to know the version to display
        Bundle args = new Bundle();
        args.putInt("cuisineIndex", cuisineIndex);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "RestaurantListFragment.onCreateView()");

        // Programmatically, create a scrollable TextView to show the Android version information
        ScrollView scroller = new ScrollView(getActivity());
        TextView textView = new TextView(getActivity());
        scroller.addView(textView);

        // Set the padding for the new TextView
        // These padding attributes are normally defined in the XML file
        // here, they are set programmatically.
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getActivity().getResources().getDisplayMetrics());
        textView.setPadding(padding, padding, padding, padding);

        // set the text size
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);

        // show the android version info
        viewRestaurants(getShownVersionIndex(), textView);

        return scroller;
    }


    /**
     * Sets proper raw file to the textview
     *
     * @param view           textView
     * @param cuisineRawFile raw file that corresponds to selection
     */
    protected void setRawText(TextView view, @RawRes int cuisineRawFile) {
        try {
            InputStream rawView = this.getResources().openRawResource(cuisineRawFile);

            byte[] cuisineView = new byte[rawView.available()];
            rawView.read(cuisineView);
            view.setText(Html.fromHtml(new String(cuisineView)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * sets the texView based on what option is selected from the spinner and updates the action bar accordingly
     *
     * @param index
     * @param textView
     */

    protected void viewRestaurants(int index, TextView textView) {
        switch (index) {
            case 0:
                setRawText(textView, R.raw.americanrec);
                textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.american, 0, 0);
                Linkify.addLinks(textView, Linkify.ALL);
                break;
            case 1:
                setRawText(textView, R.raw.asianrec);
                textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.asian, 0, 0);
                Linkify.addLinks(textView, Linkify.ALL);
                break;
            case 2:
                setRawText(textView, R.raw.italianrec);
                textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.italian, 0, 0);
                Linkify.addLinks(textView, Linkify.ALL);
                break;
            case 3:
                setRawText(textView, R.raw.mexicanrec);
                textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.mexican, 0, 0);
                Linkify.addLinks(textView, Linkify.ALL);
                break;
            case 4:
                setRawText(textView, R.raw.vegetarianrec);
                textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.vegetarian, 0, 0);
                Linkify.addLinks(textView, Linkify.ALL);
                break;
            case 5:
                setRawText(textView, R.raw.dessert);
                textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.dessert, 0, 0);
                Linkify.addLinks(textView, Linkify.ALL);
                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d(TAG, "RestaurantListFragment.onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "RestaurantListFragment.onCreate() ");
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate(): " + getShownVersionIndex());
    }

    @Override
    public void onStart() {
        Log.d(TAG, "RestaurantListFragment.onStart(): " + getShownVersionIndex());
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "RestaurantListFragment.onResume():" + getShownVersionIndex());
    }

    @Override
    public void onPause() {
        Log.d(TAG, "RestaurantListFragment.onPause(): " + getShownVersionIndex());
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "RestaurantListFragment.onStop(): " + getShownVersionIndex());
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "RestaurantListFragment.onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "RestaurantListFragment.onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "RestaurantListFragment.onDetach()");
        super.onDetach();
    }

    public int getShownVersionIndex() {

        return getArguments().getInt("cuisineIndex", 0);
    }
}
