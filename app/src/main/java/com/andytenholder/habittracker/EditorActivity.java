package com.andytenholder.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.andytenholder.habittracker.data.HabitContract;
import com.andytenholder.habittracker.data.WorkoutDBHelper;

/**
 * Created by Andy Tenholder on 6/6/2017.
 */

public class EditorActivity extends AppCompatActivity {

    /** EditText field to enter Date */
    private EditText mDateEditText;

    /** EditText field to enter mood */
    private EditText mMoodEditText;

    /** EditText field to enter crunchs */
    private EditText mCrunchsEditText;

    /** EditText field to enter push ups */
    private EditText mPushUpsEditText;

    /** EditText field to enter squats */
    private EditText mSquatsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mDateEditText = (EditText) findViewById(R.id.edit_date);
        mMoodEditText = (EditText) findViewById(R.id.edit_mood);
        mPushUpsEditText = (EditText) findViewById(R.id.edit_push_ups);
        mCrunchsEditText = (EditText) findViewById(R.id.edit_crunchs);
        mSquatsEditText = (EditText) findViewById(R.id.edit_squats);

    }

    private void insertWorkout(){
        String dateString = mDateEditText.getText().toString().trim();
        String moodString = mMoodEditText.getText().toString().trim();
        String pushupsString = mPushUpsEditText.getText().toString().trim();
        int pushupsInt = Integer.parseInt(pushupsString);
        String crunchsString = mCrunchsEditText.getText().toString().trim();
        int crunchsInt = Integer.parseInt(crunchsString);
        String squatsString = mSquatsEditText.getText().toString().trim();
        int squatsInt = Integer.parseInt(squatsString);


        WorkoutDBHelper mDbHelper = new WorkoutDBHelper(this);

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_DATE, dateString);
        values.put(HabitContract.HabitEntry.COLUMN_MOOD, moodString);
        values.put(HabitContract.HabitEntry.COLUMN_PUSH_UPS, pushupsInt);
        values.put(HabitContract.HabitEntry.COLUMN_CRUNCHS, crunchsInt);
        values.put(HabitContract.HabitEntry.COLUMN_SQUATS, squatsInt);


// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);

        if(newRowId == -1){
            Toast.makeText(this, "Error with saving.",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Saved with row id " + newRowId,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                //save pet to DB
                insertWorkout();
                //Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
