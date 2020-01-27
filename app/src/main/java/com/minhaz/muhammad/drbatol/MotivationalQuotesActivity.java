package com.minhaz.muhammad.drbatol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.Random;

public class MotivationalQuotesActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivational_quotes);


        viewFlipper = findViewById(R.id.view_flipper);


    }

    public void nextView(View view){

        viewFlipper.showNext();
    }

    public void prevView(View view){
        viewFlipper.showPrevious();
    }


}
