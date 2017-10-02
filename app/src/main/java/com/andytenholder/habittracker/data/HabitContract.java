package com.andytenholder.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Andy Tenholder on 6/6/2017.
 */

public class HabitContract {

    public static abstract class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "workout";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_MOOD = "mood";
        public static final String COLUMN_PUSH_UPS = "push_ups";
        public static final String COLUMN_SQUATS ="squats";
        public static final String COLUMN_CRUNCHS = "crunchs";

    }
}
