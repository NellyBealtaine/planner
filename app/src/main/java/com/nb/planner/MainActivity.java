package com.nb.planner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView _totaltasks, _donetasks, _actualtasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _totaltasks = findViewById(R.id._totaltasks);
        _donetasks = findViewById(R.id._donetasks);
        _actualtasks = findViewById(R.id._actualtasks);
    }

    public void AddTaskForm(View v){
        Intent intentadd = new Intent(this, AddForm.class);
        startActivity(intentadd);
    }
    public void ViewTask(View v){
        Intent intentview = new Intent(this, ViewList.class);
        startActivity(intentview);
    }
}
