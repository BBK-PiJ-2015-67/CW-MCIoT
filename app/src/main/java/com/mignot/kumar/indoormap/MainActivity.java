package com.mignot.kumar.indoormap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.mignot.kumar.indoormap.utils.SingleFragmentActivity;

public class MainActivity extends SingleFragmentActivity {

  @Override
  protected Fragment createFragment() {
    return SimpleTrackingFragment.newInstance();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fragment);
  }

}
