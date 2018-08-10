package com.reactnativevietnam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.net.Uri;
import android.provider.Settings;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

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
        }
    }

    @Override
    public String getName() {
        return "RNNetworkState";
    }

    @ReactMethod
    public void openGeneral() {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void openAppDetails() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.setData(Uri.parse("package:" + mReactContext.getPackageName()));
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
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

    @ReactMethod
    public void openLocationSource() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void openWireless() {
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void openAirplaneMode() {
        Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void openApn() {
        Intent intent = new Intent(Settings.ACTION_APN_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void openBluetooth() {
        Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void openDate() {
        Intent intent = new Intent(Settings.ACTION_DATE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void openLocale() {
        Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void openInputMethod() {
        Intent intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void openDisplay() {
        Intent intent = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void openPrivacy() {
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void openInternalStorage() {
        Intent intent = new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void openMemoryCard() {
        Intent intent = new Intent(Settings.ACTION_MEMORY_CARD_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void openAccessibility() {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void openApplication() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void openDeviceInfo() {
        Intent intent = new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        if (intent.resolveActivity(mReactContext.getPackageManager()) != null) {
            mReactContext.startActivity(intent);
        }
    }

    private void sendEvent(String eventName, @Nullable WritableMap params) {
        if (mReactContext != null) {
            mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
        }
    }
}
