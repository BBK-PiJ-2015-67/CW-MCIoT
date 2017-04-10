package com.mignot.kumar.indoormap;

import com.indooratlas.android.sdk.IALocationManager;
import com.mignot.kumar.indoormap.logger.LocationLogger;
import com.mignot.kumar.indoormap.models.Location;
import com.mignot.kumar.indoormap.models.LoggableLocation;
import com.mignot.kumar.indoormap.tracker.LocationTracking;
import com.mignot.kumar.indoormap.tracker.TrackerCallback;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Calendar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * LocationTracking tests
 */
public class LocationTrackingTest {

  @Mock
  LocationLogger mLocationLogger;
  @Mock
  IALocationManager mLocationManager;
  @Mock
  TrackerCallback mTrackerCallback;
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Test
  public void it_should_be_tracking_once_started() {
    LocationTracking locationTracking = new LocationTracking(mLocationLogger, mLocationManager, mTrackerCallback);
    locationTracking.start();
    assertTrue(locationTracking.isTracking());
  }

  @Test
  public void it_should_no_longer_be_tracking_once_stopped() {
    LocationTracking locationTracking = new LocationTracking(mLocationLogger, mLocationManager, mTrackerCallback);
    locationTracking.start();
    locationTracking.stop();
    assertFalse(locationTracking.isTracking());
  }
  
}
