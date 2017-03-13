package com.mignot.kumar.indoormap.models;

import java.io.Serializable;

/**
 * Location information
 */
public interface Location extends Serializable {
  Double getLongitude();

  Double getLatitude();
}
