package com.votechandnichowk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class pickUpAdapter extends RecyclerView.Adapter<pickUpAdapter.pickUpViewHolder> {
    private Context context;
    //we are storing all the products in a list
    private List<pickUp> pickUpList;
    public pickUpAdapter(Context context, List<pickUp> pickUpList) {
        this.context = context;
        this.pickUpList = pickUpList;
    }
    @Override
    public pickUpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning              our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pwd_recycler_layout, null);
        return new pickUpViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final pickUpViewHolder holder, int position) {
        //getting the product of the specified position
        final pickUp pickup= pickUpList.get(position);
       // holder.id.setText("1");
        holder.pickup_name.setText(pickup.getPickup_name());
        holder.pickup_time.setText(pickup.getPickup_time());
        final String coordinates= pickup.getPickup_coordinates();
        holder.pickup_coordnates.setOnClickListener(new View.OnClickListener() {
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
        if (PWDActivity.clicked==1){
            holder.request_pickup.setOnClickListener(new View.OnClickListener() {
                String epic=PWDActivity.EpicNO;
                @Override
                public void onClick(View v) {
                    pwdRequest pwdRequest=new pwdRequest(context);
                    pwdRequest.execute(pickup.getId(), epic);
                    PWDActivity.clicked=0;
                }
            });
        }
        else if (ThirdGenderActivity.clicked==1){
            holder.request_pickup.setOnClickListener(new View.OnClickListener() {
                String epic=ThirdGenderActivity.EpicNO;
                @Override
                public void onClick(View v) {
                    ThirdGenderRequest thirdGenderRequest=new ThirdGenderRequest(context);
                    thirdGenderRequest.execute(pickup.getId(), epic);
                    ThirdGenderActivity.clicked=0;
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return pickUpList.size();
    }
    class pickUpViewHolder extends RecyclerView.ViewHolder {
        TextView id, pickup_name,pickup_time;
        Button pickup_coordnates, request_pickup;
        public pickUpViewHolder(View itemView) {
            super(itemView);
            id =itemView.findViewById(R.id.name);
            pickup_name= itemView.findViewById(R.id.pickup_point_name);
            pickup_time = itemView.findViewById(R.id.pickup_time);
            pickup_coordnates=itemView.findViewById(R.id.pickup_location_button);
            request_pickup=itemView.findViewById(R.id.pickup_request);
        }
    }
}
