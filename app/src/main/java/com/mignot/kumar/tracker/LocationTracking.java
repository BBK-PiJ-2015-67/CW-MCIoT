package com.mignot.kumar.tracker;

import android.os.Bundle;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;
import com.mignot.kumar.logger.LocationLogger;
import com.mignot.kumar.models.LocationEntry;

import java.util.Calendar;

/**
 * Handle location tracking
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

    mLocationListener = new IALocationListener() {
      @Override
      public void onLocationChanged(IALocation location) {
        LocationEntry entry = new LocationEntry (
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
   * Start tracking locations and logging them
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

  public boolean isTracking () {
    return isTracking;
  }
}
