package com.votechandnichowk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class CustomDialogFragment extends AppCompatDialogFragment {
    private TextView partyName,vote,leaderName;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog,null);
        build.setView(view);
        //build.setView(view).setPositiveButton();

        Bundle args = getArguments();
        String name = args.getString("Name");
        String votes = args.getString("Vote");
        partyName = (TextView)view.findViewById(R.id.customDialogPartyName);
        vote = (TextView)view.findViewById(R.id.customDialogVoteCount);
        leaderName=view.findViewById(R.id.partyLeader);

        if(name.equals("BJP")){
            partyName.setText("Bhartiya Janata Party");
            leaderName.setText("Dr. Harsh Vardhan");
            vote.setText("Votes : " +votes);
        }
        if(name.equals("AAP")){
            partyName.setText("Aam Aadmi Party");
            leaderName.setText("Ashutosh");
            vote.setText("Votes : " +votes);
        }
        if(name.equals("INC")){
            partyName.setText("Indian National Congress");
            leaderName.setText("Kapil Sibal");
            vote.setText("Votes : " +votes);
        }

        if(name.equals("BSP")){
            partyName.setText("Bahujan Samaj Party");
            leaderName.setText("Narendra Kumar Pandey");
            vote.setText("Votes : " +votes);
        }
        if(name.equals("NOTA")){
            partyName.setText("None Of The Above");
            leaderName.setText("");
            vote.setText("Votes : " +votes);
        }




        return build.create();

    }
}
