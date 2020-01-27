package com.minhaz.muhammad.drbatol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PrivateAddTaskActivity extends AppCompatActivity {

    private EditText editTextFeel,editTextDescrip,editTextSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_add_task);

        editTextFeel = findViewById(R.id.editTextAddFeel);
        editTextDescrip = findViewById(R.id.editTextAddDescrip);
        editTextSchedule = findViewById(R.id.editTextAddSchedule);

        findViewById(R.id.private_button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFeel();
            }
        });
    }

    private void saveFeel() {
        final String addFeel = editTextFeel.getText().toString().trim();
        final String addDescrip = editTextDescrip.getText().toString().trim();
        final String addSchedule = editTextSchedule.getText().toString().trim();

        if (addFeel.isEmpty()) {
            editTextFeel.setError("Please say what you feel");
            editTextFeel.requestFocus();
            return;
        }

        if (addDescrip.isEmpty()) {
            editTextDescrip.setError("Add description!");
            editTextDescrip.requestFocus();
            return;
        }

        if (addSchedule.isEmpty()) {
            editTextSchedule.setError("Set the schedule");
            editTextSchedule.requestFocus();
            return;
        }

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                Private pr = new Private();
                pr.setFeel(addFeel);
                pr.setDescrip(addDescrip);
                pr.setScheduleBy(addSchedule);
                pr.setDone(false);

                PrivateDatabaseClient.getpInstance(getApplicationContext()).getPrivateAppDatabase().privateDao().insert(pr);


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), PrivateLifePlannerActivity.class));

                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }
}
