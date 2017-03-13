package com.mignot.kumar.indoormap.logger;

import android.support.annotation.NonNull;
import com.google.firebase.database.DatabaseReference;
import com.mignot.kumar.indoormap.models.Location;

/**
 * A LocationLogger implementation that logs LoggableLocation
 * objects to a FireBase Database
 * Implemented as a lazy Singleton class as we won't ever need or want
 * more than one of these
 *
 * @see LocationLogger
 */
public class FireBaseLocationLogger implements LocationLogger {
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
   * @see LocationLogger#log(Location)
   */
  @Override
  public String log(@NonNull Location entry) {
    String newId = dr.push().getKey();
    dr.child(newId).setValue(entry);

    return newId;
  }

  /**
   * @see LocationLogger#onDestroy()
   */
  @Override
  public void onDestroy() {
    dr = null;
    _instance = null;
  }

}
