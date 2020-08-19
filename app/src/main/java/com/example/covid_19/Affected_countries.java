package com.example.covid_19;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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

public class Affected_countries extends AppCompatActivity {
    EditText editSearch;
    ListView listView;
    SimpleArcLoader simpleArcLoader;
    public static List<CountryModal> countryModalList = new ArrayList<>();
    CountryModal countryModal;
    MyCustomAdapter myCustomAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_countries);

        editSearch=(EditText) findViewById(R.id.affect_et1);
        listView = (ListView) findViewById(R.id.affct_list);
        simpleArcLoader = (SimpleArcLoader) findViewById(R.id.arcloader);
        // action bar ke liye niche ki 3 line, back button ke liye onOptions method niche use kiya h
        getSupportActionBar().setTitle("Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fetchData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Affected_countries.this,Detail_Country.class).putExtra("position",position);
                startActivity(intent);
            }
        });


        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myCustomAdapter.getFilter().filter(s);
                myCustomAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {
        // niche vala url , corona.lmao.ninja rikhne pr milega
        String url = "https://corona.lmao.ninja/v2/countries/";
        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("Range")
            @Override
            public void onResponse(String response) {
                //is method mai json object aayega
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String country = jsonObject.getString("country");
                        String cases = jsonObject.getString("cases");
                        String todayCases = jsonObject.getString("todayCases");
                        String deaths = jsonObject.getString("deaths");
                        String todayDeaths = jsonObject.getString("todayDeaths");
                        String recovered = jsonObject.getString("recovered");
                        String active = jsonObject.getString("active");
                        String critical = jsonObject.getString("critical");
                        String continent = jsonObject.getString("continent");
                        String casesPerOneMillion = jsonObject.getString("casesPerOneMillion");
                        String deathsPerOneMillion = jsonObject.getString("deathsPerOneMillion");
                        String tests = jsonObject.getString("tests");
                        String testsPerOneMillion = jsonObject.getString("testsPerOneMillion");

                        JSONObject object = jsonObject.getJSONObject("countryInfo");
                        // country info mai , longitude or lattitude bhi h , marji h lena chahe toh le sakte h
                        String flag = object.getString("flag");
                        countryModal = new CountryModal(flag, country, cases, todayCases, deaths, todayDeaths, recovered, active, critical, tests, continent, casesPerOneMillion, deathsPerOneMillion,  testsPerOneMillion);
                        countryModalList.add(countryModal);
                    }
                    myCustomAdapter = new MyCustomAdapter(Affected_countries.this,countryModalList);
                    listView.setAdapter(myCustomAdapter);
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
               Toast.makeText(Affected_countries.this,error.getMessage(),Toast.LENGTH_SHORT).show();
               // Toast.makeText(Affected_countries.this,"Poor internet connection",Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);




    }
}
