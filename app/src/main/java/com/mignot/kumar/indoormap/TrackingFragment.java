package com.mignot.kumar.indoormap;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.indooratlas.android.sdk.IALocationManager;
import com.mignot.kumar.indoormap.logger.FireBaseLocationLogger;
import com.mignot.kumar.indoormap.models.InterestingLocation;
import com.mignot.kumar.indoormap.models.Location;
import com.mignot.kumar.indoormap.tracker.LocationTracking;
import com.mignot.kumar.indoormap.utils.DistanceCalculator;

public class TrackingFragment extends Fragment {
  private static final String TAG = "IndoorMapTracking";
  private static final String DB_REF = "location-logs";
  private static final double SPECIAL_LAT = 51.52241142692823;
  private static final double SPECIAL_LONG = -0.13065934289975817;

  private LocationTracking mTracker;
  private TextView currentLocation;

  public static Fragment newInstance() { return new TrackingFragment(); }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) {

    View v = inflater.inflate(R.layout.tracking_fragment, container, false);

    Location interestingLocation = new InterestingLocation(
      SPECIAL_LAT,
      SPECIAL_LONG,
      getString(R.string.interesting_location));
    currentLocation = (TextView) v.findViewById(R.id.current_loc);

    mTracker = new LocationTracking(
      FireBaseLocationLogger.getInstance(FirebaseDatabase.getInstance().getReference(DB_REF)),
      IALocationManager.create(super.getActivity()),
      s -> {
        // display the coordinates
        currentLocation.setText(s.toString());
        Log.d(TAG, s.toString());

        // calculate the distance from the "interesting location"
        Float distance = DistanceCalculator.getDistanceBetween(
          s.getLatitude(),
          s.getLongitude(),
          interestingLocation.getLatitude(),
          interestingLocation.getLongitude()
        );

        // show a message if within 3m
        if (distance <= 3) {
          String message = "You are " + distance + " from " +
            ((InterestingLocation) interestingLocation).getName();
          Toast
            .makeText(this.getContext(), message, Toast.LENGTH_SHORT)
            .show();
        }
      }
    );

    // enable the start button
    v.findViewById(R.id.start_loc_log).setEnabled(true);
    // initialise button listeners
    initButtonListeners((Button) v.findViewById(R.id.start_loc_log),
                (Button) v.findViewById(R.id.stop_loc_log));

    return v;
  }

  @Override
  public void onResume() {
    super.onResume();

  }

  @Override
  public void onPause() {
    // if (mTracker.isTracking())
    mTracker.stop();
    super.onPause();
  }

  @Override
  public void onDestroy() {
    mTracker.onDestroy();
    super.onDestroy();
  }

  /**
   * Initialises the start and stop button listeners
   * @param start The "start tracking" Button
   * @param stop The "stop tracking" Button
   */
  private void initButtonListeners(Button start, Button stop) {
    start.setOnClickListener(l -> {
      mTracker.start();
      start.setEnabled(false);
      stop.setEnabled(true);
    });
    stop.setOnClickListener(l -> {
      mTracker.stop();
      stop.setEnabled(false);
      start.setEnabled(true);
    });
  }
}
