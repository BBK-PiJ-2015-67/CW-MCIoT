package com.mignot.kumar.indoormap;

import com.mignot.kumar.models.LocationEntry;
import com.mignot.kumar.utils.DistanceCalculator;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * LocationEntry tests
 */
public class LocationEntryTest {

  private static final Double LAT = 0.54676;
  private static final Double LONG = -1.24545;
  private static final String TIMESTAMP = Calendar.getInstance().toString();
  private static final String EXPECTED_STRING =
      "Longitude: " + LONG + " Latitude: " + LAT + " Timestamp: " + TIMESTAMP;

  /**
   * Testing toString() method
   */
  @Test
  public void it_should_return_expected_string_if_toString_is_called() {
    LocationEntry entry = new LocationEntry(LAT, LONG, TIMESTAMP);
    assertEquals(EXPECTED_STRING, entry.toString());
  }

}