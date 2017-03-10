package com.mignot.kumar.indoormap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.firebase.database.FirebaseDatabase;
import com.indooratlas.android.sdk.IALocationManager;
import com.mignot.kumar.logger.FireBaseLocationLogger;
import com.mignot.kumar.tracker.LocationTracking;

public class SimpleTrackingFragment extends Fragment {
    private static final String DB_REF = "location-logs";

    private LocationTracking mTracker;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTracker = new LocationTracking(
            FireBaseLocationLogger.getInstance(FirebaseDatabase.getInstance().getReference(DB_REF)),
            IALocationManager.create(super.getActivity()),
            (s) -> Log.d(this.getClass().getSimpleName(), s)
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_fragment, container, false);

        Button mStartButton = (Button) v.findViewById(R.id.start_loc_log);
        Button mStopButton = (Button) v.findViewById(R.id.stop_loc_log);

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
