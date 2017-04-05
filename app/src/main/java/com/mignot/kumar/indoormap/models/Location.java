package com.mignot.kumar.indoormap.models;

import java.io.Serializable;

/**
 * Defines a Location with a latitude and longitude
 */
public interface Location extends Serializable {
  Double getLongitude();

  Double getLatitude();
}
