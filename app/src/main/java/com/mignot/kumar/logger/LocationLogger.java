package com.mignot.kumar.logger;

import android.support.annotation.NonNull;
import com.mignot.kumar.models.LocationEntry;

/**
 * LocationLogger
 * Defines an interface for some class to log LocationEntry models
 * somewhere
 */
public interface LocationLogger {

  /**
   * Logs a LocationEntry model object to a location
   * tbd by the implementing class
   *
   * @param entry the LocationEntry object to be logged
   */
  void log(@NonNull LocationEntry entry);
}
