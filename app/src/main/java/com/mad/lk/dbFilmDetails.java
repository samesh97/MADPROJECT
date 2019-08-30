package com.mad.lk;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import android.widget.TableRow;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;


public class dbFilmDetails extends SQLiteOpenHelper {


        private static String DATABASE_NAME = "Tharasha.db";

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
        db.execSQL("CREATE TABLE TABLE_NAME (" +
                "FilmID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " FilmName TEXT(50)," +
                " Role1 TEXT(50)," +
                " Role2 TEXT(50)," +
                " Role3 TEXT(50), " +
                " Role4 TEXT(50), " +
                " DirectorName TEXT(50)," +
                " Photo1 blob not null," +
                " Photo2 blob not null," +
                " Photo3 blob not null," +
                " Photo4 blob not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
//------------------------------------------------------------------------

    public void Add_FilmDetails(String filmName, String role1, String role2, String role3, String role4, String directorName,String photo1, String photo2, String photo3, String photo4) {


        //try{

            //FileInputStream fs = new FileInputStream(x);
            //byte[] imgbyte = new byte[fs.available()] ;
            //fs.read(imgbyte);

            ContentValues values = new ContentValues();

            values.put(COL_2, filmName);
            values.put(COL_3, role1);
            values.put(COL_4, role2);
            values.put(COL_5, role3);
            values.put(COL_6, role4);
            values.put(COL_7, directorName);
            values.put(COL_8, photo1);
            values.put(COL_9, photo2);
            values.put(COL_10, photo3);
            values.put(COL_11, photo4);

            this.getWritableDatabase().insertOrThrow("Film_Details","",values);

           // db.Insert(TABLE_NAME,null,values);
          //  fs.close();
           // return true;
        //}
        //catch(IOException e){
          //  e.printStackTrace();
            //return false;
        //}
    }

    public void Update_FilmDetails(String oldfilmName,String newfilmName, String oldrole1, String newrole1,
                                   String oldrole2, String newrole2, String oldrole3, String newrole3,
                                   String oldrole4, String newrole4, String olddirectorName, String newdirectorName,
                                   String newimage1, String oldimage1,String newimage2, String oldimage2,
                                   String newimage3, String oldimage3, String newimage4, String oldimage4,
                                   SQLiteDatabase sqLiteDatabase) {
        //ContentValues contentValues = new ContentValues();

        //ContentValues.put(COL_2, newfilmName);
        //ContentValues.put(COL_3, newrole1);
        //ContentValues.put(COL_4, newrole2);
        //ContentValues.put(COL_5, newrole3);
        //ContentValues.put(COL_6, newrole4);
        //ContentValues.put(COL_7, newdirectorName);


        //  String selection = COL_2+" LIKE ?";
        //String[] selection_args = {oldfilmName};
        //int count = sqLiteDatabase.update(TABLE_NAME,contentValues,selection,selection_args);

        this.getWritableDatabase().execSQL("UPDATE Film_Details SET FilmName ='"+ newfilmName +"' WHERE FilmName = '"+ oldfilmName +"'");

    }


    public void Delete_FilmDetails(String filmName) {
        //  String selection = COL_1+ " LIKE ?";
        //String[] selection_args = {filmid};
        //sqLiteDatabase.delete(TABLE_NAME, selection, selection_args);

        this.getWritableDatabase().delete("Film_Details", "FilmName='"+ filmName + "'", null);

    }
    public void getFilmInformation(TextView textView){

        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM Film_Details",null);
       textView.setText("");
       while (cursor.moveToNext()){

       }
    }



}


