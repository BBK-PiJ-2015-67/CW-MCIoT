package com.mignot.kumar.indoormap.models;

import android.support.annotation.NonNull;

/**
 * Model a location of interest.
 * Satisfies requirement 2 - show a message if user
 * is within 3m of the interesting location
 */
public final class InterestingLocation implements Location {
  private final Double longitude;
  private final Double latitude;
  private final String name;

  public InterestingLocation(@NonNull Double lat,
                             @NonNull Double lon,
                             @NonNull String newName) {
    longitude = lon;
    latitude = lat;
    name = newName;
  }

  @Override
  public Double getLongitude() {
    return longitude;
  }

  @Override
  public Double getLatitude() {
    return latitude;
  }

  public String getName() {
    return name;
  }
}
