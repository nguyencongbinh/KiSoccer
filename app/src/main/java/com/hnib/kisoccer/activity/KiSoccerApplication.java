package com.hnib.kisoccer.activity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hnib.kisoccer.model.Fixture;

import java.util.List;

/**
 * Created by caucukien on 24/11/2015.
 */
public class KiSoccerApplication extends Application {

    private static KiSoccerApplication instance;
    private List<Fixture> fixtures;



    public static KiSoccerApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }



    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static void saveToPreferences(Context context, String preferenceName, boolean preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    public static boolean readFromPreferences(Context context, String preferenceName, boolean defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getBoolean(preferenceName, defaultValue);
    }
}
