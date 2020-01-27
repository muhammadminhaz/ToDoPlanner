package com.minhaz.muhammad.drbatol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private CardView studyCard, motivationCard;
    private CardView privateLife, fitnessLife;
    private Toolbar tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studyCard = findViewById(R.id.study_card);
        motivationCard = findViewById(R.id.motivation_card);
        privateLife = findViewById(R.id.private_life);
        fitnessLife = findViewById(R.id.fitness_card);
        tool = findViewById(R.id.toolbar);


        setSupportActionBar(tool);

        studyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudyPlannerActivity.class);
                startActivity(intent);
            }
        });

        motivationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MotivationalQuotesActivity.class);
                startActivity(intent);
            }
        });

        privateLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PrivateLifePlannerActivity.class);
                startActivity(intent);
            }
        });

        fitnessLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FitnessActivity.class);
                startActivity(intent);
            }
        });

    }
}
