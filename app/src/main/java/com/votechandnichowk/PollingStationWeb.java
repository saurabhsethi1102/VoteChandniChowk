package com.votechandnichowk;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class PollingStationWeb extends AppCompatActivity {
    WebView wv1;
    TextView textView;
    Handler mHandler = new Handler();
    public static String psname,p_name,f_name,ac_name,gender,epic_no;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nvsp_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.skip:
                startActivity(new Intent(this, PollingStation.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polling_station_web);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.know_your_candidate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final WebView webview = (WebView) findViewById(R.id.webview);
        final SwipeRefreshLayout swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.webViewSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webview.loadUrl("https://electoralsearch.in/");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        final ProgressDialog progress=new ProgressDialog(PollingStationWeb.this);
        progress.setMessage("Loading Page...");
        progress.show();
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.getSettings().setAllowFileAccess(true);
        swipeRefreshLayout.setRefreshing(false);
        webview.addJavascriptInterface(new MyJavaScriptInterface(this), "HtmlViewer");
        webview.loadUrl("https://electoralsearch.in/");
        webview.setWebViewClient(new WebViewClient() {
                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         if (progress.isShowing()){
                                             progress.dismiss();
                                         }
                                         webview.loadUrl("javascript:window.HtmlViewer.showHTML" +"('&lt;html&gt;'+document.getElementsByTagName('form')[2].innerHTML+'&lt;/html&gt;');");
                                     }
                                     @Override
                                     public void onReceivedSslError(WebView webView, SslErrorHandler handler, SslError error) {
                                         handler.proceed(); // Ignore SSL certificate errors
                                     }
                                 }
        );
        webview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String webUrl = webview.getUrl();
                if (webUrl == "https://electoralsearch.in/##resultArea") {
                }
                //Toast.makeText(getApplicationContext(), webUrl, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }


    class MyJavaScriptInterface {
        private Context ctx;

        MyJavaScriptInterface(Context ctx) {
            this.ctx = ctx;
        }

        @JavascriptInterface
        public void showHTML(final String html) {
            final String html_ = html;
            mHandler.post(new Runnable() {
                              @Override
                              public void run() {
                                  //Toast.makeText(ctx, html_, Toast.LENGTH_LONG).show();
                                  String[] b = html_.split("<input type=\"hidden\" id=\"acno\" name=\"ac_no\" value=\"");
                                  String[] ac_no = b[1].split("\">");
                                  String[] a = html_.split("<input type=\"hidden\" name=\"ps_name\" value=\"");
                                  String[] ps_name = a[1].split("\">");
                                  psname = ps_name[0];
                                  a = html_.split("<input type=\"hidden\" name=\"name\" value=\"");
                                  ps_name = a[1].split("\">");
                                  p_name = ps_name[0];
                                  a = html_.split("<input type=\"hidden\" name=\"gender\" value=\"");
                                  ps_name = a[1].split("\">");
                                  gender = ps_name[0];
                                  a = html_.split("<input type=\"hidden\" name=\"ac_name\" value=\"");
                                  ps_name = a[1].split("\">");
                                  ac_name = ps_name[0];
                                  //Toast.makeText(ctx, ac_no[0], Toast.LENGTH_LONG).show();
                                  //Toast.makeText(ctx, ps_name[0], Toast.LENGTH_SHORT).show();
                                  if (ac_no[0].equals("4")||ac_no[0].equals("14")||ac_no[0].equals("15")||ac_no[0].equals("16")||ac_no[0].equals("17")||ac_no[0].equals("18")||ac_no[0].equals("19")||ac_no[0].equals("20")||ac_no[0].equals("21")||ac_no[0].equals("22")){
                                      if (ps_name[0] != null) {
                                          AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(PollingStationWeb.this);
                                          alertDialogBuilder.setTitle("Polling Station Details");
                                          alertDialogBuilder.setMessage("AC No.: "+ac_no[0]+"\nPolling Station Name: "+psname);
                                          alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialog, int which) {
                                                  startActivity(new Intent(PollingStationWeb.this, PollingStation.class));
                                              }
                                          });
                                          AlertDialog alertDialog=alertDialogBuilder.create();
                                          alertDialog.show();
                                      }
                                  }
                                  else{
                                      AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(PollingStationWeb.this);
                                      alertDialogBuilder.setMessage("Sorry, currently this feature is only for Chandni Chowk Constituency");
                                      alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                          @Override
                                          public void onClick(DialogInterface dialog, int which) {
                                              startActivity(new Intent(PollingStationWeb.this, Main2Activity.class));
                                              finish();
                                          }
                                      });
                                      AlertDialog alertDialog=alertDialogBuilder.create();
                                      alertDialog.show();


                                  }

                              }
                          }
            );
        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
