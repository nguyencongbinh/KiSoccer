package com.hnib.kisoccer.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hnib.kisoccer.R;
import com.hnib.kisoccer.Utils.CommonUtils;
import com.hnib.kisoccer.Utils.Constants;
import com.hnib.kisoccer.adapter.RecycleViewAdapter;
import com.hnib.kisoccer.adapter.SimpleSectionedRecyclerViewAdapter;
import com.hnib.kisoccer.model.Fixture;
import com.hnib.kisoccer.model.FixtureSingleton;
import com.hnib.kisoccer.network.VolleySingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFixturesFragment extends BaseFragment {

    public final String TAG = ResultFixturesFragment.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        List<Fixture> resultFixtures = FixtureSingleton.getInstance().getResultFixtures();
        CommonUtils.sortListFixtureByLeague(resultFixtures);
        setFixtures(resultFixtures);
        Log.i(TAG, CommonUtils.getCurrentTime());


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view = super.onCreateView(inflater, container, savedInstanceState);
        getSwipeRefreshLayout().setOnRefreshListener(onResultRefreshListener);
        return view;

    }

    private SwipeRefreshLayout.OnRefreshListener onResultRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            Log.d("TAG", "onRefresh");
            VolleySingleton.getInstance().callFixtureJsonStringRequest(Constants.url);
            VolleySingleton.getInstance().setOnNetWorkResponse(new VolleySingleton.OnNetworkResponse() {
                @Override
                public void onResponseSucces() {
                    List<Fixture> resultFixtures = FixtureSingleton.getInstance().getResultFixtures();
                    getRecycleViewAdapter().setFixtures(resultFixtures);
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
