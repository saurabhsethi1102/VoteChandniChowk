package com.votechandnichowk;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ResultDetails extends AsyncTask<String, Void, String> {
    Context context;
    RecyclerView recyclerView;
    ImageView imageView;
    TextView textView1, textView2, textView3;
    List<ResultJava> resultJavaList;

    public ResultDetails(Context context, RecyclerView recyclerView, ImageView imageView, TextView textView1, TextView textView2, TextView textView3){
        this.context=context;
        this.recyclerView=recyclerView;
        this.imageView=imageView;
        this.textView1=textView1;
        this.textView2=textView2;
        this.textView3=textView3;
    }
    ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Loading Result");
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            //cd.php is for Candidate Details
            String link="http://laxmiwafersncones.com/14apr/result_details.php";
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
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
        resultJavaList= new ArrayList<>();
        String[] splitArray = result.split("\\|");
        int length = (splitArray.length);
        Picasso.with(context).load("http://laxmiwafersncones.com/14apr/"+splitArray[3]).into(imageView);
        textView1.setText(splitArray[0]);
        textView2.setText(splitArray[1]);
        textView3.setText(splitArray[2]);
        for (int i=4; i<length; i=i+4){
            String Name=splitArray[i];
            String party=splitArray[i+1];
            String image="http://laxmiwafersncones.com/14apr/"+splitArray[i+3];
            String No_votes=splitArray[i+2];
            resultJavaList.add(new ResultJava(image, Name, party, No_votes));
        }

        ResultAdpater resultAdpater= new ResultAdpater(context, resultJavaList);
        recyclerView.setAdapter(resultAdpater);
        super.onPostExecute(result);
    }
}
