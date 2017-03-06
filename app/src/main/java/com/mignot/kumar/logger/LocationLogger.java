package com.mignot.kumar.logger;

import android.support.annotation.NonNull;
import com.mignot.kumar.models.LocationEntry;

/**
 * LocationLogger
 */
public interface LocationLogger {

  /**
   * Logs a LocationEntry model to a location
   * tbd by the implementing class
   *
   * @param entry the LocationEntry to be logged
   */
  void log(@NonNull LocationEntry entry);
}
