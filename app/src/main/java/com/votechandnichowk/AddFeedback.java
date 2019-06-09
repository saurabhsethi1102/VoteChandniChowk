package com.votechandnichowk;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
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

public class AddFeedback  extends AsyncTask<String, Void , String> {
    private Context context;
    private TextView messageTextview;
    private String bname, feedback;
    AddFeedback(Context context, TextView messageText, String name, String feedbackMsg){
        this.context =context;
        messageTextview = messageText;
        bname = name;
        feedback = feedbackMsg;
    }


    protected void onPreExecute(){
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            String name = (String)strings[0];
            String suggestion = (String)strings[1];
            String rating = (String)strings[2];
            String link="http://avrutti.com/election/eci/feedback.php";
            String data  = URLEncoder.encode("name", "UTF-8") + "=" +
                    URLEncoder.encode(name, "UTF-8");
            data += "&" + URLEncoder.encode("suggestion", "UTF-8") + "=" +
                    URLEncoder.encode(suggestion, "UTF-8");
            data += "&" + URLEncoder.encode("rating", "UTF-8") + "=" +
                    URLEncoder.encode(rating, "UTF-8");
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new String("Exception: " + e.getMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return new String("Exception: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){
        Log.d("Result Activity",result);
        this.messageTextview.setText(result);
    }

}
