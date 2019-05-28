package com.example.c196assessmentbrianadair;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Switch;


public class DbProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.c196assessmentbrianadair.dbprovider";
    private static final String TERMS_BASE_PATH = "terms";
    private static final String COURSES_BASE_PATH = "courses";
    private static final String ASSESSMENTS_BASE_PATH = "assessments";
    private static final String NOTES_BASE_PATH = "notes";

    public static final Uri TERMS_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TERMS_BASE_PATH);
    public static final Uri COURSES_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + COURSES_BASE_PATH);
    public static final Uri ASSESSMENTS_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + ASSESSMENTS_BASE_PATH);
    public static final Uri NOTES_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + NOTES_BASE_PATH);

    //Constant to identify requested operation
    private static final int TERMS = 1;
    private static final int TERMS_ID = 2;
    private static final int COURSES = 3;
    private static final int COURSES_ID = 4;
    private static final int ASSESSMENTS = 5;
    private static final int ASSESSMENTS_ID = 6;
    private static final int NOTES = 7;
    private static final int NOTES_ID = 8;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, TERMS_BASE_PATH, TERMS);
        uriMatcher.addURI(AUTHORITY,TERMS_BASE_PATH + "/#", TERMS_ID);
        uriMatcher.addURI(AUTHORITY, COURSES_BASE_PATH, COURSES);
        uriMatcher.addURI(AUTHORITY, COURSES_BASE_PATH + "/#", COURSES_ID);
        uriMatcher.addURI(AUTHORITY, ASSESSMENTS_BASE_PATH, ASSESSMENTS);
        uriMatcher.addURI(AUTHORITY, ASSESSMENTS_BASE_PATH + "/#", ASSESSMENTS_ID);
        uriMatcher.addURI(AUTHORITY, NOTES_BASE_PATH, NOTES);
        uriMatcher.addURI(AUTHORITY, NOTES_BASE_PATH + "/#", NOTES_ID);
    }

    private DBOpenHelper helper;

    @Override
    public boolean onCreate() {
        helper = new DBOpenHelper(getContext());
        return helper != null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch(uriMatcher.match(uri)) {
            case TERMS: {
                cursor = getAllTerms(uri);
                break;
            }
            case TERMS_ID: {
                cursor = getSingleTerm(uri);
                break;
            }
            case COURSES: {
                cursor = getTermCourses(uri, selectionArgs);
                break;
            }
            case COURSES_ID: {
                cursor = getSingleCourse(uri);
                break;
            }
            case ASSESSMENTS: {
                cursor = getCourseAssessments(uri, selectionArgs);
                break;
            }
            case ASSESSMENTS_ID: {
                cursor = getSingleAssessment(uri);
                break;
            }
            case NOTES: {
                cursor = getCourseNotes(uri, selectionArgs);
                break;
            }
            case NOTES_ID: {
                cursor = getSingleNote(uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = helper.getWritableDatabase();
        Uri _uri = null;
        switch(uriMatcher.match(uri)) {
            case TERMS:
                long _TID = db.insert(DBOpenHelper.TABLE_TERMS, "", values);
                if (_TID > 0) {
                    _uri = ContentUris.withAppendedId(TERMS_CONTENT_URI, _TID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case COURSES:
                long _CID = db.insert(DBOpenHelper.TABLE_COURSES, "", values);
                if(_CID > 0) {
                    _uri = ContentUris.withAppendedId(COURSES_CONTENT_URI, _CID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case ASSESSMENTS:
                long _AID = db.insert(DBOpenHelper.TABLE_ASSESSMENTS,"", values);
                if(_AID > 0) {
                    _uri = ContentUris.withAppendedId(ASSESSMENTS_CONTENT_URI, _AID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case NOTES:
                long _NID = db.insert(DBOpenHelper.TABLE_NOTES,"", values);
                if(_NID > 0) {
                    _uri = ContentUris.withAppendedId(NOTES_CONTENT_URI, _NID);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            default: throw new SQLException("Failed to insert row into " + uri);
        }
        return _uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = helper.getWritableDatabase();
        switch(uriMatcher.match(uri)) {
            case TERMS: {
                return db.delete(DBOpenHelper.TABLE_TERMS,selection,selectionArgs);
            }
            case COURSES: {
                return db.delete(DBOpenHelper.TABLE_COURSES,selection,selectionArgs);
            }
            case ASSESSMENTS: {
                return db.delete(DBOpenHelper.TABLE_ASSESSMENTS,selection,selectionArgs);
            }
            case NOTES: {
                return db.delete(DBOpenHelper.TABLE_NOTES,selection,selectionArgs);
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = helper.getWritableDatabase();
        switch(uriMatcher.match(uri)) {
            case TERMS: {
                return db.update(DBOpenHelper.TABLE_TERMS,values,selection,selectionArgs);
            }
            case COURSES: {
                return db.update(DBOpenHelper.TABLE_COURSES,values,selection,selectionArgs);
            }
            case ASSESSMENTS: {
                return db.update(DBOpenHelper.TABLE_ASSESSMENTS,values,selection,selectionArgs);
            }
            case NOTES: {
                return db.update(DBOpenHelper.TABLE_NOTES,values,selection,selectionArgs);
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    public Cursor getSingleTerm(Uri uri) {
        Cursor cursor;
        switch(uriMatcher.match(uri)) {
            case TERMS_ID: {
                String _ID = uri.getLastPathSegment();
                String[] selectionArguments = new String[]{_ID};
                cursor = helper.getReadableDatabase().query(
                        DBOpenHelper.TABLE_TERMS, DBOpenHelper.TERMS_COLUMNS, DBOpenHelper.TERM_ID + " = ?", selectionArguments, null, null, null
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    public Cursor getAllTerms(Uri uri) {
        Cursor cursor;
        switch(uriMatcher.match(uri)) {
            case TERMS: {
                cursor = helper.getReadableDatabase().query(
                        DBOpenHelper.TABLE_TERMS, DBOpenHelper.TERMS_COLUMNS, null, null, null, null, DBOpenHelper.TERM_START_DATE
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    public Cursor getSingleCourse(Uri uri) {
        Cursor cursor;
        switch(uriMatcher.match(uri)) {
            case COURSES_ID: {
                String _ID = uri.getLastPathSegment();
                String[] selectionArguments = new String[]{_ID};
                cursor = helper.getReadableDatabase().query(
                        DBOpenHelper.TABLE_COURSES, DBOpenHelper.COURSES_COLUMNS, DBOpenHelper.COURSE_ID + " = ?", selectionArguments, null, null, null
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    public Cursor getTermCourses(Uri uri, String[] selectionArgs) {
        Cursor cursor;
        switch(uriMatcher.match(uri)) {
            case COURSES: {
                cursor = helper.getReadableDatabase().query(
                        DBOpenHelper.TABLE_COURSES, DBOpenHelper.COURSES_COLUMNS, DBOpenHelper.COURSE_TERM_ID + " = ?", selectionArgs, null, null, DBOpenHelper.COURSE_START_DATE
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    public Cursor getSingleAssessment(Uri uri) {
        Cursor cursor;
        switch(uriMatcher.match(uri)) {
            case ASSESSMENTS_ID: {
                String _ID = uri.getLastPathSegment();
                String[] selectionArguments = new String[]{_ID};
                cursor = helper.getReadableDatabase().query(
                        DBOpenHelper.TABLE_ASSESSMENTS, DBOpenHelper.ASSESSMENTS_COLUMNS, DBOpenHelper.ASSESSMENT_ID + " = ?", selectionArguments, null, null, null
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    public Cursor getCourseAssessments(Uri uri, String[] selectionArgs) {
        Cursor cursor;
        switch(uriMatcher.match(uri)) {
            case ASSESSMENTS: {
                cursor = helper.getReadableDatabase().query(
                        DBOpenHelper.TABLE_ASSESSMENTS, DBOpenHelper.ASSESSMENTS_COLUMNS, DBOpenHelper.ASSESSMENT_COURSE_ID + " = ?", selectionArgs, null, null, DBOpenHelper.ASSESSMENT_DUE_DATE
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    public Cursor getSingleNote(Uri uri) {
        Cursor cursor;
        switch(uriMatcher.match(uri)) {
            case NOTES_ID: {
                String _ID = uri.getLastPathSegment();
                String[] selectionArguments = new String[]{_ID};
                cursor = helper.getReadableDatabase().query(
                        DBOpenHelper.TABLE_NOTES, DBOpenHelper.NOTES_COLUMNS, DBOpenHelper.NOTE_ID + " = ?", selectionArguments, null, null, null
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    public Cursor getCourseNotes(Uri uri, String[] selectionArgs) {
        Cursor cursor;
        switch(uriMatcher.match(uri)) {
            case NOTES: {
                cursor = helper.getReadableDatabase().query(
                        DBOpenHelper.TABLE_NOTES, DBOpenHelper.NOTES_COLUMNS, DBOpenHelper.NOTE_COURSE_ID + " = ?", selectionArgs, null, null, DBOpenHelper.NOTE_TITLE
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }
}
