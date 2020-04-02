//package com.mlife.googleAnalytics;
//
//import android.app.Application;
//import android.content.Context;
//import android.support.multidex.MultiDex;
//
//import com.example.focpc.mahindralifespaces.utils.MlsApp;
//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.google.android.gms.analytics.Tracker;
//import com.mlife.R;
//
///**
// * Created by milagro on 11/3/2017.
// */
//
//public class AnalyticsApplication  extends MlsApp {
//
//    private static GoogleAnalytics sAnalytics;
//    private static Tracker sTracker;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        sAnalytics = GoogleAnalytics.getInstance(this);
//    }
//
//    /**
//     * Gets the default {@link Tracker} for this {@link Application}.
//     * @return tracker
//     */
//    synchronized public Tracker getDefaultTracker() {
//        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
//        if (sTracker == null) {
//            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
//        }
//
//        return sTracker;
//    }
//
//    @Override
//    protected void attachBaseContext(Context base) {
//        MultiDex.install(base);
//        super.attachBaseContext(base);
//
//    }
//}