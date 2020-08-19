package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class home_page extends AppCompatActivity {
TextView txtmarque,tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvAffectedCountries,tvTodayDeaths;
SimpleArcLoader simpleArcLoader;
ScrollView scrollView;
PieChart pieChart;
Button trackCountry;
ImageView bttn_india;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().setTitle("Global Stats");
        // niche vali 2 line marque ke liya h
        txtmarque = (TextView)findViewById(R.id.home_marque);
        txtmarque.setSelected(true);

        bttn_india = (ImageView) findViewById(R.id.home_bttn_india);


        tvCases = (TextView)findViewById(R.id.home_tvcases);
        tvRecovered = (TextView)findViewById(R.id.home_tvrecover);
        tvCritical = (TextView) findViewById(R.id.home_tvcritical);
        tvActive = (TextView) findViewById(R.id.home_tvactive);
        tvTodayCases = (TextView) findViewById(R.id.home_tv_today_cases);
        tvTotalDeaths = (TextView) findViewById(R.id.home_tv_total_deaths);
        tvAffectedCountries = (TextView) findViewById(R.id.home_tv_affected_countries);
        tvTodayDeaths = (TextView)findViewById(R.id.home_tvTodayDeaths);



        simpleArcLoader = (SimpleArcLoader) findViewById(R.id.arcloader);
        scrollView = (ScrollView) findViewById(R.id.home_scroll);
        pieChart = (PieChart) findViewById(R.id.piechart);
        trackCountry = (Button) findViewById(R.id.home_bttn_track);

        bttn_india.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_page.this,Indian.class);
                startActivity(intent);

            }
        });

        trackCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_page.this,Affected_countries.class);
                startActivity(intent);
            }
        });




        fetchingData();



    }

    private void fetchingData() {
        // is method m Global data aayega
        // niche vala url , corona.lmao.ninja rikhne pr milega
        String url = "https://corona.lmao.ninja/v2/all/";
        simpleArcLoader.start();
        // volley library ki class use kri h , StringRequest jo request ko get kregi

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("Range")
            @Override
            public void onResponse(String response) {
                //is method mai json object aayega
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    tvCases.setText(jsonObject.getString("cases"));
                    tvRecovered.setText(jsonObject.getString("recovered"));
                    tvCritical.setText(jsonObject.getString("critical"));
                    tvActive.setText(jsonObject.getString("active"));
                    tvTodayCases.setText(jsonObject.getString("todayCases"));
                    tvTotalDeaths.setText(jsonObject.getString("deaths"));
                    tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                    tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));

                    pieChart.addPieSlice(new PieModel("cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFBF00")));
                    pieChart.addPieSlice(new PieModel("recovered",Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#2DEF10")));
                    pieChart.addPieSlice(new PieModel("deaths",Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#0C0B0C")));
                    pieChart.addPieSlice(new PieModel("active",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#FF2019")));
                    pieChart.startAnimation();

                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);



                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(home_page.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }




}
