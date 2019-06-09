package com.votechandnichowk;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class AboutChandniChowk extends AppCompatActivity {

    ViewFlipper v_flipper;
    TextView chandniChowkText, parliamentText, aboutCCText, constituencyText, noVotesText, maleText, femaleText, pwdText, ThirdGenderText, shakurText, trinagarText;
    TextView shalimarText, wazirpurText, sadarText, adarshText, modelText, chandniText, ballimaranText, matiaText;

    int images[] =
            {
                    R.drawable.chandni_chowk,
                    R.drawable.shopping,
                    R.drawable.food,
                    R.drawable.sightseeing
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_chandni_chowk);
        chandniChowkText = findViewById(R.id.chandni_chowk);
        parliamentText= findViewById(R.id.parliament);
        constituencyText=findViewById(R.id.constituency);
        aboutCCText= findViewById(R.id.about_cc);
        noVotesText= findViewById(R.id.no_votes);
        maleText= findViewById(R.id.male);
        femaleText= findViewById(R.id.female);
        pwdText= findViewById(R.id.pwd);
        ThirdGenderText= findViewById(R.id.thirdGender);
        shakurText= findViewById(R.id.shakur);
        trinagarText= findViewById(R.id.trinagar);
        shalimarText= findViewById(R.id.shalimar);
        wazirpurText= findViewById(R.id.wazirpur);
        sadarText= findViewById(R.id.sadar);
        adarshText= findViewById(R.id.adarsh);
        modelText= findViewById(R.id.model);
        chandniText= findViewById(R.id.chandni);
        ballimaranText= findViewById(R.id.ballimaran);
        matiaText= findViewById(R.id.matia);

        v_flipper = findViewById(R.id.flipper);
        for(int i=0;i<images.length;i++)
        {
            flip_image(images[i]);
        }

        if (Main2Activity.lang==1){
            updateViews("hi");
        }
        else{
            updateViews("en");
        }

    }

    public void flip_image(int i)
    {
        ImageView view = new ImageView(this);
        view.setBackgroundResource(i);
        v_flipper.addView(view);
        v_flipper.setFlipInterval(2200);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }

    private void updateViews(String languageCode){
        Context context=LocaleHelper.setLocale(this, languageCode);
        Resources resources=context.getResources();

        chandniChowkText.setText(resources.getString(R.string.chandni_chowk));
        parliamentText.setText(resources.getString(R.string.parliament_constituency));
        aboutCCText.setText(resources.getString(R.string.chandni_chowk_content));
        constituencyText.setText(resources.getString(R.string.constituency));
        noVotesText.setText(resources.getString(R.string.number_of_votes));
        maleText.setText(resources.getString(R.string.male));
        femaleText.setText(resources.getString(R.string.female));
        pwdText.setText(resources.getString(R.string.pwd));
        ThirdGenderText.setText(resources.getString(R.string.third_gender));
        shakurText.setText(resources.getString(R.string.shakur_basti));
        trinagarText.setText(resources.getString(R.string.tri_nagar));
        shalimarText.setText(resources.getString(R.string.shalimar_bagh));
        wazirpurText.setText(resources.getString(R.string.wazirpur));
        sadarText.setText(resources.getString(R.string.sadar_bazar));
        adarshText.setText(resources.getString(R.string.adarsh_nagar));
        modelText.setText(resources.getString(R.string.model_town));
        chandniText.setText(resources.getString(R.string.chandni_chowk));
        ballimaranText.setText(resources.getString(R.string.ballimaran));
        matiaText.setText(resources.getString(R.string.matia_mahal));


    }

}
