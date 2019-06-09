package com.votechandnichowk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ResultAdpater extends RecyclerView.Adapter<ResultAdpater.ResultViewHolder> {

    private Context context;

    private List<ResultJava> resultList;

    public ResultAdpater(Context context, List<ResultJava> resultList) {
        this.context = context;
        this.resultList = resultList;
    }


    public ResultAdpater.ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning              our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.result_card, null);
        return new ResultAdpater.ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ResultAdpater.ResultViewHolder holder, int position) {
        ResultJava resultJava = resultList.get(position);
        Picasso.with(context).load(resultJava.getCandidateImage()).into(holder.candidateImage);
        holder.candidateName.setText(resultJava.getName());
        holder.party.setText(resultJava.getPartyName());
        holder.votes.setText(resultJava.getVotes());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    class ResultViewHolder extends RecyclerView.ViewHolder {
        ImageView candidateImage;
        TextView candidateName, party,votes ;

        public ResultViewHolder(View itemView) {
            super(itemView);
            candidateImage = itemView.findViewById(R.id.candidateImage);
            candidateName= itemView.findViewById(R.id.candidateName);
            party = itemView.findViewById(R.id.partyName);
            votes=itemView.findViewById(R.id.votes);
        }
    }



}
