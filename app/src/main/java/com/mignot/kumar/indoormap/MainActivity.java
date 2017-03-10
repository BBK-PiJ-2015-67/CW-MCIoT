package com.mignot.kumar.indoormap;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class MainActivity extends SingleFragmentActivity {

  @Override
  protected Fragment createFragment() {
    return new SimpleTrackingFragment();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_container);
  }

}
