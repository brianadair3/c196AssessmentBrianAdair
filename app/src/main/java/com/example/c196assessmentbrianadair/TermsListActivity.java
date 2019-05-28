package com.example.c196assessmentbrianadair;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class TermsListActivity extends AppCompatActivity {

    private static final String LOG_TAG = TermsListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.termListAddBtn);
        fab.setOnClickListener(view -> startActivity(new Intent(this, TermDetail.class)));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //insertTerm("Test title", "2019-05-25", "2019-05-26");

        Cursor cursor = getContentResolver().query(DbProvider.TERMS_CONTENT_URI, DBOpenHelper.TERMS_COLUMNS, null, null, DBOpenHelper.TERM_START_DATE);
        ArrayList<Term> terms = new ArrayList<>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Term tempTerm = new Term(cursor.getString(cursor.getColumnIndex(DBOpenHelper.TERM_TITLE)),cursor.getString(cursor.getColumnIndex(DBOpenHelper.TERM_START_DATE)),cursor.getString(cursor.getColumnIndex(DBOpenHelper.TERM_END_DATE)));
            tempTerm.setTermId(cursor.getString(cursor.getColumnIndex(DBOpenHelper.TERM_ID)));
            terms.add(tempTerm);
        }
        RecyclerView rvTerms = findViewById(R.id.termsListView);
        TermsAdapter adapter = new TermsAdapter(terms);
        rvTerms.setAdapter(adapter);
        rvTerms.setLayoutManager(new LinearLayoutManager(this));
    }

    public void launchActivityTermDetail(View view) {
        Log.d(LOG_TAG, "Button clicked!");

        Intent intent = new Intent(this, TermDetail.class);
        startActivity(intent);
    }
}
