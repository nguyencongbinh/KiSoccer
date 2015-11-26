package com.hnib.kisoccer.network;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hnib.kisoccer.activity.KiSoccerApplication;

import org.json.JSONObject;

/**
 * Created by caucukien on 23/11/2015.
 */
public class VolleySingleton {

    private static final String TAG = VolleySingleton.class.getSimpleName();
    private static VolleySingleton instance;
    private RequestQueue requestQueue;
    private VolleySingleton(){
        requestQueue= Volley.newRequestQueue(KiSoccerApplication.getAppContext());
    }
    public static VolleySingleton getInstance(){
        if(instance==null){
            instance = new VolleySingleton();
        }
        return instance;

    }
    public RequestQueue getRequestQueue(){
        return requestQueue;
    }


}
