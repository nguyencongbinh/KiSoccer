package com.hnib.kisoccer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.hnib.kisoccer.R;
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
        Button btnTime;
        TextView tvHomeName, tvAwayName;
        ImageView imgHome, imgAway;
        TextView tvScore;


        FixtureViewHolder(View itemView) {
            super(itemView);
            btnTime = (Button) itemView.findViewById(R.id.btn_time);
            tvHomeName = (TextView) itemView.findViewById(R.id.tv_home_name);
            tvAwayName = (TextView) itemView.findViewById(R.id.tv_away_name);
            imgHome = (ImageView) itemView.findViewById(R.id.img_home);
            imgAway = (ImageView) itemView.findViewById(R.id.img_away);
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
        holder.btnTime.setText(fixture.getStatus());
        Result result = fixture.getResult();
        if (fixture.getStatus().equalsIgnoreCase("timed") == false) {
            holder.tvScore.setText(result.getGoalsHomeTeam() + "-" + result.getGoalsAwayTeam());
        }
//        String urlHome = fixture.getLinks().getHomeTeam().getHref();
//        String urlAway = fixture.getLinks().getAwayTeam().getHref();
//        makeJsonObjectRequest(urlHome, urlAway, holder);


    }

    public void makeJsonObjectRequest(String urlHome, String urlAway, final FixtureViewHolder holder) {

        final JsonObjectRequest jsonObjReqHome = new JsonObjectRequest(Request.Method.GET,
                urlHome, (String) null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    String urlIconHome = response.getString("crestUrl");
                    Picasso.with(context)
                            .load(urlIconHome)
                            .resize(30,30)
                            .into(holder.imgHome);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());


            }

        });

        final JsonObjectRequest jsonObjReqAway = new JsonObjectRequest(Request.Method.GET,
                urlAway, (String) null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    String urlIconAway = response.getString("crestUrl");
                    Picasso.with(context)
                            .load(urlIconAway)
                            .resize(30,30)
                            .into(holder.imgAway);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());


            }

        });
        VolleySingleton.getInstance().getRequestQueue().add(jsonObjReqHome);
        VolleySingleton.getInstance().getRequestQueue().add(jsonObjReqAway);
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
