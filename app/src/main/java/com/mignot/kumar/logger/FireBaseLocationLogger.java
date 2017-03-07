package com.mignot.kumar.logger;

import android.support.annotation.NonNull;
import com.google.firebase.database.DatabaseReference;
import com.mignot.kumar.models.LocationEntry;

/**
 * A LocationLogger implementation that logs LocationEntry
 * objects to a FireBase Database
 * Implemented as a lazy Singleton class as we won't ever need or want
 * more than one of these
 *
 * @see LocationLogger
 */
public class FireBaseLocationLogger implements LocationLogger {
  private static final String LOG_ROOT = "location-logs";
  private static FireBaseLocationLogger _instance = null;
  private static DatabaseReference dr = null;

  /**
   * Standard Singleton pattern "getInstance" method.
   *
   * @param dbRef the DatabaseReference object we'll be using
   * @return FireBaseLocationLogger instance
   */
  public static FireBaseLocationLogger getInstance(DatabaseReference dbRef) {
    if (dr == null) dr = dbRef;
    if (_instance == null) _instance = new FireBaseLocationLogger();
    return _instance;
  }

  private FireBaseLocationLogger() {}

  /**
   * @see LocationLogger#log(LocationEntry)
   */
  @Override
  public void log(@NonNull LocationEntry entry) {
    // get a new key from FireBase for this entry
    String newId = dr.push().getKey();
    // and log it
    dr.child(LOG_ROOT)
      .child(newId)
      .setValue(entry);
  }
}