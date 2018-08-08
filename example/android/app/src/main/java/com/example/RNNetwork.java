package com.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class RNNetwork extends ReactContextBaseJavaModule {
    private ReactApplicationContext mReactContext = null;

    public RNNetwork(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
        IntentFilter intentFilter = new IntentFilter("RNNetworkState");
        if (mReactContext != null) {
            mReactContext.registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Boolean v = intent.getBooleanExtra("isConnected", false);
                    String type = intent.getStringExtra("type");
                    Boolean isFast = intent.getBooleanExtra("isFast", true);
                    WritableMap params = Arguments.createMap();
                    params.putBoolean("isConnected", v.booleanValue());
                    params.putString("type", type);
                    params.putBoolean("isFast", isFast);

                    sendEvent("networkChanged", params);
                }
            }, intentFilter);
        }
    }

    @Override
    public String getName() {
        return "RNNetworkState";
    }

    private void sendEvent(String eventName, @Nullable WritableMap params) {
        if (mReactContext != null) {
            mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
        }
    }
}
