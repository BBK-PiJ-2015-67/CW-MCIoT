package com.mignot.kumar.indoormap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.mignot.kumar.indoormap.utils.SingleFragmentActivity;

public class IndoorMapActivity extends SingleFragmentActivity {

  private static final String TAG = "IndoorMap";
  private static final int CODE_COARSE_LOCATION_PERM = 99;

  @Override
  protected Fragment createFragment() {
    return TrackingFragment.newInstance();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_fragment);

    checkPermissions();
  }

  private void checkPermissions() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
      PackageManager.PERMISSION_GRANTED) {

      Log.d(TAG, "App doesn't have access to COARSE_LOCATION");

      // do we need to give an explanation as to why we need permission?
      if (ActivityCompat.shouldShowRequestPermissionRationale(this,
        Manifest.permission.ACCESS_COARSE_LOCATION)) {

        // show a dialog explaining why we need access to Coarse Location
        new AlertDialog.Builder(this)
          .setTitle(R.string.location_perm_req_title)
          .setMessage(R.string.location_perm_req_message)
          .setPositiveButton(R.string.button_accept, (dialog, which) -> {
            Log.d(TAG, "Requesting COARSE_LOCATION permission");

            ActivityCompat.requestPermissions(this,
              new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
              CODE_COARSE_LOCATION_PERM);
          })
          .setNegativeButton(R.string.button_deny, (dialog, which) -> {
            Log.d(TAG, "COARSE_LOCATION permission denied");

            Toast.makeText(this,
              R.string.location_perm_denied_message,
              Toast.LENGTH_LONG).show();
          })
          .show();

      } else {
        ActivityCompat.requestPermissions(this,
          new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
          CODE_COARSE_LOCATION_PERM);

      }
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    switch (requestCode) {
      case CODE_COARSE_LOCATION_PERM:
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
          Log.d(TAG, "COARSE_LOCATION permission denied");
          Toast.makeText(this,
            R.string.location_perm_denied_message,
            Toast.LENGTH_LONG).show();
        }
    }
  }
}
