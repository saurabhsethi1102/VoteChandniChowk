package com.votechandnichowk;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class getPickUpDetails extends AsyncTask<String, Void, String>{
    Context context;
    RecyclerView recyclerView;
    List<pickUp> pickUpList;
    getPickUpDetails(Context context, RecyclerView recyclerView){
        this.context=context;
        this.recyclerView=recyclerView;
    }
    ProgressDialog progressDialog;
    @Override
    protected String doInBackground(String... strings) {
        try{
            String link="http://laxmiwafersncones.com/14apr/pickup.php";
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        pickUpList= new ArrayList<>();
        String[] splitArray = result.split("\\|");
        int length = (splitArray.length);
        for (int i=0; i<length; i=i+4){
            String id=splitArray[i];
            String coordinates=splitArray[i+1];
            String name=splitArray[i+2];
            String time=splitArray[i+3];
            pickUpList.add(new pickUp(id,name,time,coordinates));
        }
        pickUpAdapter pickUpAdapter= new pickUpAdapter(context, pickUpList);
        //setting adapter to recyclerview
        recyclerView.setAdapter(pickUpAdapter);
    }

    @Override
    protected void onPreExecute() {
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Loading Pickup Details");
        progressDialog.show();
        super.onPreExecute();
    }
}
