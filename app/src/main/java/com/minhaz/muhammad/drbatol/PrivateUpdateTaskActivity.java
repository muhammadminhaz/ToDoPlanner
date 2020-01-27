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
import android.widget.TextView;
import android.widget.Toast;

public class PrivateUpdateTaskActivity extends AppCompatActivity {

    private TextView editTxtFeel, editTxtDescrip, editTxtSchedule;
    private CheckBox checkBoxPrivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_update_task);

        editTxtFeel = findViewById(R.id.editTextFeelUpdate);
        editTxtDescrip = findViewById(R.id.editTextDescripUpdate);
        editTxtSchedule = findViewById(R.id.editTextScheduleByUpdate);

        checkBoxPrivate = findViewById(R.id.checkBoxDone);

        final Private po = (Private) getIntent().getSerializableExtra("feel");
        privateLoadTask(po);

        findViewById(R.id.private_button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                privateUpdateTask(po);
            }
        });

        findViewById(R.id.private_button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PrivateUpdateTaskActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteTask(po);
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

    private void privateLoadTask(Private po)
    {
        editTxtFeel.setText(po.getFeel());
        editTxtDescrip.setText(po.getDescrip());
        editTxtSchedule.setText(po.getScheduleBy());
        checkBoxPrivate.setChecked(po.isDone());
    }

    private void privateUpdateTask(final Private po)
    {
        final String updateFeel = editTxtFeel.getText().toString().trim();
        final String updateDescrip = editTxtDescrip.getText().toString().trim();
        final String updateSchedule = editTxtSchedule.getText().toString().trim();

        if (updateFeel.isEmpty()) {
            editTxtFeel.setError("Not allowed to be empty");
            editTxtFeel.requestFocus();
            return;
        }

        if (updateDescrip.isEmpty()) {
            editTxtDescrip.setError("Not allowed to be empty");
            editTxtDescrip.requestFocus();
            return;
        }

        if (updateSchedule.isEmpty()) {
            editTxtSchedule.setError("Not allowed to be empty");
            editTxtSchedule.requestFocus();
            return;
        }

        class PrivateUpdateTask extends AsyncTask<Void,Void,Void> {
            @SuppressLint("WrongThread")
            @Override
            protected Void doInBackground(Void... voids) {

                po.setFeel(updateFeel);
                po.setDescrip(updateDescrip);
                po.setScheduleBy(updateSchedule);
                po.setDone(checkBoxPrivate.isChecked());
                PrivateDatabaseClient.getpInstance(getApplicationContext()).getPrivateAppDatabase().privateDao().update(po);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(PrivateUpdateTaskActivity.this, PrivateLifePlannerActivity.class);
                startActivity(intent);
            }
        }

        PrivateUpdateTask ut = new PrivateUpdateTask();
        ut.execute();
    }

    private void deleteTask(final Private po) {
        class PrivateDeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                PrivateDatabaseClient.getpInstance(getApplicationContext()).getPrivateAppDatabase()
                        .privateDao()
                        .delete(po);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(PrivateUpdateTaskActivity.this, PrivateLifePlannerActivity.class);
                startActivity(intent);
            }
        }

        PrivateDeleteTask dt = new PrivateDeleteTask();
        dt.execute();

    }


}
