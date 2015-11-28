package com.hnib.kisoccer.network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hnib.kisoccer.Utils.Constants;
import com.hnib.kisoccer.activity.KiSoccerApplication;
import com.hnib.kisoccer.model.Fixture;
import com.hnib.kisoccer.model.JsonFixturesResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caucukien on 23/11/2015.
 */
public class VolleySingleton {

    public interface OnNetworkResponse{
        public void onResponseSucces();
        public void onResponseError();
    }
    private OnNetworkResponse onNetworkResponse;

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

    public void callJsonStringRequest(String url){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                JsonFixturesResponse jsonFixturesResponse = gson.fromJson(response, JsonFixturesResponse.class);
                List<Fixture> fixtures = jsonFixturesResponse.getFixtures();
                KiSoccerApplication.getInstance().setFixtures(fixtures);
                onNetworkResponse.onResponseSucces();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d(TAG, "" + error.getMessage() + "," + error.toString());
                onNetworkResponse.onResponseError();

            }
        }){


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<String, String>();
                headers.put("X-Auth-Token", Constants.API_KEY_FOOTBALl);
                return headers;
            }
        };



        VolleySingleton.getInstance().getRequestQueue().add(stringRequest);
    }

    public void setOnNetWorkResponse(OnNetworkResponse onNetworkResponse){
        this.onNetworkResponse = onNetworkResponse;
    }

}
