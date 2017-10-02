package com.andytenholder.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andy Tenholder on 6/6/2017.
 */

public class WorkoutDBHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME+ " ("+
                    HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    HabitContract.HabitEntry.COLUMN_DATE + " TEXT NOT NULL,"+
                    HabitContract.HabitEntry.COLUMN_MOOD + " TEXT NOT NULL,"+
                    HabitContract.HabitEntry.COLUMN_CRUNCHS + " INTEGER NOT NULL DEFAULT 0,"+
                    HabitContract.HabitEntry.COLUMN_PUSH_UPS + " INTEGER NOT NULL DEFAULT 0,"+
                    HabitContract.HabitEntry.COLUMN_SQUATS + " INTEGER NOT NULL DEFAULT 0)";

    private static final String SQL_DETELE_ENTRIES = "DROP TABLE IF EXISTS " + HabitContract.HabitEntry.TABLE_NAME;


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "workout.db";

    public WorkoutDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DETELE_ENTRIES);
        onCreate(db);
    }
}
