package com.mignot.kumar.indoormap.logger;

import android.support.annotation.NonNull;
import com.mignot.kumar.indoormap.models.Location;

/**
 * LocationLogger
 * Defines an interface for some class to log LoggableLocation models
 * somewhere
 */
public interface LocationLogger {

  /**
   * Logs a Location model object to a location
   * tbd by the implementing class
   *
   * @param entry the Location object to be logged
   * @return String some identifier associated with this entry
   */
  String log(@NonNull Location entry);

  /**
   * Clean up any references if necessary
   */
  void onDestroy();
}
