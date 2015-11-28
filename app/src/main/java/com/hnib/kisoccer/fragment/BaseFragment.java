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

import com.hnib.kisoccer.R;
import com.hnib.kisoccer.Utils.Constants;
import com.hnib.kisoccer.adapter.RecycleViewAdapter;
import com.hnib.kisoccer.model.Fixture;
import com.hnib.kisoccer.model.FixtureSingleton;
import com.hnib.kisoccer.network.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caucukien on 28/11/2015.
 */
public class BaseFragment extends Fragment {

    public final String TAG = BaseFragment.class.getSimpleName();

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
    private List<Fixture> fixtures = new ArrayList<>();
    private String jsonResponse;
    private ProgressDialog pDialog;


    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "OnCreate");

        fixtures = FixtureSingleton.getInstance().getFixtures();

    }
    public void setFixtures(List<Fixture> fixtures){
        this.fixtures = fixtures;
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

        return view;
    }

    @Override
    public void onResume() {
        Log.d("TAG", "onResume");
        super.onResume();

    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    public RecycleViewAdapter getRecycleViewAdapter() {
        return recycleViewAdapter;
    }


}
