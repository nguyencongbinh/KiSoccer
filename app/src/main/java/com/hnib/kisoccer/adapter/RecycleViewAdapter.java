package com.hnib.kisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.hnib.kisoccer.R;
import com.hnib.kisoccer.Utils.CommonUtils;
import com.hnib.kisoccer.Utils.Constants;
import com.hnib.kisoccer.model.Fixture;
import com.hnib.kisoccer.model.Result;
import com.hnib.kisoccer.network.VolleySingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by caucukien on 23/11/2015.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.FixtureViewHolder> {

    private List<Fixture> fixtures;
    public final String TAG = RecycleViewAdapter.class.getSimpleName();
    private Context context;


    public static class FixtureViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime;
        TextView tvHomeName, tvAwayName;
        TextView tvScore;


        FixtureViewHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvHomeName = (TextView) itemView.findViewById(R.id.tv_home_name);
            tvAwayName = (TextView) itemView.findViewById(R.id.tv_away_name);
            tvScore = (TextView) itemView.findViewById(R.id.tv_score);
        }
    }

    public void setFixtures(List fixtures) {

        this.fixtures = fixtures;
    }

    @Override
    public FixtureViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.row_fixture, viewGroup, false);
        FixtureViewHolder holder = new FixtureViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(FixtureViewHolder holder, int position) {
        Fixture fixture = fixtures.get(position);
        holder.tvHomeName.setText(fixture.getHomeTeamName());
        holder.tvAwayName.setText(fixture.getAwayTeamName());
        if(fixture.getStatus().equalsIgnoreCase(Constants.Key.KEY_FINISHED)){
            holder.tvTime.setText(Constants.FULL_TIME);
        }else{
            holder.tvTime.setText(fixture.getStatus());
        }

        Result result = fixture.getResult();
        if (fixture.getStatus().equalsIgnoreCase(Constants.Key.KEY_TIMED) == true ) {
            String time = CommonUtils.getTimeFromDateFixture(fixture.getDate());
            if(time!=null){
                holder.tvTime.setText(time);
            }
            holder.tvScore.setText(Constants.VS);
        }else{
            holder.tvScore.setText(result.getGoalsHomeTeam() + "-" + result.getGoalsAwayTeam());
        }
//        String urlHome = fixture.getLinks().getHomeTeam().getHref();
//        String urlAway = fixture.getLinks().getAwayTeam().getHref();


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
