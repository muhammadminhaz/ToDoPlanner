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

public class UpdateTaskActivity extends AppCompatActivity {
    private EditText editTextTask, editTextDesc, editTextFinishBy;
    private CheckBox checkBoxFinished;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_update_task);

        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);

        checkBoxFinished = findViewById(R.id.checkBoxFinished);


        final Task task = (Task) getIntent().getSerializableExtra("task");

        loadTask(task);

        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Updated :)", Toast.LENGTH_LONG).show();

                updateTask(task);
            }
        });

        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTaskActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteTask(task);

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

    private void loadTask(Task task) {
        editTextTask.setText(task.getTask());
        editTextDesc.setText(task.getDesc());
        editTextFinishBy.setText(task.getFinishBy());
        checkBoxFinished.setChecked(task.isFinished());
    }

    private void updateTask(final Task task) {

        final String sTask = editTextTask.getText().toString().trim();
        final String sDesc = editTextDesc.getText().toString().trim();
        final String sFinishBy = editTextFinishBy.getText().toString().trim();

        if (sTask.isEmpty()) {
            editTextTask.setError("Not allowed to be empty");
            editTextTask.requestFocus();
            return;
        }

        if (sDesc.isEmpty()) {
            editTextDesc.setError("Not allowed to be empty");
            editTextDesc.requestFocus();
            return;
        }

        if (sFinishBy.isEmpty()) {
            editTextFinishBy.setError("Not allowed to be empty");
            editTextFinishBy.requestFocus();
            return;
        }

        class UpdateTask extends AsyncTask<Void, Void, Void> {

            @SuppressLint("WrongThread")
            @Override
            protected Void doInBackground(Void... voids) {
                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinishBy(sFinishBy);
                task.setFinished(checkBoxFinished.isChecked());
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .update(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(UpdateTaskActivity.this, StudyPlannerActivity.class);
                startActivity(intent);
            }

        }

        UpdateTask ut = new UpdateTask();
        ut.execute();
    }


    private void deleteTask(final Task task) {
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateTaskActivity.this, StudyPlannerActivity.class));
            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();

    }
}
