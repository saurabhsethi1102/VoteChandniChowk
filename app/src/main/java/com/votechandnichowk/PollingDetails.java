package com.votechandnichowk;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
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

public class PollingDetails extends AsyncTask<String, Void, String> {
    Context context;
    RecyclerView recyclerView;
    List<Station> stationList;
    ArrayList<String> ar = new ArrayList<String>();
    public PollingDetails(Context context, RecyclerView recyclerView){
        this.context=context;
        this.recyclerView=recyclerView;
    }
    ProgressDialog progressDialog;
    @Override
    protected void onPreExecute() {
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Polling Stations Loading...");
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String ac_no = PollingStation.ac_no;
        try{
            String link="http://laxmiwafersncones.com/14apr/polling_station_details.php";
            String data  = URLEncoder.encode("ac_no", "UTF-8") + "=" +
                    URLEncoder.encode(ac_no, "UTF-8");
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setReadTimeout(100000);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();
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
        stationList = new ArrayList<>();
        String[] splitArray = result.split("\\|");
        int length = (splitArray.length);
        for (int i=0; i<length; i=i+18){
            String PS_No=splitArray[i];
            String PS_Name=splitArray[i+1];
            String PS_QUEUE=splitArray[i+2];
            String PS_GIS=splitArray[i+3];
            String PS_IMAGE="http://laxmiwafersncones.com/14apr/"+splitArray[i+4];
            String facilities="";
            if (splitArray[i+5].equals("1")) {
                facilities += "Creche\t";
            }
            if (splitArray[i+6].equals("1")) {
                facilities += "drinking_water\n";
            }
            if (splitArray[i+7].equals("1")) {
                facilities += "food\t";
            }
            if (splitArray[i+8].equals("1")) {
                facilities += "furniture\n";
            }
            if (splitArray[i+9].equals("1")) {
                facilities += "helpdesk\t";
            }
            if (splitArray[i+10].equals("1")) {
                facilities += "lighting\n";
            }
            if (splitArray[i+11].equals("1")) {
                facilities += "medical_kit\t";
            }
            if (splitArray[i+12].equals("1")) {
                facilities += "ramp\n";
            }
            if (splitArray[i+13].equals("1")) {
                facilities += "shed\t";
            }
            if (splitArray[i+14].equals("1")) {
                facilities += "signage\n";
            }
            if (splitArray[i+15].equals("1")) {
                facilities += "toilet\t";
            }
            if (splitArray[i+16].equals("1")) {
                facilities += "volunteer\n";
            }
            if (splitArray[i+17].equals("1")) {
                facilities += "wheelchair\t";
            }
            ar.add(splitArray[i+1]);
            stationList.add(new Station("", PS_IMAGE, PS_Name, PS_No, facilities, PS_QUEUE, PS_GIS));
            }
        PollingAdapter pollingAdapter=new PollingAdapter(context, stationList);
        recyclerView.setAdapter(pollingAdapter);
        super.onPostExecute(result);
    }

}

