package com.example.covid_19;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Indian extends AppCompatActivity {
    ListView state_list;
    SimpleArcLoader simpleArcLoader;

    public static List<IndiamModal> indiamModalList = new ArrayList<>();
    IndiamModal indiamModal;
    MyCustomIndianAdapter myCustomIndianAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian);
        getSupportActionBar().setTitle("Indian States");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        state_list = (ListView) findViewById(R.id.indian_state_list);
        simpleArcLoader = (SimpleArcLoader) findViewById(R.id.state_loader);

        fetchStateData();



    }

    private void fetchStateData() {
        String url = "https://covid-india-cases.herokuapp.com/states";
        simpleArcLoader.start();
        // volley library ki class use kri h , StringRequest jo request ko get kregi
        // niche vala jsonobjectrequest vala sara code coding in flow se kra h(how to parse a json using volley)

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {


                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i= 0;i<jsonArray.length(); i++)
                        {
                            JSONObject states = jsonArray.getJSONObject(i);
                            String sname = states.getString("state");
                            String confirmed = states.getString("noOfCases");
                            String recovered = states.getString("cured");
                            String deaths = states.getString("deaths");

                            indiamModal = new IndiamModal(sname, confirmed, recovered , deaths);
                            indiamModalList.add(indiamModal);

                        }
                        myCustomIndianAdapter = new MyCustomIndianAdapter(Indian.this,indiamModalList);
                        state_list.setAdapter(myCustomIndianAdapter);
                        simpleArcLoader.stop();
                        simpleArcLoader.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        simpleArcLoader.stop();
                        simpleArcLoader.setVisibility(View.GONE);
                    }

                }



        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                Toast.makeText(Indian.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);






    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
