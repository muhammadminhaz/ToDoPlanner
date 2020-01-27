package com.minhaz.muhammad.drbatol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FitnessAddTaskActivity extends AppCompatActivity {

    private EditText editTxtFitnessWork, editTxtFitnessDetails, editTxtFitnessSchedule;
    private Button fitnessSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiteness_add_task);

        editTxtFitnessWork = findViewById(R.id.editTextFitnessWork);
        editTxtFitnessDetails = findViewById(R.id.editTextFitnessDetails);
        editTxtFitnessSchedule = findViewById(R.id.editTextFitnessSchedule);
        fitnessSave = findViewById(R.id.fitness_button_save);

        fitnessSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFitness();
            }
        });
    }

    private void saveFitness() {
        final String sFitnessWork = editTxtFitnessWork.getText().toString().trim();
        final String sFitnessDetails = editTxtFitnessDetails.getText().toString().trim();
        final String sSchedule = editTxtFitnessSchedule.getText().toString().trim();

        if (sFitnessWork.isEmpty()) {
            editTxtFitnessWork.setError("No empty fields allowed");
            editTxtFitnessWork.requestFocus();
            return;
        }

        if (sFitnessDetails.isEmpty()) {
            editTxtFitnessDetails.setError("No empty fields allowed");
            editTxtFitnessDetails.requestFocus();
            return;
        }

        if (sSchedule.isEmpty()) {
            editTxtFitnessSchedule.setError("No empty fields allowed");
            editTxtFitnessSchedule.requestFocus();
            return;
        }

        class SaveFitness extends AsyncTask<Void, Void, Void>
        {

            @Override
            protected Void doInBackground(Void... voids) {
                Fitness fitness = new Fitness();
                fitness.setFitnessWork(sFitnessWork);
                fitness.setFitnessDetails(sFitnessDetails);
                fitness.setFitnessSchedule(sSchedule);
                fitness.setFit(false);

                FitnessDatabaseClient.getfInstance(getApplicationContext()).getFitnessAppDatabase().fitnessDao().insert(fitness);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(),FitnessActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

            }
        }

        SaveFitness st = new SaveFitness();
        st.execute();
    }
}
