package com.mad.lk;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import java.util.List;


public class dbFilmDetails extends SQLiteOpenHelper {


        private static String DATABASE_NAME = "Users.db";
        private static String TABLE_NAME = "Film_Details";
        public static final String COL_1 = "FilmID";
        public static final String COL_2 = "FilmName";
        public static final String COL_3 = "Role1";
        public static final String COL_4 = "Role2";
        public static final String COL_5 = "Role3";
        public static final String COL_6 = "Role4";
        public static final String COL_7 = "DirectorName";


    public dbFilmDetails(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

        db.execSQL("CREATE TABLE Film_Details (FilmID INTEGER PRIMARY KEY AUTOINCREMENT, FilmName VARCHAR(50),Role1 VARCHAR(50), Role2 VARCHAR(50),  " +
                "Role3 VARCHAR(50),  Role4 VARCHAR(50),  DirectorName VARCHAR(50))");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS FILMDETAILS");
        onCreate(db);
    }


    public boolean Add_FilmDetails(String FilmID, String FilmName, String Role1, String Role2, String Role3, String Role4, String DirectorName)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_1, FilmID);
        values.put(COL_2, FilmName);
        values.put(COL_3, Role1);
        values.put(COL_4, Role2);
        values.put(COL_5, Role3);
        values.put(COL_6, Role4);
        values.put(COL_7, DirectorName);

        Long newRowId = db.insert(TABLE_NAME,null,values);
        db.insert(TABLE_NAME, null, values);
        db.close();

        if(newRowId == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }




    public boolean Update_FilmDetails(String id,String username,String password,String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", username);
        contentValues.put("EMAIL", email);
        contentValues.put("PASSWORD", password);


        if(db.update(TABLE_NAME, contentValues, "ID=?", new String[] {id}) == 0)
        {
            return false;
        }
        else
        {
            return true;
        }


    }
    public boolean Delete_FilmDetails(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int val = db.delete(TABLE_NAME, "ID =?", new String[]{id});

        if(val > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor getAllData_FilmDetails()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return cursor;
    }

    public boolean IsExist_FilmName_FilmDetails(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE FilmName = '" + name + "'",null);
        if(cursor.getCount() == 0)
        {
            cursor.close();
            return false;
        }
        else
        {
            cursor.close();
            return true;
        }

    }

    public List void readAllInfo()
    {

        SQLiteDatabase db = this.getWritableDatabase();

         String[] projection = new String[]{
                 dbFilmDetails.COL_1,
                 dbFilmDetails.COL_2,
                 dbFilmDetails.COL_3,
                 dbFilmDetails.COL_4,
                 dbFilmDetails.COL_5,
                 dbFilmDetails.COL_6,
                 dbFilmDetails.COL_7
         };
        String sortOrder = dbFilmDetails.COL_1+ "FilmID";

return ;

    }
}


