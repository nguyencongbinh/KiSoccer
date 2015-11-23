package com.hnib.kisoccer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.hnib.kisoccer.R;
import com.hnib.kisoccer.Utils.Constants;
import com.hnib.kisoccer.adapter.RecycleViewAdapter;
import com.hnib.kisoccer.model.Fixture;
import com.hnib.kisoccer.model.JsonFixturesResponse;
import com.hnib.kisoccer.network.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycleView;
    private List<Fixture> fixtures = new ArrayList<>();
    private RecycleViewAdapter recycleViewAdapter;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recycleView.setLayoutManager(llm);
        recycleViewAdapter = new RecycleViewAdapter();
        recycleViewAdapter.setFixtures(fixtures);
        recycleView.setAdapter(recycleViewAdapter);
        sendRequestToFootballData();


    }

    private void sendRequestToFootballData() {

        String url = "http://httpbin.org/html";

// Request a string response
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        JsonFixturesResponse fixturesResponse = gson.fromJson(response, JsonFixturesResponse.class);
                        fixtures = fixturesResponse.getFixtures();
                        recycleViewAdapter.setFixtures(fixtures);
                        recycleViewAdapter.notifyDataSetChanged();

                        // Result handling
                        System.out.println("binhnc " + response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });
        VolleySingleton.getInstance().getRequestQueue().add(stringRequest);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
