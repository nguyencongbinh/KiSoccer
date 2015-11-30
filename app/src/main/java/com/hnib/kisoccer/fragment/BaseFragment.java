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
import com.hnib.kisoccer.adapter.SimpleSectionedRecyclerViewAdapter;
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

    }
    public void setFixtures(List<Fixture> fixtures){

        this.fixtures = fixtures;
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

        List<SimpleSectionedRecyclerViewAdapter.Section> sections =
                new ArrayList<SimpleSectionedRecyclerViewAdapter.Section>();

        //Sections
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0,"Section 1"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(5,"Section 2"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(12,"Section 3"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(14,"Section 4"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(20,"Section 5"));

        //Add your adapter to the sectionAdapter
        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new
                SimpleSectionedRecyclerViewAdapter(getActivity(),R.layout.section, R.id.section_text,recycleViewAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        recyclerView.setAdapter(mSectionedAdapter);

        return view;
    }


    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    public RecycleViewAdapter getRecycleViewAdapter() {
        return recycleViewAdapter;
    }


}
