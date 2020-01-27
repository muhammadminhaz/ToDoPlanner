package com.minhaz.muhammad.drbatol;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class FitnessUpdateTaskActivity extends AppCompatActivity {

    private EditText eTextFitnessWork, eTextFitnessDetails, eTextFitnessSchedule;
    private CheckBox checkBoxFit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_update_task);

        eTextFitnessWork = findViewById(R.id.editTextFitnessUpdateTask);
        eTextFitnessDetails = findViewById(R.id.editTextFitnessUpdateDetails);
        eTextFitnessSchedule = findViewById(R.id.editTextFitnessUpdateSchedule);

        checkBoxFit = findViewById(R.id.checkBoxFit);
        
        final Fitness fitness = (Fitness) getIntent().getSerializableExtra("fitness");
        loadFitness(fitness);

        findViewById(R.id.fitness_button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Updated :)", Toast.LENGTH_LONG).show();

                updateFitness(fitness);
            }
        });

        findViewById(R.id.fitness_button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(FitnessUpdateTaskActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteFitness(fitness);

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });



    }

    private void deleteFitness(final Fitness fitness) {

        class DeleteFitness extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                FitnessDatabaseClient.getfInstance(getApplicationContext()).getFitnessAppDatabase()
                        .fitnessDao()
                        .delete(fitness);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(FitnessUpdateTaskActivity.this, FitnessActivity.class));
            }
        }

        DeleteFitness df = new DeleteFitness();
        df.execute();

    }

    private void updateFitness(final Fitness fitness) {
        final String sWork = eTextFitnessWork.getText().toString().trim();
        final String sDetails = eTextFitnessDetails.getText().toString().trim();
        final String sSchedule = eTextFitnessSchedule.getText().toString().trim();

        if (sWork.isEmpty()) {
            eTextFitnessWork.setError("Not allowed to be empty");
            eTextFitnessWork.requestFocus();
            return;
        }

        if (sDetails.isEmpty()) {
            eTextFitnessDetails.setError("Not allowed to be empty");
            eTextFitnessDetails.requestFocus();
            return;
        }

        if (sSchedule.isEmpty()) {
            eTextFitnessSchedule.setError("Not allowed to be empty");
            eTextFitnessSchedule.requestFocus();
            return;
        }

        class UpdateFitness extends AsyncTask<Void, Void, Void>
        {


            @SuppressLint("WrongThread")
            @Override
            protected Void doInBackground(Void... voids) {
                fitness.setFitnessWork(sWork);
                fitness.setFitnessDetails(sDetails);
                fitness.setFitnessSchedule(sSchedule);
                fitness.setFit(checkBoxFit.isChecked());
                FitnessDatabaseClient.getfInstance(getApplicationContext()).getFitnessAppDatabase()
                        .fitnessDao()
                        .update(fitness);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(FitnessUpdateTaskActivity.this, FitnessActivity.class);
                startActivity(intent);
            }
        }

        UpdateFitness uf = new UpdateFitness();
        uf.execute();

    }

    private void loadFitness(Fitness fitness) {
        eTextFitnessWork.setText(fitness.getFitnessWork());
        eTextFitnessDetails.setText(fitness.getFitnessDetails());
        eTextFitnessSchedule.setText(fitness.getFitnessSchedule());
        checkBoxFit.setChecked(fitness.isFit());
    }
}
