
package com.reactnativevietnam;

import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

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
          ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
          NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
          boolean isConnected = activeNetwork != null &&
                                activeNetwork.isConnectedOrConnecting();
          handler!!.postDelayed(runnable, 2000);
        }
    };
    handler!!.post(runnable);
  }

  @Override
  public String getName() {
    return "RNNetworkState";
  }

  /**
  * Send Events to Javascript
  * @param reactContext
  * @param eventName
  * @param params
  */
  private void sendEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params) {
    if (reactContext.hasActiveCatalystInstance()) {
      Log.i("sendEvent", params.toString());
      reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
    } else {
      Log.i("sendEvent", "Waiting for CatalystInstance...");
    }
  }
}