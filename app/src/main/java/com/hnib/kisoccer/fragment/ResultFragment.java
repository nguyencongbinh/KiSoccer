package com.hnib.kisoccer.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.hnib.kisoccer.R;
import com.hnib.kisoccer.Utils.Constants;
import com.hnib.kisoccer.adapter.RecycleViewAdapter;
import com.hnib.kisoccer.model.Fixture;
import com.hnib.kisoccer.model.JsonFixturesResponse;
import com.hnib.kisoccer.network.VolleySingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    public final String TAG = ResultFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
    private List<Fixture> fixtures = new ArrayList<>();
    private String jsonResponse;
    private ProgressDialog pDialog;


    public ResultFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //create progress Dialog
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycleView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
         recycleViewAdapter = new RecycleViewAdapter();
        recycleViewAdapter.setFixtures(fixtures);
        recyclerView.setAdapter(recycleViewAdapter);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        makeJsonObjectRequest(Constants.url);
    }

    public void makeJsonObjectRequest(String url){


        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hideDialog();
                Gson gson = new Gson();
                JsonFixturesResponse jsonFixturesResponse = gson.fromJson(response, JsonFixturesResponse.class);
                fixtures = jsonFixturesResponse.getFixtures();
                recycleViewAdapter.setFixtures(fixtures);
                recycleViewAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d(TAG, "" + error.getMessage() + "," + error.toString());
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

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
