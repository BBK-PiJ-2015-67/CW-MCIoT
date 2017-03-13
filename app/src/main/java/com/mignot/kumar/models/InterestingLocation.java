package com.mignot.kumar.models;

import android.support.annotation.NonNull;

public final class InterestingLocation implements LocationInfo {
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
