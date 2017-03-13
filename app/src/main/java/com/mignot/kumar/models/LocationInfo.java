package com.mignot.kumar.models;

import java.io.Serializable;

public interface LocationInfo extends Serializable {
  Double getLongitude();

  Double getLatitude();
}
