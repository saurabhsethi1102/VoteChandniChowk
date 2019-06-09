package com.votechandnichowk;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PWDActivity extends AppCompatActivity {

    private EditText epic_number;
    public static String EpicNO;
    public static int clicked=0;
    Button submit;
    Button pwdApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.facility_for_pwd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        epic_number = (EditText)findViewById(R.id.pwd_epic_number_edit_text);
        submit=findViewById(R.id.submit);
        if (Main2Activity.lang==1){
            updateViews("hi");
        }
        else{
            updateViews("en");
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked=1;
                if (epic_number.getText().toString().equals("")){
                    Toast.makeText(PWDActivity.this, "EPIC Number Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    checkPWD checkPWD=new checkPWD(PWDActivity.this);
                    EpicNO=epic_number.getText().toString();
                    checkPWD.execute(epic_number.getText().toString());
                }
            }
        });

        pwdApp = (Button) findViewById(R.id.pwdApp);
        pwdApp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                Intent pwdIntent = getPackageManager().getLaunchIntentForPackage("pwd.eci.com.pwdapp");
                if (pwdIntent!= null) {
                    startActivity(pwdIntent);
                } else {
                    Intent i=new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://play.google.com/store/apps/details?id=pwd.eci.com.pwdapp"));
                    startActivity(i);
                }
            }
        });
    }

    public void updateViews(String languageCode){
        Context context=LocaleHelper.setLocale(this, languageCode);
        Resources resources=context.getResources();

        getSupportActionBar().setTitle(resources.getString(R.string.facility_for_pwd));
        epic_number.setHint(resources.getString(R.string.enter_epic));
        submit.setText(resources.getString(R.string.submit));
        TextView text=findViewById(R.id.clickPWDApp);
        text.setText(resources.getString(R.string.click_on_button));
        //pwdApp.setText(resources.getString(R.string.open_pwd_app));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
