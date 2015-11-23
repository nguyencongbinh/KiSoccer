package com.hnib.kisoccer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hnib.kisoccer.R;
import com.hnib.kisoccer.model.Fixture;

import java.util.List;

/**
 * Created by caucukien on 23/11/2015.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.FixtureViewHolder>{

    private List<Fixture> fixtures;
    public static class FixtureViewHolder extends RecyclerView.ViewHolder {
       TextView tvHomeTeamName;
        TextView tvAwayTeamName;


        FixtureViewHolder(View itemView) {
            super(itemView);
            tvHomeTeamName = (TextView)itemView.findViewById(R.id.tvHomeTeamName);
            tvAwayTeamName = (TextView)itemView.findViewById(R.id.tvAwayTeamName);
        }
    }

    public void setFixtures(List fixtures){
        this.fixtures = fixtures;
    }

    @Override
    public FixtureViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_fixture, viewGroup, false);
        FixtureViewHolder holder = new FixtureViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(FixtureViewHolder holder, int position) {
        holder.tvHomeTeamName.setText(fixtures.get(position).getHomeTeamName());
        holder.tvAwayTeamName.setText(fixtures.get(position).getAwayTeamName());
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return fixtures.size();
    }

}
