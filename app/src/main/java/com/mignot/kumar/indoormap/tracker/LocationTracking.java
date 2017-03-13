package com.mignot.kumar.indoormap.tracker;

import android.os.Bundle;

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
  private final static long LOG_INTERVAL = 1000;

  private final LocationLogger mLocationLogger;
  private final IALocationManager mLocationManager;
  private final IALocationListener mLocationListener;

  private boolean isTracking = false;

  public LocationTracking (LocationLogger newLogger, IALocationManager newLocMan, TrackerCallback cb) {
    mLocationLogger = newLogger;
    mLocationManager = newLocMan;

    // listener to handle changes in current location
    // when tracking is active
    // logs location updates to firebase and executes the callback
    // so the view can update with the current location
    mLocationListener = new IALocationListener() {
      @Override
      public void onLocationChanged(IALocation location) {
        Location entry = new LoggableLocation(
            location.getLatitude(),
            location.getLongitude(),
            Calendar.getInstance().getTime().toString());
        mLocationLogger.log(entry);
        cb.execute(entry.toString());
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
    }
  }

  /**
   * Stop tracking locations
   */
  public void stop() {
    if (isTracking) {
      mLocationManager.removeLocationUpdates(mLocationListener);
      isTracking = false;
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
  public boolean isTracking () {
    return isTracking;
  }
}
