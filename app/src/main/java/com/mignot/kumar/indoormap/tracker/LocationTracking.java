package com.mignot.kumar.indoormap.tracker;

import android.os.Bundle;
import android.util.Log;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;
import com.mignot.kumar.indoormap.logger.LocationLogger;
import com.mignot.kumar.indoormap.models.Location;
import com.mignot.kumar.indoormap.models.LoggableLocation;

import java.util.Calendar;

/**
 * Handle location tracking and logging
 */
public class LocationTracking {
  private static final long LOG_INTERVAL = 1000;
  private static final String TAG = "LocationTracker";

  private final LocationLogger mLocationLogger;
  private final IALocationListener mLocationListener;
  private final IALocationManager mLocationManager;

  private boolean isTracking = false;

  public LocationTracking(LocationLogger newLogger, IALocationManager newLocMan, TrackerCallback cb) {
    mLocationLogger = newLogger;
    mLocationManager = newLocMan;

    mLocationListener = new IALocationListener() {
      @Override
      public void onLocationChanged(IALocation location) {
        Location entry = new LoggableLocation(
            location.getLatitude(),
            location.getLongitude(),
            Calendar.getInstance().getTime().toString());
        mLocationLogger.log(entry);
        cb.execute(entry);
      }
      @Override
      public void onStatusChanged(String s, int i, Bundle bundle) {
        // no-op
      }
    };
  }

  /**
   * Start tracking locations
   */
  public void start() {
    if (!isTracking) {
      mLocationManager.requestLocationUpdates(
        IALocationRequest
          .create()
          .setFastestInterval(LOG_INTERVAL),
        mLocationListener
      );
      isTracking = true;
      Log.d(TAG, "location tracking started");
    }
  }

  /**
   * Stop tracking locations
   */
  public void stop() {
    if (isTracking) {
      mLocationManager.removeLocationUpdates(mLocationListener);
      isTracking = false;
      Log.d(TAG, "location tracking stopped");
    }
  }

  /**
   * Clean up any references
   */
  public void onDestroy() {
    mLocationLogger.onDestroy();
    mLocationManager.destroy();
  }

  /**
   * Check if location tracking is active
   * @return true or false if tracking is active
   */
  public boolean isTracking() {
    return isTracking;
  }
}
