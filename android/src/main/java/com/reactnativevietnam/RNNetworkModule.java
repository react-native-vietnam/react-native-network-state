package com.reactnativevietnam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.provider.Settings;
import android.util.Log;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anh Tuan Nguyen
 * @created 8/8/2018
 */
public class RNNetworkModule extends ReactContextBaseJavaModule {
    private ReactApplicationContext mReactContext = null;

    public RNNetworkModule(ReactApplicationContext reactContext) {
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
        } else {
            Log.e("RNNetworkError", "Missing context");
        }
    }

    @Override
    public String getName() {
        return "RNNetworkState";
    }

    @ReactMethod
    public void openWifi() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        try {
            ConnectivityManager manager = (ConnectivityManager) getReactApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = manager.getActiveNetworkInfo();
            Boolean isFast = netInfo != null ? NetworkReceiver.isConnectionFast(netInfo.getType(), netInfo.getSubtype()) : false;
            if (netInfo != null && netInfo.isConnected() && netInfo.isAvailable()) {
                constants.put("isConnected", true);
                constants.put("type", netInfo.getTypeName());
                constants.put("isFast", isFast);
            } else {
                constants.put("isConnected", false);
                constants.put("type", netInfo != null ? netInfo.getTypeName() :  "unknown");
                constants.put("isFast", isFast);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return constants;
    }

    private void sendEvent(String eventName, @Nullable WritableMap params) {
        if (mReactContext != null) {
            mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
        }
    }
}
