package com.example.c196assessmentbrianadair;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class CourseDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.termListAddBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    private void insertCourse(String title, String startDate, String endDate, String startDateAlert, String endDateAlert, String status, String mentorName, String mentorPhone, String mentorEmail, String notes, String tId) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COURSE_TITLE,title);
        values.put(DBOpenHelper.COURSE_START_DATE,startDate);
        values.put(DBOpenHelper.COURSE_END_DATE,endDate);
        values.put(DBOpenHelper.COURSE_STATUS,status);
        values.put(DBOpenHelper.COURSE_MENTOR_NAME,mentorName);
        values.put(DBOpenHelper.COURSE_MENTOR_PHONE,mentorPhone);
        values.put(DBOpenHelper.COURSE_MENTOR_EMAIL,mentorEmail);
        values.put(DBOpenHelper.COURSE_START_DATE_ALERT,startDateAlert);
        values.put(DBOpenHelper.COURSE_END_DATE_ALERT,endDateAlert);
        values.put(DBOpenHelper.COURSE_TERM_ID,tId);
        Uri courseUri = getContentResolver().insert(DbProvider.COURSES_CONTENT_URI,values);
        Log.d("MainActivity","Inserted course " + courseUri.getLastPathSegment());
    }

}
