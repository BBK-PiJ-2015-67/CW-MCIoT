package com.mignot.kumar.indoormap.models;

import android.support.annotation.NonNull;

/**
 * Model a LoggableLocation for database logging
 * Fulfil requirement 1 - log locations every 1 second to database
 */
final public class LoggableLocation implements Location {
  private final Double longitude;
  private final Double latitude;
  private final String timestamp;

  public LoggableLocation(@NonNull Double lat,
                          @NonNull Double lon,
                          @NonNull String ts) {
    longitude = lon;
    latitude = lat;
    timestamp = ts;
  }

  @Override
  public Double getLongitude() {
    return longitude;
  }

  @Override
  public Double getLatitude() {
    return latitude;
  }

  public String getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "Longitude: " + longitude +
        "\nLatitude: " + latitude +
        "\nTimestamp: " + timestamp;
  }
}
