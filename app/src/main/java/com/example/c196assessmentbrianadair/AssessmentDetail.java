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

public class AssessmentDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);
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




    public void insertAssessment(String title, String dueDate, String dueDateAlert, String type, String cId) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.ASSESSMENT_TITLE,title);
        values.put(DBOpenHelper.ASSESSMENT_DUE_DATE,dueDate);
        values.put(DBOpenHelper.ASSESSMENT_DUE_DATE_ALERT,dueDateAlert);
        values.put(DBOpenHelper.ASSESSMENT_TYPE,type);
        values.put(DBOpenHelper.ASSESSMENT_COURSE_ID,cId);
        Uri assessmentUri = getContentResolver().insert(DbProvider.ASSESSMENTS_CONTENT_URI,values);
        Log.d("MainActivity","Inserted assessment " + assessmentUri.getLastPathSegment());
    }

}
