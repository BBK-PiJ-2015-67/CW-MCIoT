package com.mignot.kumar.indoormap.tracker;

import com.mignot.kumar.indoormap.models.Location;

/**
 * Defines a callback to operate on the provided Location
 */
public interface TrackerCallback {

  /**
   * @param input the Location the callback receives as its parameter
   */
  void execute (Location input);
}
