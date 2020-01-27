package com.minhaz.muhammad.drbatol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.minhaz.muhammad.drbatol.ui.FitnessAdapter;

import java.util.List;

public class FitnessActivity extends AppCompatActivity {

    private FloatingActionButton fitnessButton;
    private RecyclerView fitnessRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);

        fitnessButton = findViewById(R.id.fitness_floating_button_add);
        fitnessRecyclerView = findViewById(R.id.fitness_recyclerview);
        fitnessRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fitnessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FitnessActivity.this,FitnessAddTaskActivity.class);
                startActivity(intent);

            }
        });
        fitnessGetTasks();
    }

    private void fitnessGetTasks() {
        class FitnessGetTasks extends AsyncTask<Void, Void, List<Fitness>>
        {

            @Override
            protected List<Fitness> doInBackground(Void... voids) {
                List<Fitness> fitnessList = FitnessDatabaseClient
                        .getfInstance(getApplicationContext())
                        .getFitnessAppDatabase()
                        .fitnessDao()
                        .getAll();
                return fitnessList;
            }

            @Override
            protected void onPostExecute(List<Fitness> fitnesses) {
                super.onPostExecute(fitnesses);
                FitnessAdapter adapter = new FitnessAdapter(FitnessActivity.this, fitnesses);
                fitnessRecyclerView.setAdapter(adapter);
            }
        }

        FitnessGetTasks fitnessGetTasks = new FitnessGetTasks();
        fitnessGetTasks.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FitnessActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
