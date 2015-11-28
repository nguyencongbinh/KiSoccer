package com.hnib.kisoccer.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.google.gson.reflect.TypeToken;
import com.hnib.kisoccer.R;
import com.hnib.kisoccer.Utils.Constants;
import com.hnib.kisoccer.activity.KiSoccerApplication;
import com.hnib.kisoccer.adapter.RecycleViewAdapter;
import com.hnib.kisoccer.model.Fixture;
import com.hnib.kisoccer.model.JsonFixturesResponse;
import com.hnib.kisoccer.network.VolleySingleton;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    public final String TAG = ResultFragment.class.getSimpleName();

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
    private List<Fixture> fixtures = new ArrayList<>();
    private String jsonResponse;
    private ProgressDialog pDialog;


    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "OnCreate");

        fixtures = KiSoccerApplication.getInstance().getFixtures();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("TAG", "onSaveInstanceState");
        super.onSaveInstanceState(outState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycleView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
         recycleViewAdapter = new RecycleViewAdapter();
        recycleViewAdapter.setFixtures(fixtures);
        recyclerView.setAdapter(recycleViewAdapter);


        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);


        return view;
    }

    @Override
    public void onResume() {
        Log.d("TAG", "onResume");
        super.onResume();

    }


    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            Log.d("TAG", "onRefresh");
            VolleySingleton.getInstance().callJsonStringRequest(Constants.url);
            VolleySingleton.getInstance().setOnNetWorkResponse(new VolleySingleton.OnNetworkResponse() {
                @Override
                public void onResponseSucces() {

                    List<Fixture> fixtures = KiSoccerApplication.getInstance().getFixtures();
                    recycleViewAdapter.setFixtures(fixtures);
                    recycleViewAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);

                }

                @Override
                public void onResponseError() {

                }
            });

        }
    };







}
