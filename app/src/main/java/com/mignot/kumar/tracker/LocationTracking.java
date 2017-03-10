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

  private IALocationListener mLocationListener;

  public LocationTracking (LocationLogger newLogger, IALocationManager newLocMan) {
    mLocationLogger = newLogger;
    mLocationManager = newLocMan;

    mLocationListener = new IALocationListener() {
      @Override
      public void onLocationChanged(IALocation location) {
        mLocationLogger.log(new LocationEntry (
            location.getLatitude(),
            location.getLongitude(),
            Calendar.getInstance().toString())
        );
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
    mLocationManager.requestLocationUpdates(
        IALocationRequest
        .create()
        .setFastestInterval(LOG_INTERVAL),
        mLocationListener
    );
  }

  /**
   * Stop tracking locations
   */
  public void stop() {
    mLocationManager.removeLocationUpdates(mLocationListener);
  }

  /**
   * Clean up any references
   */
  public void onDestroy() {
    mLocationLogger.onDestroy();
    mLocationManager.destroy();
    mLocationListener = null;
  }
}
