
package com.reactnativevietnam.networkstate;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNNetworkStateModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private Handler handler = null;
  private Runnable runnable = null;

  public RNNetworkStateModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  public void startPing(Int interval) {
    handler = new Handler();
    runnable = new Runnable() {
        @Override
        public void run() {
          Log.d("Handlers", "Called on main thread");
          handler!!.postDelayed(runnable, 2000);
        }
    };
    handler!!.post(runnable);
  }

  @Override
  public String getName() {
    return "RNNetworkState";
  }
}