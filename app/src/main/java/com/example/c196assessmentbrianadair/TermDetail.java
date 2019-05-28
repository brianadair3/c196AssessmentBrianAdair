package com.example.c196assessmentbrianadair;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class TermDetail extends AppCompatActivity {

    private static final String TAG = "TermDetail";
    private DatePicker datePicker;
    private Calendar calendar;
    private EditText editTermStartDate;
    private EditText editTermEndDate;
    private TextInputLayout inputTermStartDate;
    private TextInputLayout inputTermEndDate;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTermStartDate = findViewById(R.id.textTermStartDate);
        editTermEndDate = findViewById(R.id.textTermEndDate);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showStartDate(year, month, day);
        showEndDate(year, month, day);

        FloatingActionButton fab = findViewById(R.id.termListAddBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setStartDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Select term start date", Toast.LENGTH_LONG).show();
    }

    @SuppressWarnings("deprecation")
    public void setEndDate(View view) {
        showDialog(998);
        Toast.makeText(getApplicationContext(), "Select term end date", Toast.LENGTH_LONG).show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myStartDateListener, year, month, day);
        } else if (id == 998) {
            return new DatePickerDialog(this, myEndDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myStartDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            showStartDate(year, month, dayOfMonth);
        }
    };

    private DatePickerDialog.OnDateSetListener myEndDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            showEndDate(year, month, dayOfMonth);
        }
    };

    private void showStartDate(int year, int month, int day) {
        editTermStartDate.setText(new StringBuilder().append(month).append("/").append(day).append("/").append(year));
    }

    private void showEndDate(int year, int month, int day) {
        editTermEndDate.setText(new StringBuilder().append(month).append("/").append(day).append("/").append(year));
    }

    private void insertTerm(String title, String startDate, String endDate) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.TERM_TITLE,title);
        values.put(DBOpenHelper.TERM_START_DATE,startDate);
        values.put(DBOpenHelper.TERM_END_DATE,endDate);
        Uri termUri = getContentResolver().insert(DbProvider.TERMS_CONTENT_URI,values);
        Log.d("MainActivity","Inserted term " + termUri.getLastPathSegment());
    }

}
