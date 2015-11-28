package com.hnib.kisoccer.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.hnib.kisoccer.activity.KiSoccerApplication;

/**
 * Created by caucukien on 28/11/2015.
 */
public class CommonUtils {

    public static boolean isNetWorkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) KiSoccerApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
