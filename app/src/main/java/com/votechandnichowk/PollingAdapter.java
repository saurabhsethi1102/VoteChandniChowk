package com.votechandnichowk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PollingAdapter extends RecyclerView.Adapter<PollingAdapter.PollingViewHolder> {
    private Context context;
    //we are storing all the products in a list
    private List<Station> stationList;

    public PollingAdapter(Context context, List<Station> stationList) {
        this.context = context;
        this.stationList = stationList;
    }

    @Override
    public PollingAdapter.PollingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.polling_station_card_layout, null);
        return new PollingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PollingAdapter.PollingViewHolder holder, int position) {
        //getting the product of the specified position
        final Station station = stationList.get(position);
        // holder.id.setText("1");
        Picasso.with(context).load(station.getPs_image()).into(holder.ps_image);
        holder.ps_name.setText(station.getPs_name());
        holder.ps_no.setText(station.getPs_no());
        holder.facilities.setText(station.getFacilities());
        holder.ps_queue.setText(station.getPs_queue());
        final String coordinates= station.getPs_gis();
        holder.ps_locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+coordinates);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(mapIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    class PollingViewHolder extends RecyclerView.ViewHolder {
        TextView id, ps_name, ps_no, facilities, ps_queue;
        ImageView ps_image;
        Button ps_locate;

        public PollingViewHolder(View itemView) {
            super(itemView);
            //id =itemView.findViewById(R.id.name);
            ps_image = itemView.findViewById(R.id.boothImage);
            ps_name = itemView.findViewById(R.id.boothName);
            ps_no = itemView.findViewById(R.id.Polling_station_no);
            facilities = itemView.findViewById(R.id.facilities);
            ps_queue = itemView.findViewById(R.id.number_of_people);
            ps_locate=itemView.findViewById(R.id.ps_locate);
        }
    }
}