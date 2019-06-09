package com.votechandnichowk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class candidateAdapter extends RecyclerView.Adapter<candidateAdapter.candidateViewHolder> {
    private Context context;

    private List<Candidates> candidatesList;

    public candidateAdapter(Context context, List<Candidates> candidatesList) {
        this.context = context;
        this.candidatesList = candidatesList;
    }

    @Override
    public candidateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning              our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.candidate_card_layout, null);
        return new candidateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final candidateViewHolder holder, int position) {
        Candidates candidates = candidatesList.get(position);
        Picasso.with(context).load(candidates.getCandidateImage()).into(holder.candidateImage);
        holder.candidateName.setText(candidates.getName());
        holder.party.setText(candidates.getParty());
    }

    @Override
    public int getItemCount() {
        return candidatesList.size();
    }

    class candidateViewHolder extends RecyclerView.ViewHolder {
        ImageView candidateImage;
        TextView candidateName,party;

        public candidateViewHolder(View itemView) {
            super(itemView);
            candidateImage = itemView.findViewById(R.id.candidate);
            candidateName= itemView.findViewById(R.id.name);
            party = itemView.findViewById(R.id.partyName);
        }
    }
}
