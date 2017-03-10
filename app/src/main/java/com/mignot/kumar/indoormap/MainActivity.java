package com.mignot.kumar.indoormap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;
import com.indooratlas.android.sdk.IALocationManager;
import com.mignot.kumar.logger.FireBaseLocationLogger;
import com.mignot.kumar.models.LocationEntry;
import com.mignot.kumar.tracker.LocationTracking;

public class MainActivity extends AppCompatActivity {
  private final static String DATABASE_REF = "location-logs";
  private LocationTracking tracker;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    tracker = new LocationTracking (
        FireBaseLocationLogger.getInstance(FirebaseDatabase.getInstance().getReference(DATABASE_REF)),
        IALocationManager.create(this)
    );

    tracker.start();

    tracker.stop();
  }

  @Override
  protected void onDestroy() {
    tracker.onDestroy();
    super.onDestroy();
  }
}
