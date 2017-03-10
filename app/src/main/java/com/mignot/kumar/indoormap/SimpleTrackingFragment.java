package com.mignot.kumar.indoormap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.indooratlas.android.sdk.IALocationManager;
import com.mignot.kumar.logger.FireBaseLocationLogger;
import com.mignot.kumar.tracker.LocationTracking;

import org.w3c.dom.Text;

public class SimpleTrackingFragment extends Fragment {
    private static final String DB_REF = "location-logs";

    private LocationTracking mTracker;
    private TextView mCurrentLocation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.simple_tracker_fragment, container, false);

        mCurrentLocation = (TextView) v.findViewById(R.id.current_loc);
        mTracker = new LocationTracking(
                FireBaseLocationLogger.getInstance(FirebaseDatabase.getInstance().getReference(DB_REF)),
                IALocationManager.create(super.getActivity()),
                (s) -> mCurrentLocation.setText(s)
        );

        Button mStartButton = (Button) v.findViewById(R.id.start_loc_log);
        mStartButton.setBackgroundColor(Color.GREEN);
        Button mStopButton = (Button) v.findViewById(R.id.stop_loc_log);
        mStopButton.setBackgroundColor(Color.RED);

        mStartButton.setOnClickListener(l -> mTracker.start());
        mStopButton.setOnClickListener(l -> mTracker.stop());
        return v;
    }

    @Override
    public void onDestroy() {
        mTracker.onDestroy();
        super.onDestroy();
    }
}
