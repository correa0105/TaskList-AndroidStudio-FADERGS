package com.lucascorrea.tasklist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnGoTask;
    private ListView listViewTask;
    private List<Task> listTasks;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewTask = findViewById(R.id.listViewTasks);
        btnGoTask = findViewById(R.id.btnGoTask);

        listViewTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });

        btnGoTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TaskForm.class);
                i.putExtra("action", "insert");
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadTasks();
    }

    private void loadTasks() {
        listTasks = TaskDAO.getTasks(this);

        if(listTasks.isEmpty()) {
            listViewTask.setEnabled(false);
            String[] emptyList = {getString(R.string.empty_list)};
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, emptyList);
        } else {
            listViewTask.setEnabled((true));
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listTasks);
        }

        listViewTask.setAdapter(adapter);
    }

}