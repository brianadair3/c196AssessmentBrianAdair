package com.example.c196assessmentbrianadair;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.solver.widgets.Helper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void launchActivityTerms(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, TermsListActivity.class);
        startActivity(intent);
    }

    public void launchActivityAssessments(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, AssessmentsListActivity.class);
        startActivity(intent);
    }

    public void launchActivityCourses(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, CoursesListActivity.class);
        startActivity(intent);
    }
}
