package edu.uga.cs.ideafragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

public class CuisineTypeListFragment extends ListFragment {

    // a TAG string for logcat messages identification
    private static final String TAG = "Cuisine";

    private String[] cuisineList = {
            "American",
            "Asian",
            "Italian",
            "Mexican",
            "Vegetarian",
            "Sweets"
    };

    // indicate if this is a landscape mode with both fragments present or not
    boolean twoFragmentsActivity = false;

    // list selection index
    int cuisineIndex = 0;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "CuisineTypeListFragment.onActivityCreated()" + savedInstanceState);

        // create a new ArrayAdapter for the list
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_activated_1, cuisineList));

        // set the twoFragmentsActivity variable to true iff we are in 2 fragment (landscape) view
        View detailsFrame = getActivity().findViewById(R.id.cuisineInfo);
        Log.d(TAG, "CuisineTypeListFragment.onActivityCreated(): detailsFrame: " + detailsFrame);

        twoFragmentsActivity = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        // restore the saved list index selection (Android version no), if available
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            cuisineIndex = savedInstanceState.getInt("CuisineTypeListFragment", 0);
            Log.d(TAG, "CuisineTypeListFragment.onActivityCreated(): restored versionIndex: " + cuisineIndex);
        }

        // set the list mode as single choice and
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setItemChecked(cuisineIndex, true);

        // if we are in 2 fragment (landscape) orientation, show the Android version information on the right side
        // This class will act as the "driver" here by displaying the details using the other fragment.
        if (twoFragmentsActivity) {

            // display the information about the selected Android version in the fragment on the right (details)
            aboutCuisine(cuisineIndex);

            // The list in the landscape orientation may be shorter and the selected item
            // which was visible in portrait mode might be invisible (out of view)
            // i.e., "below" the end of the screen in landscape orientation.
            // To make it visible again, call smoothScrollToPosition
            getListView().smoothScrollToPosition(cuisineIndex);
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // on a click on a list item, show the selected Android version info
        // store the list view and selection for coming back to the list (using the back button)
        //firstVisibleVersionIndex = l.getFirstVisiblePosition();
        //versionIndex = position;
        aboutCuisine(position);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save the list index selection
        outState.putInt("CuisineTypeListFragment", cuisineIndex);
        Log.d(TAG, "CuisineTypeListFragment.onSaveInstanceState(): saved versionIndex: " + cuisineIndex);
    }

    void aboutCuisine(int cuisineIndex) {
        this.cuisineIndex = cuisineIndex;

        if (twoFragmentsActivity) {  // only in the 2 fragment (landscape) orientation

            getListView().setItemChecked(cuisineIndex, true);

            // get the placeholder element (FrameLayout) in a 2 fragment (landscape) orientation layout
            RestaurantListFragment details =
                    (RestaurantListFragment) getFragmentManager().findFragmentById(R.id.cuisineInfo);

            Log.d(TAG, "CuisineTypeListFragment.showAndroidVersionInfo(): details: " + details);
            if (details == null || details.getShownVersionIndex() != cuisineIndex) {

                // obtain a new Android info fragment instance
                details = RestaurantListFragment.newInstance(cuisineIndex);

                // replace the placeholder with the new fragment stance within a transaction
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.cuisineInfo, details);

                // TRANSIT_FRAGMENT_FADE means that the fragment should fade in or fade out
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                // commit the transaction, i.e. make the changes
                fragmentTransaction.commit();
            }
        } else {
            // In a 1 fragment orientation (portrait), start a new activity using an Intent, as in the previous demo app
            Intent intent = new Intent();
            intent.setClass(getActivity(), RestaurantListActivity.class);
            intent.putExtra("cuisineIndex", cuisineIndex);

            startActivity(intent);
        }
    }

    // These list fragment callback methods are not needed and are for educational purposes only

    @Override
    public void onAttach(Activity activity) {
        Log.d(TAG, "CuisineTypeListFragment.onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "CuisineTypeListFragment.onCreate()");
        super.onCreate(savedInstanceState);

        // IMPORTANT: this method call will prevent this fragment from being destroyed when
        // recreating the hosting activity. Consequently, the list will be retained.
        //setRetainInstance( true );
    }

    @Override
    public void onStart() {
        Log.d(TAG, "CuisineTypeListFragment.onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "CuisineTypeListFragment.onResume(): "
                + cuisineIndex);
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "CuisineTypeListFragment.onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "CuisineTypeListFragment.onStop()");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "CuisineTypeListFragment.onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "CuisineTypeListFragment.onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "CuisineTypeListFragment.onDetach()");
        super.onDetach();
    }
}