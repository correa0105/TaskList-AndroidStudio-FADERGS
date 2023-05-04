package com.lucascorrea.tasklist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    public static void insert(Context context, Task task) {
        SQLiteDatabase db = new Connection(context).getWritableDatabase();

        ContentValues valuesDB = new ContentValues();

        valuesDB.put("title", task.getTitle());
        valuesDB.put("priority", task.getPriority());
        valuesDB.put("executed", task.getExecuted());

        db.insert("tasks", null, valuesDB);
    }

    public static List<Task> getTasks(Context context) {
        SQLiteDatabase db =  new Connection(context).getReadableDatabase();

        List<Task> taskList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT id, title, priority, executed FROM tasks ORDER BY title", null);

        if(cursor !=  null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Task task = new Task();
                task.setId(cursor.getInt(0));
                task.setTitle(cursor.getString(1));
                task.setPriority(cursor.getString(2));
                task.setExecuted(cursor.getString(3));
                taskList.add(task);
            } while (cursor.moveToNext());
        }

        return taskList;
    }

}
