package com.mignot.kumar.models;

import android.support.annotation.NonNull;

/**
 * Simple model of a LocationEntry
 * Fulfils requirements that we log the retrieved location and a timestamp
 */
final public class LocationEntry {
  private final Double longitude;
  private final Double latitude;
  private final String timestamp;

  public LocationEntry(@NonNull Double lat,
                       @NonNull Double lon,
                       @NonNull String ts) {

    longitude = lon;
    latitude = lat;
    timestamp = ts;
  }

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public String getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "Longitude: " + longitude + " Latitude: " + latitude + " Timestamp: " + timestamp;
  }
}
