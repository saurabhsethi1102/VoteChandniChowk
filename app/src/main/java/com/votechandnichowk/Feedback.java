package com.votechandnichowk;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class Feedback extends AppCompatActivity {

    TextView suggestText, message, max250Text, rateUsText, yourRatingText;
    EditText feedbackMessage, name;
    Button submit;
    RatingBar ratingBar;
    float Value;
    TextView ratedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        suggestText=findViewById(R.id.suggest_improve);
        rateUsText=findViewById(R.id.rateUs);
        max250Text=findViewById(R.id.max250);
        yourRatingText=findViewById(R.id.your_rating);
        message = (TextView)findViewById(R.id.message);
        name = (EditText) findViewById(R.id.name);
        feedbackMessage = (EditText) findViewById(R.id.feedbackMessage);
        submit = (Button)findViewById(R.id.submit);

        ratingBar=findViewById(R.id.ratingBar);
        ratedValue=findViewById(R.id.rateValue);
        submit=findViewById(R.id.submit);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Value=ratingBar.getRating();
                ratedValue.setText(String.valueOf(Value));
            }
        });
        if (Main2Activity.lang==1){
            updateViews("hi");
        }
        else{
            updateViews("en");
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
    }

    public void send(){
        String sname = name.getText().toString();
        String sfeedback = feedbackMessage.getText().toString();
        AddFeedback addFeedback = new AddFeedback(this,message,sname,sfeedback);
        addFeedback.execute(sname,sfeedback,String.valueOf(Value));
    }

    private void updateViews(String languageCode){
        Context context=LocaleHelper.setLocale(this, languageCode);
        Resources resources=context.getResources();

        //getActionBar().setTitle(resources.getString(R.string.feedback));
        suggestText.setText(resources.getString(R.string.suggest_improve));
        name.setHint(resources.getString(R.string.feed_name));
        feedbackMessage.setHint(resources.getString(R.string.feedback2));
        max250Text.setText(resources.getString(R.string.max250));
        rateUsText.setText(resources.getString(R.string.rateus));
        yourRatingText.setText(resources.getString(R.string.your_rating));
        submit.setText(resources.getString(R.string.submit_feed));


    }
}
