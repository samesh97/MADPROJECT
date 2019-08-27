package com.mad.lk;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import android.widget.TableRow;


public class dbFilmDetails extends SQLiteOpenHelper {


        private static String DATABASE_NAME = "Users.db";

        //------------------------------------------------------------------
        private static String TABLE_NAME = "Film_Details";

        //-------------------------------------------------------------------
        public static final String COL_1 = "FilmID";
        public static final String COL_2 = "FilmName";
        public static final String COL_3 = "Role1";
        public static final String COL_4 = "Role2";
        public static final String COL_5 = "Role3";
        public static final String COL_6 = "Role4";
        public static final String COL_7 = "DirectorName";
        public static final String COL_8 = "Photo1";
        public static final String COL_9 = "Photo2";
        public static final String COL_10 = "Photo3";
        public static final String COL_11 = "Photo4";

        private  DatabaseHelper db;



    public dbFilmDetails(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "FilmID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FilmName VARCHAR(50),Role1 VARCHAR(50)," +
                " Role2 VARCHAR(50)," +
                " Role3 VARCHAR(50), " +
                " Role4 VARCHAR(50), " +
                " DirectorName VARCHAR(50)," +
                " Photo1 Image," +
                " Photo2 Image," +
                " Photo3 Image," +
                " Photo4 Image)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
//------------------------------------------------------------------------

    public void Add_FilmDetails(String filmName, String role1, String role2, String role3, String role4, String directorName) {

        ContentValues values = new ContentValues();

        values.put(COL_2, filmName);
        values.put(COL_3, role1);
        values.put(COL_4, role2);
        values.put(COL_5, role3);
        values.put(COL_6, role4);
        values.put(COL_7, directorName);
        values.put(COL_8, photo1);
        values.put(COL_9, Photo2);
        values.put(COL_10, Photo3);
        values.put(COL_10, Photo4);

        db.insert(TABLE_NAME,null,contentValues);

    }

    public Cursor getFilmInformation(SQLiteDatabase db){

        Cursor cursor;
        String[] projections = {COL_2,COL_3,COL_4,COL_5,COL_6,COL_7,COL_8,COL_9,COL_10,COL_11};
        cursor = db.query(TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }


    public Cursor getVideo(String filmName,SQLiteDatabase sqLiteDatabase){
        String[] projections = {COL_1, COL_2, COL_3, COL_4, COL_5, COL_6, COL_7, COL_8, COL_9, COL_10, COL_11};
        String selection = COL_2+" LIKE ?";
        String[] selection_args = {filmName};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }

    public int Update_FilmDetails(String oldfilmName,String newfilmName, String oldrole1, String newrole1,
                                   String oldrole2, String newrole2, String oldrole3, String newrole3,
                                   String oldrole4, String newrole4, String olddirectorName, String newdirectorName,SQLiteDatabase sqLiteDatabase) {
        ContentValues contentValues = new ContentValues();

        ContentValues.put(COL_2, newfilmName);
        ContentValues.put(COL_3, newrole1);
        ContentValues.put(COL_4, newrole2);
        ContentValues.put(COL_5, newrole3);
        ContentValues.put(COL_6, newrole4);
        ContentValues.put(COL_7, newdirectorName);


        String selection = COL_2+" LIKE ?";
        String[] selection_args = {oldfilmName};
        int count = sqLiteDatabase.update(TABLE_NAME,contentValues,selection,selection_args);

        return count;
    }
    public void Delete_FilmDetails(String filmname,SQLiteDatabase sqLiteDatabase) {
        String selection = COL_1 + " LIKE ?";
        String[] selection_args = {filmname};
        sqLiteDatabase.delete(TABLE_NAME, selection, selection_args);

    }

}


