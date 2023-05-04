package com.lucascorrea.tasklist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class TaskForm extends AppCompatActivity {

    private EditText titleTask, priorityTask;
    private Button btnAddTask;
    private RadioGroup radioGroupExecuted;
    private String executedStatus;
    private Task task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        titleTask = findViewById(R.id.titleTask);
        priorityTask = findViewById(R.id.priority);
        radioGroupExecuted = findViewById(R.id.radioGroupExecuted);
        btnAddTask = findViewById(R.id.btnAddTask);

        executedStatus = getString(R.string.task_noExecuted);

        radioGroupExecuted.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioExecuted) {
                    executedStatus = getString(R.string.task_executed);
                } else {
                    executedStatus = getString(R.string.task_noExecuted);
                }
            }
        });

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        String title = titleTask.getText().toString();

        if(title.isEmpty()) {
            Toast.makeText(this, getString(R.string.fill_title), Toast.LENGTH_LONG).show();
        } else {
            task = new Task();

            task.setTitle(title);
            task.setPriority(priorityTask.getText().toString());
            task.setExecuted(executedStatus);

            TaskDAO.insert(this, task);
            titleTask.setText("");
            priorityTask.setText("");
            radioGroupExecuted.clearCheck();
            task = null;
        }
    }
}