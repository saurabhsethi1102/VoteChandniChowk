package com.votechandnichowk;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

public class checkPWD extends AsyncTask<String, Void, String> {
    Context context;
    public checkPWD(Context context){
        this.context=context;
    }
    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... strings) {
        String epic = strings[0];
        try{
            String link="http://avrutti.com/election/eci/check_pwd.php";
            String data  = URLEncoder.encode("EPIC_NO", "UTF-8") + "=" +
                    URLEncoder.encode(epic, "UTF-8");
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
        super.onPostExecute(result);
        if (result.equals("1")){
            Intent i1 = new Intent(context, Pwd_pick_up_points.class);
            context.startActivity(i1);
        }
        else{
            Intent i1=new Intent(context, Main2Activity.class);
            Toast.makeText(context, "Your EPIC Number is not in PWD list or you have already availed the service", Toast.LENGTH_LONG).show();
            context.startActivity(i1);
        }

    }
}
