package com.realidentity.ali;

import android.util.Log;

import com.alibaba.security.cloud.CloudRealIdentityTrigger;
import com.alibaba.security.realidentity.ALRealIdentityCallback;
import com.alibaba.security.realidentity.ALRealIdentityResult;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;

public class RealIdentityModule extends ReactContextBaseJavaModule {
    private static final String TAG = RealIdentityModule.class.getSimpleName();
    private final ReactApplicationContext reactContext;

    public RealIdentityModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;

    }

    @Override
    public String getName() {
        return "RealIdentity";
    }

    @ReactMethod
    public void start(String verifyToken, Promise promise) {
        try {
            Log.d(TAG, "CloudRealIdentityTrigger start:" + verifyToken);
            CloudRealIdentityTrigger.start(getReactApplicationContext(), "XXX#/", getALRealIdentityCallback());
            promise.resolve(verifyToken);
        } catch (IllegalViewOperationException e) {
            Log.d(TAG,e.getMessage());
            promise.reject(e);
        }

    }

    /**
     * 基础回调的方式 TODO
     *
     * @return
     */
    private ALRealIdentityCallback getALRealIdentityCallback() {
        return new ALRealIdentityCallback() {
            @Override
            public void onAuditResult(ALRealIdentityResult alRealIdentityResult, String s) {
                //DO your things
                Log.d("RPSDK","ALRealIdentityResult:"+alRealIdentityResult.audit);
            }
        };
    }
}
