package com.example.c196assessmentbrianadair;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    //Constants for db name and version
    public static final String DATABASE_NAME = "wgu.db";
    public static final int DATABASE_VERSION = 1;

    //Constants for identifying table and columns for activity_terms
    public static final String TABLE_TERMS = "terms";
    public static final String TERM_ID = "_id";
    public static final String TERM_TITLE = "title";
    public static final String TERM_START_DATE = "startDate";
    public static final String TERM_END_DATE = "endDate";

    //Constants for identifying table and columns for courses
    public static final String COURSE_ID = "_id";
    public static final String TABLE_COURSES = "courses";
    public static final String COURSE_TITLE = "title";
    public static final String COURSE_START_DATE = "startDate";
    public static final String COURSE_START_DATE_ALERT = "startDateAlert";
    public static final String COURSE_END_DATE_ALERT = "endDateAlert";
    public static final String COURSE_END_DATE = "endDate";
    public static final String COURSE_STATUS = "status";
    public static final String COURSE_MENTOR_NAME = "mentorName";
    public static final String COURSE_MENTOR_PHONE = "mentorPhone";
    public static final String COURSE_MENTOR_EMAIL = "mentorEmail";
    public static final String COURSE_TERM_ID = "tId";

    //Constants for identifying table and columns for assessments
    public static final String TABLE_ASSESSMENTS = "assessments";
    public static final String ASSESSMENT_ID = "_id";
    public static final String ASSESSMENT_TITLE = "title";
    public static final String ASSESSMENT_DUE_DATE = "dueDate";
    public static final String ASSESSMENT_DUE_DATE_ALERT = "dueDateAlert";
    public static final String ASSESSMENT_TYPE = "type";
    public static final String ASSESSMENT_COURSE_ID = "cId";

    //Constants for identifying table and columns for notes
    public static final String TABLE_NOTES = "notes";
    public static final String NOTE_ID = "_id";
    public static final String NOTE_TITLE = "title";
    public static final String NOTE_TEXT = "text";
    public static final String NOTE_COURSE_ID = "cId";

    //SQL to create activity_terms table
    private static final String TERMS_TABLE_CREATE =
            "CREATE TABLE " + TABLE_TERMS + " (" +
                    TERM_ID + " TEXT PRIMARY KEY AUTOINCREMENT, " +
                    TERM_TITLE + " TEXT NOT NULL, " +
                    TERM_START_DATE + " TEXT, " +
                    TERM_END_DATE + " TEXT " +
                    ")";

    //SQL to create courses table
    private static final String COURSES_TABLE_CREATE =
            "CREATE TABLE " + TABLE_COURSES + " (" +
                    COURSE_ID + " TEXT PRIMARY KEY AUTOINCREMENT, " +
                    COURSE_TITLE + " TEXT NOT NULL, " +
                    COURSE_START_DATE + " TEXT, " +
                    COURSE_START_DATE_ALERT + " TEXT NOT NULL, " +
                    COURSE_END_DATE + " TEXT, " +
                    COURSE_END_DATE_ALERT + " TEXT NOT NULL, " +
                    COURSE_STATUS + " TEXT, " +
                    COURSE_MENTOR_NAME + " TEXT, " +
                    COURSE_MENTOR_PHONE + " TEXT, " +
                    COURSE_MENTOR_EMAIL + " TEXT, " +
                    COURSE_TERM_ID + " TEXT NOT NULL REFERENCES " + TABLE_TERMS + " (" + TERM_ID + ") " +
                    ")";

    //SQL to create assessments table
    private static final String ASSESSMENTS_TABLE_CREATE =
            "CREATE TABLE " + TABLE_ASSESSMENTS + " (" +
                    ASSESSMENT_ID + " TEXT PRIMARY KEY AUTOINCREMENT, " +
                    ASSESSMENT_TITLE + " TEXT NOT NULL, " +
                    ASSESSMENT_DUE_DATE + " TEXT, " +
                    ASSESSMENT_DUE_DATE_ALERT + " TEXT NOT NULL, " +
                    ASSESSMENT_TYPE + " TEXT, " +
                    ASSESSMENT_COURSE_ID + " TEXT NOT NULL REFERENCES " + TABLE_COURSES + " (" + COURSE_ID + ") " +
                    ")";

    //SQL to create notes table
    private static final String NOTES_TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOTES + " (" +
                    NOTE_ID + " TEXT PRIMARY KEY AUTOINCREMENT, " +
                    NOTE_TITLE + " TEXT NOT NULL, " +
                    NOTE_TEXT + " TEXT, " +
                    NOTE_COURSE_ID + " TEXT NOT NULL REFERENCES " + TABLE_COURSES + " (" + COURSE_ID + ") " +
                    ")";

    public static final String[] TERMS_COLUMNS =
            {TERM_ID,
             TERM_TITLE,
             TERM_START_DATE,
             TERM_END_DATE};
    public static final String[] COURSES_COLUMNS =
            {COURSE_ID,
             COURSE_TITLE,
             COURSE_START_DATE,
             COURSE_END_DATE,
             COURSE_STATUS,
             COURSE_MENTOR_NAME,
             COURSE_MENTOR_PHONE,
             COURSE_MENTOR_EMAIL,
             COURSE_START_DATE_ALERT,
             COURSE_END_DATE_ALERT,
             COURSE_TERM_ID};
    public static final String[] ASSESSMENTS_COLUMNS =
            {ASSESSMENT_ID,
             ASSESSMENT_TITLE,
             ASSESSMENT_DUE_DATE,
             ASSESSMENT_DUE_DATE_ALERT,
             ASSESSMENT_TYPE,
             ASSESSMENT_COURSE_ID};
    public static final String[] NOTES_COLUMNS =
            {NOTE_ID,
             NOTE_TITLE,
             NOTE_TEXT,
             NOTE_COURSE_ID};

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TERMS_TABLE_CREATE);
        db.execSQL(COURSES_TABLE_CREATE);
        db.execSQL(ASSESSMENTS_TABLE_CREATE);
        db.execSQL(NOTES_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TERMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSESSMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }
}
