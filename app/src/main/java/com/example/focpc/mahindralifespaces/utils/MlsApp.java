package com.example.focpc.mahindralifespaces.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.LruCache;

import com.example.focpc.mahindralifespaces.api.MLSApiInterface;
import com.example.focpc.mahindralifespaces.api.MLSNetworkConfig;

/**
 * Created by foc pc on 04-12-2017.
 */

public class MlsApp extends Application {
    private static MLSApiInterface mlsApiInterface;
    private static MlsApp instance;
    private static LruCache<String, Typeface> sTypefaceCache;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MLSNetworkConfig mlsNetworkConfig = new MLSNetworkConfig(MlsConstants.BASE_URL,true);
        mlsApiInterface = mlsNetworkConfig.createRetrofitAdapter();
    }

    public static MLSApiInterface getMlsService() {
        return mlsApiInterface;
    }

    public static Context getContext() {
        return instance;
    }

    public static LruCache<String, Typeface> getTypeFaceCache(){

        if (sTypefaceCache == null){
            sTypefaceCache = new LruCache<String, Typeface>(12);
        }
        return sTypefaceCache;

    }


}