package com.hnib.kisoccer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.hnib.kisoccer.R;
import com.hnib.kisoccer.Utils.Constants;
import com.hnib.kisoccer.model.Fixture;
import com.hnib.kisoccer.model.JsonFixturesResponse;
import com.hnib.kisoccer.network.VolleySingleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caucukien on 28/11/2015.
 */
public class SplashActivity extends Activity implements VolleySingleton.OnNetworkResponse {


    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);    // Removes title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        VolleySingleton.getInstance().callJsonStringRequest(Constants.url);
        VolleySingleton.getInstance().setOnNetWorkResponse(this);
    }





    @Override
    public void onResponseSucces() {

            startActivity(new Intent(this,MainActivity.class));
            finish();
    }

    @Override
    public void onResponseError() {

    }
}


