package com.votechandnichowk;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllPollingStation extends AppCompatActivity {
    private SearchView searchView;
    public static String ac_no="2";
    RecyclerView recyclerView;
    List<Station> stationList;
    PollingSearchAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_polling_station);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search your Polling Station");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        searchView = (SearchView) findViewById(R.id.containersearchView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ac_no=PollingStation.ac_no;
       PollingDetails pollingDetails=new PollingDetails(this, recyclerView);
       pollingDetails.execute(ac_no);
        if (Main2Activity.lang==1){
            updateViews("hi");
        }
        else{
            updateViews("en");
        }
       stationList = new ArrayList<>();
       adapter = new PollingSearchAdapter(this, stationList);

//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                if(stationList.contains(query)){
//                    adapter.getFilter().filter(query);
//                }else{
//                    Toast.makeText(AllPollingStation.this, "No Match found", Toast.LENGTH_LONG).show();
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
    }
    public void updateViews(String languageCode){
        Context context=LocaleHelper.setLocale(this, languageCode);
        Resources resources=context.getResources();

        getSupportActionBar().setTitle(resources.getString(R.string.search_ps));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
