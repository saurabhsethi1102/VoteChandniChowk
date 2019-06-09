package com.votechandnichowk;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class ResultActivity extends AppCompatActivity {
    TextView winnerName,winnerPartyName,winnerVotes;
    ImageView winnerImage;
    RecyclerView resultRecycler;
    private List<ResultJava> resultList = new ArrayList<>();
    private ResultAdpater mAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        winnerImage=findViewById(R.id.winnerImage);
        winnerName=findViewById(R.id.resultCandidateNameTextView);
        winnerPartyName=findViewById(R.id.resultPartyName);
        winnerVotes=findViewById(R.id.votes);
        resultRecycler=findViewById(R.id.resultRecyler);

       resultRecycler.setHasFixedSize(true);
       resultRecycler.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.results);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (Main2Activity.lang==1){
            updateViews("hi");
        }
        else{
            updateViews("en");
        }

        ResultDetails resultDetails=new ResultDetails(this, resultRecycler, winnerImage, winnerName, winnerPartyName, winnerVotes);
        resultDetails.execute();
        resultRecycler.setAdapter(mAdapter);

    }

    public void updateViews(String languageCode){
        Context context=LocaleHelper.setLocale(this, languageCode);
        Resources resources=context.getResources();

        getSupportActionBar().setTitle(resources.getString(R.string.results));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}