package com.mad.lk.Film_Details_Maintain;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.BaseColumns;

public class usersMaster {

    private usersMaster() {
    }

    protected static class Users implements BaseColumns {

        public static String TABLE_NAME = "Film_Details_Maintain";
        public static final String COL_1 = "Film_Id";
        public static final String COL_2 = "Film_Name";
        public static final String COL_3 = "Role1";
        public static final String COL_4 = "Role2";
        public static final String COL_5 = "Role3";
        public static final String COL_6 = "Role4";
        public static final String COL_7 = "Director_Name";
        public static final String COL_8 = "Photo1";
       public static final String COL_9 = "Photo2";




    }
}