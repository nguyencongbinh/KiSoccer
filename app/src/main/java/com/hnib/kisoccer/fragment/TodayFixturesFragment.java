package com.hnib.kisoccer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hnib.kisoccer.R;
import com.hnib.kisoccer.Utils.Constants;
import com.hnib.kisoccer.model.Fixture;
import com.hnib.kisoccer.model.FixtureSingleton;
import com.hnib.kisoccer.network.VolleySingleton;

import java.util.List;


public class TodayFixturesFragment extends BaseFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFixtures(FixtureSingleton.getInstance().getTodaytFixtures());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        getSwipeRefreshLayout().setOnRefreshListener(onTodayRefreshListener);
        return view;

    }

    private SwipeRefreshLayout.OnRefreshListener onTodayRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            Log.d("TAG", "onRefresh");
            VolleySingleton.getInstance().callFixtureJsonStringRequest(Constants.url);
            VolleySingleton.getInstance().setOnNetWorkResponse(new VolleySingleton.OnNetworkResponse() {
                @Override
                public void onResponseSucces() {

                    List<Fixture> todayFixtures = FixtureSingleton.getInstance().getTodaytFixtures();
                    getRecycleViewAdapter().setFixtures(todayFixtures);
                    getRecycleViewAdapter().notifyDataSetChanged();
                    getSwipeRefreshLayout().setRefreshing(false);

                }

                @Override
                public void onResponseError() {

                }
            });

        }
    };
}
