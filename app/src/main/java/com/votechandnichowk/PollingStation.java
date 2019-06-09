package com.votechandnichowk;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class PollingStation extends AppCompatActivity {

    public static String ac_no;
    SearchView containersearchView;
    ListView listView;
    TextView textView;
    ArrayList<String> containersearchlist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polling_station); ;
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.choose_ac);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        containersearchView =findViewById(R.id.containersearchView);
        listView=findViewById(R.id.listView);
        textView=findViewById(R.id.textView);
        if (Main2Activity.lang==1){
            updateViews("hi");
        }
        else{
            updateViews("en");
        }

        containersearchlist = new ArrayList<>();
        containersearchlist.add("4 - Adarsh Nagar");
        containersearchlist.add("14 - Shalimar Bagh");
        containersearchlist.add("15 - Shakur Basti");
        containersearchlist.add("16 - Tri Nagar");
        containersearchlist.add("17 - Wazirpur");
        containersearchlist.add("18 - Model Town");
        containersearchlist.add("19 - Sadar Bazar");
        containersearchlist.add("20 - Chandni Chowk");
        containersearchlist.add("21 - Matia Mahal");
        containersearchlist.add("22 - Ballimaran");

        final ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.polling_station_list,containersearchlist);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String product = ((TextView) view).getText().toString();
                String[] ac=product.split(" - ");
//                Toast.makeText(PollingStation.this,ac[0], Toast.LENGTH_LONG).show();
                ac_no=ac[0];
                Intent i1 = new Intent(getApplicationContext(), AllPollingStation.class);
                startActivity(i1);
            }
        });


        containersearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(containersearchlist.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(PollingStation.this, "No Match Found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


    }
    public void updateViews(String languageCode){
        Context context=LocaleHelper.setLocale(this, languageCode);
        Resources resources=context.getResources();

        getSupportActionBar().setTitle(resources.getString(R.string.choose_ac));

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
