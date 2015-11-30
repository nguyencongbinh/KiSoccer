package com.hnib.kisoccer.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.hnib.kisoccer.activity.KiSoccerApplication;
import com.hnib.kisoccer.model.Fixture;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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

    public static String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDateAndTime = sdf.format(new Date());
        return currentDateAndTime;
    }
    public static String getTimeFromDateFixture(String date){
        String result = date.substring(11,16); //get only time from date
        return result;
    }
    public static int compareTwoTime(String time1, String time2){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        int result = -1;
        try {
            Date date1 = sdf.parse(time1);
            Date date2 = sdf.parse(time2);
            result = date1.compareTo(date2);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void sortListFixtureByLeague(List<Fixture> fixtures){

        Collections.sort(fixtures, new Comparator<Fixture>() {
            @Override
            public int compare(Fixture fixture1, Fixture fixture2) {
               String urlLeague1 = fixture1.getLinks().getSoccerSeason().getHref();
                List<String> leagues1 = Arrays.asList(urlLeague1.split("/"));
                String idLeague1 = leagues1.get(leagues1.size()-1);
                Log.d("idLeague1", idLeague1);

                String urlLeague12 = fixture2.getLinks().getSoccerSeason().getHref();
                List<String> leagues2 = Arrays.asList(urlLeague12.split("/"));
                String idLeague2 = leagues2.get(leagues2.size()-1);
                Log.d("idLeague2", idLeague2);

                if (Integer.parseInt(idLeague1) >  Integer.parseInt(idLeague2)){
                    return 1;
                }else if ( Integer.parseInt(idLeague1) < Integer.parseInt(idLeague2) ) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }

}
