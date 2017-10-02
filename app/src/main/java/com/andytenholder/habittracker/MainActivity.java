package com.andytenholder.habittracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.andytenholder.habittracker.data.HabitContract;
import com.andytenholder.habittracker.data.WorkoutDBHelper;

public class MainActivity extends AppCompatActivity {

    WorkoutDBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new WorkoutDBHelper(this);

        Cursor cursor = readDatabaseInfo();
        displayDatabaseInfo(cursor);
    }

    @Override
    public void onStart(){
        super.onStart();
        Cursor cursor = readDatabaseInfo();
        displayDatabaseInfo(cursor);
    }

    private Cursor readDatabaseInfo() {


        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_DATE,
                HabitContract.HabitEntry.COLUMN_MOOD,
                HabitContract.HabitEntry.COLUMN_CRUNCHS,
                HabitContract.HabitEntry.COLUMN_PUSH_UPS,
                HabitContract.HabitEntry.COLUMN_SQUATS
        };

        String selection = null;
        String[] selectionArgs = null;

        Cursor cursor = db.query(HabitContract.HabitEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        return cursor;
    }





    private void displayDatabaseInfo(Cursor cursor){

        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view_workout);
            displayView.setText("Number of rows in database table: " + cursor.getCount());

            int idComlumIndex = cursor.getColumnIndex(HabitContract.HabitEntry._ID);
            int dateComlumIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_DATE);
            int moodComlumIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_MOOD);
            int crunchsComlumIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_CRUNCHS);
            int pushupsComlumIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_PUSH_UPS);
            int squatsComlumIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_SQUATS);

            while (cursor.moveToNext()){

                int currentID = cursor.getInt(idComlumIndex);
                String currentDate = cursor.getString(dateComlumIndex);
                String currentMood = cursor.getString(moodComlumIndex);
                int currentCrunchs = cursor.getInt(crunchsComlumIndex);
                int currentPushUps = cursor.getInt(pushupsComlumIndex);
                int currentSquats = cursor.getInt(squatsComlumIndex);

                displayView.append(
                        "\n" + currentID + " - "
                                + currentDate + " - "
                                + currentMood + " - "
                                + currentCrunchs + " - "
                                + currentPushUps + " - "
                                + currentSquats);

            }


        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
