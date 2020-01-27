package com.minhaz.muhammad.drbatol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.minhaz.muhammad.drbatol.ui.PrivateAdapter;

import java.util.List;

public class PrivateLifePlannerActivity extends AppCompatActivity {

    private FloatingActionButton privateAddTaskButton;
    private RecyclerView privateRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_life_planner);

        privateAddTaskButton = findViewById(R.id.private_floating_button_add);
        privateRecyclerView = findViewById(R.id.private_recyclerview);
        privateRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        privateAddTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrivateLifePlannerActivity.this,PrivateAddTaskActivity.class);

                startActivity(intent);
            }
        });

        privateGetTasks();
    }

    private void privateGetTasks() {
        class PrivateGetTasks extends AsyncTask<Void, Void, List<Private>>
        {
            @Override
            protected List<Private> doInBackground(Void... voids) {

                List<Private> privateList = PrivateDatabaseClient.
                        getpInstance(getApplicationContext())
                        .getPrivateAppDatabase()
                        .privateDao()
                        .getAll();

                return privateList;
            }

            @Override
            protected void onPostExecute(List<Private> aVoid) {
                super.onPostExecute(aVoid);

                PrivateAdapter adapter = new PrivateAdapter(PrivateLifePlannerActivity.this,aVoid);
                privateRecyclerView.setAdapter(adapter);


            }
        }

        PrivateGetTasks privateGetTasks = new PrivateGetTasks();
        privateGetTasks.execute();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PrivateLifePlannerActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
