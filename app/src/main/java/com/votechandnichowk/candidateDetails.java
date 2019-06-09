package com.votechandnichowk;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class candidateDetails extends AsyncTask<String, Void, String> {

    Context context;
    RecyclerView recyclerView;
    List<Candidates> candidatesList;
    public candidateDetails(Context context, RecyclerView recyclerView){
        this.context=context;
        this.recyclerView=recyclerView;
    }
    ProgressDialog progress;
    @Override
    protected String doInBackground(String... strings) {
        try{
            //cd.php is for Candidate Details
            String link="http://laxmiwafersncones.com/14apr/cd.php";
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
    protected void onPreExecute() {

        progress=new ProgressDialog(context);
        progress.setMessage("Loading Candidate Details...");
        progress.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        if (progress.isShowing()){
            progress.dismiss();
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        candidatesList= new ArrayList<>();
        String[] splitArray = result.split("\\|");
        int length = (splitArray.length);
        for (int i=0; i<length; i=i+3){

            String Name=splitArray[i];
            String party=splitArray[i+1];
            String image="http://laxmiwafersncones.com/14apr/"+splitArray[i+2];
            System.out.print(image);
            candidatesList.add(new Candidates(image, Name, party));
        }
        candidateAdapter candidateAdapter= new candidateAdapter(context, candidatesList);
        recyclerView.setAdapter(candidateAdapter);
        super.onPostExecute(result);
    }
}
