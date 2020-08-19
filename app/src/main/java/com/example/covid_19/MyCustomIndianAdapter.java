package com.example.covid_19;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyCustomIndianAdapter extends ArrayAdapter<IndiamModal> {
    private Context context;
    private List<IndiamModal> indiamModalList;

    public MyCustomIndianAdapter( Context context, List<IndiamModal> indiamModalList) {
        super(context, R.layout.list_custom_state_india,indiamModalList);
        this.context = context;
        this.indiamModalList = indiamModalList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint({"ViewHolder", "InflateParams"}) View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_state_india,null,true);
        TextView sname = view.findViewById(R.id.state_state_name);
        TextView confirmed = view.findViewById(R.id.state_tv_active_cases);
        TextView recoverd = view.findViewById(R.id.state_tv_recovered_cases);
        TextView deaths = view.findViewById(R.id.state_tv_deaths);

        sname.setText(indiamModalList.get(position).getSname());
        confirmed.setText(indiamModalList.get(position).getConfirmed());
        recoverd.setText(indiamModalList.get(position).getRecovered());
        deaths.setText(indiamModalList.get(position).getDeaths());


        return view;
    }
}
