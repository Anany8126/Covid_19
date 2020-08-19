package com.example.covid_19;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<CountryModal> {

    private Context context;
    private List<CountryModal> countryModalList;
    private List<CountryModal> countryModalListFiltered;
    public MyCustomAdapter(@NonNull Context context, List<CountryModal> countryModalList) {
        super(context,R.layout.list_custom_item,countryModalList);
        this.context=context;
        this.countryModalList=countryModalList;
        this.countryModalListFiltered=countryModalList;
    }
         // getView ke naam se 1 override method generate krengey
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint({"ViewHolder", "InflateParams"}) View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,null,true);
        TextView tvCountryName = view.findViewById(R.id.custom_country_name_card);
        ImageView imageView = view.findViewById(R.id.custom_flag_card_list);

        tvCountryName.setText(countryModalListFiltered.get(position).getCountry());
        Glide.with(context).load(countryModalListFiltered.get(position).getFlag()).into(imageView);

        return view;
    }
    // niche ka sara code Search bar ke liye h

    @Override
    public int getCount() {
        return countryModalListFiltered.size();
    }

    @Nullable
    @Override
    public CountryModal getItem(int position) {
        return countryModalListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = countryModalList.size();
                    filterResults.values = countryModalList;

                }
                else {
                    List<CountryModal> resultsModal = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (CountryModal itemModal:countryModalList)
                    { if(itemModal.getCountry().toLowerCase().contains(searchStr))
                    {
                        resultsModal.add(itemModal);
                    }
                    filterResults.count = resultsModal.size();
                    filterResults.values = resultsModal;

                }
            }
                return filterResults;


            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countryModalListFiltered = (List<CountryModal>) results.values;
                Affected_countries.countryModalList = (List<CountryModal>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
