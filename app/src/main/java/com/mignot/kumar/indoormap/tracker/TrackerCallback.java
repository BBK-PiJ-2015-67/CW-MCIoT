package com.mignot.kumar.indoormap.tracker;

/**
 * Simple interface to define a callback used by the tracker
 */
public interface TrackerCallback {

  /**
   * Executes the callback with the given input
   * @param input Some input string
   */
  void execute (String input);
}
