package com.reactnativevietnam;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;

import com.facebook.react.ReactApplication;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Anh Tuan Nguyen
 * @created 8/8/2018
 */
public class RNNetworkModule extends ReactContextBaseJavaModule {
    private Boolean isConnected = false;
    private  ReactApplicationContext mContext = null;
    public RNNetworkModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
        ReactiveNetwork
                .observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean mIsConnected) throws Exception {
                        if(isConnected == mIsConnected) {
                            return;
                        }
                        isConnected = mIsConnected;
                        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo netInfo = manager.getActiveNetworkInfo();
                        Boolean isFast = netInfo != null ? RNNetworkModule.isConnectionFast(netInfo.getType(), netInfo.getSubtype()) : false;
                        String type = netInfo != null ? netInfo.getTypeName() : "unkown";

                        WritableMap params = Arguments.createMap();
                        params.putBoolean("isConnected", mIsConnected);
                        params.putString("type", type);
                        params.putBoolean("isFast", isFast);
                        sendEvent("networkChanged", params);
                    }
                });
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
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        }
    }

    @ReactMethod
    public void reload() {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ReactApplication app = (ReactApplication)mContext.getCurrentActivity().getApplication();
                app.getReactNativeHost().getReactInstanceManager().recreateReactContextInBackground();
            }
        });
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        try {
            ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = manager.getActiveNetworkInfo();
            Boolean isFast = netInfo != null ? RNNetworkModule.isConnectionFast(netInfo.getType(), netInfo.getSubtype()) : false;
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
        if (mContext == null || !mContext.hasActiveCatalystInstance()) {
            return;
        }
        mContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
    }

    public static boolean isConnectionFast(int type, int subType) {
        if (type == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        if (type == ConnectivityManager.TYPE_MOBILE) {
            switch (subType) {
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return false; // ~ 14-64 kbps
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    return true; // ~ 400-1000 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    return true; // ~ 600-1400 kbps
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return false; // ~ 100 kbps
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return true; // ~ 2-14 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return true; // ~ 700-1700 kbps
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    return true; // ~ 1-23 Mbps
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    return true; // ~ 400-7000 kbps
                /*
                 * Above API level 7, make sure to set android:targetSdkVersion
                 * to appropriate level to use these
                 */
                case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
                    return true; // ~ 1-2 Mbps
                case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
                    return true; // ~ 5 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                    return true; // ~ 10-20 Mbps
                case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
                    return false; // ~25 kbps
                case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                    return true; // ~ 10+ Mbps
                // Unknown
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                default:
                    return false;
            }
        }
        return false;
    }
}