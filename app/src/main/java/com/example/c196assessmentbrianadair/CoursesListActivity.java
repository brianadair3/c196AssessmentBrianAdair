package com.example.c196assessmentbrianadair;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class CoursesListActivity extends AppCompatActivity {

    private static final String LOG_TAG = CoursesListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.courseListAddBtn);
        fab.setOnClickListener(view -> startActivity(new Intent(this, CourseDetail.class)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void launchActivityCourseDetail(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, CourseDetail.class);
        startActivity(intent);
    }

}
