package com.votechandnichowk;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PollingSearchAdapter extends RecyclerView.Adapter<PollingSearchAdapter.PollingViewHolder> implements Filterable {

    private Context context;

    //we are storing all the products in a list
    private List<Station> stationList;
    private List<Station> PollingListFiltered;

    public PollingSearchAdapter(Context context, List<Station> stationList) {
        this.context = context;
        this.stationList = stationList;
        this.PollingListFiltered = stationList;
    }

    @Override
    public PollingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.polling_station_card_layout, null);
        return new PollingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PollingViewHolder holder, int position) {
        //getting the product of the specified position
        Station station = stationList.get(position);

        Picasso.with(context).load(station.getPs_image()).into(holder.boothImage);
        holder.boothName.setText(station.getPs_name());
        holder.facility.setText(station.getFacilities());
        holder.no_of_people.setText(station.getPs_queue());


    }


    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    PollingListFiltered = stationList;
                } else {
                    List<Station> filteredList = new ArrayList<>();
                    for (Station row : stationList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getPs_name().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    PollingListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = PollingListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                PollingListFiltered = (ArrayList<Station>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    class PollingViewHolder extends RecyclerView.ViewHolder {

        TextView boothName, pid, facility, no_of_people;
        ImageView boothImage;

        public PollingViewHolder(View itemView) {
            super(itemView);

            final Intent[] i1 = new Intent[1];
            boothImage = itemView.findViewById(R.id.boothImage);
            boothName = itemView.findViewById(R.id.boothName);
            pid = itemView.findViewById(R.id.Polling_station_no);
            facility = itemView.findViewById(R.id.facilities);
            no_of_people = (itemView).findViewById(R.id.number_of_people);
            final Context context = itemView.getContext();

        }
    }
}
