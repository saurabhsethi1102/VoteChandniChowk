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

public class ThirdGenderRequest extends AsyncTask<String, Void, String> {
    Context context;
    public ThirdGenderRequest(Context context){
        this.context=context;
    }

    @Override
    protected String doInBackground(String... strings) {
        String id = strings[0];
        String epic=strings[1];
        try{
            String link="http://avrutti.com/election/eci/third_gender_request.php";
            String data  = URLEncoder.encode("id", "UTF-8") + "=" +
                    URLEncoder.encode(id, "UTF-8");
            data  += "&" + URLEncoder.encode("EPIC_NO", "UTF-8") + "=" +
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
        if (result.equals("1")){
            Toast.makeText(context, "Thank you for availing our Service", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, Main2Activity.class));
        }
        else{
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
        super.onPostExecute(result);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
}
