package com.votechandnichowk;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdGenderActivity extends AppCompatActivity {

    private EditText epic_number;
    public static String EpicNO;
    public static int clicked=0;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_gender);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle(R.string.facilities_for_third_gender);
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
                    Toast.makeText(ThirdGenderActivity.this, "EPIC Number Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    checkThirdGender checkThirdGender=new checkThirdGender(ThirdGenderActivity.this);
                    EpicNO=epic_number.getText().toString();
                    checkThirdGender.execute(epic_number.getText().toString());
                }
            }
        });
    }
    public void updateViews(String languageCode){
        Context context=LocaleHelper.setLocale(this, languageCode);
        Resources resources=context.getResources();

        getSupportActionBar().setTitle(resources.getString(R.string.facilities_for_third_gender));
        epic_number.setHint(resources.getString(R.string.enter_epic));
        submit.setText(resources.getString(R.string.submit));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
