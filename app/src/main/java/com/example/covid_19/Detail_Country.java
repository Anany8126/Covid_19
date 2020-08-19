package com.example.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Detail_Country extends AppCompatActivity {
    private  int positionCountry;
    TextView country,cases,todayCases,deaths,todayDeaths,recovered,active,critical,tests,continent,casesPerOneMillion,deathsPerOneMillion,testsPerOneMillion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__country);
        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+Affected_countries.countryModalList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        country = (TextView) findViewById(R.id.detail_tvcountry);
        cases = (TextView) findViewById(R.id.detail_tv_cases);
        todayCases = (TextView) findViewById(R.id.detail_tv_today_cases);
        deaths = (TextView) findViewById(R.id.detail_tv_deaths);
        todayDeaths = (TextView) findViewById(R.id.detail_tv_today_deaths);
        recovered = (TextView) findViewById(R.id.detail_tv_recovered);
        active = (TextView) findViewById(R.id.detail_tv_active_cases);
        critical = (TextView) findViewById(R.id.detail_tv_critical);
        tests = (TextView) findViewById(R.id.detail_tv_test);
        continent = (TextView) findViewById(R.id.detail_tvcontinent);
        casesPerOneMillion = (TextView) findViewById(R.id.detail_tv_casesper_million);
        deathsPerOneMillion = (TextView) findViewById(R.id.detail_tv_deaths_million);
        testsPerOneMillion = (TextView) findViewById(R.id.detail_tv_test_million);



        country.setText(Affected_countries.countryModalList.get(positionCountry).getCountry());
        cases.setText(Affected_countries.countryModalList.get(positionCountry).getCases());
        todayCases.setText(Affected_countries.countryModalList.get(positionCountry).getTodayCases());
        deaths.setText(Affected_countries.countryModalList.get(positionCountry).getDeaths());
        todayDeaths.setText(Affected_countries.countryModalList.get(positionCountry).getTodayDeaths());
        recovered.setText(Affected_countries.countryModalList.get(positionCountry).getRecovered());
        active.setText(Affected_countries.countryModalList.get(positionCountry).getActive());
        critical.setText(Affected_countries.countryModalList.get(positionCountry).getCritical());
        tests.setText(Affected_countries.countryModalList.get(positionCountry).getTests());
        continent.setText(Affected_countries.countryModalList.get(positionCountry).getContinent());
        casesPerOneMillion.setText(Affected_countries.countryModalList.get(positionCountry).getCasesPerOneMillion());
        deathsPerOneMillion.setText(Affected_countries.countryModalList.get(positionCountry).getDeathsPerOneMillion());
        testsPerOneMillion.setText(Affected_countries.countryModalList.get(positionCountry).getTestsPerOneMillion());


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
