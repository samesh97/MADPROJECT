package com.mad.lk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;

import java.util.ArrayList;
import java.util.List;

import Favorites.FavoriteContract;

import static com.mad.lk.DatabaseHelper.DATABSE_NAME;

public class FavoriteDatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Favorite.db";

    public static final String TABLE_NAME = "Favorite_Details";

    public static final String FAVORITE_COL_1 = "NAME";
    public static final String FAVORITE_COL_2 = "IMAGE";
    public static final String FAVORITE_COL_3 = "DESCRIPTION";
    public static final String FAVORITE_COL_4 = "RANKINGS";
    public static final String FAVORITE_COL_5 = "DATE";
    public static final String FAVORITE_COL_6 = "TIME";
    public static final String FAVORITE_COL_7 = "SEATS";
    public static final String FAVORITE_COL_8 = "NOTES";

    public FavoriteDatabaseHelper(Context context) {
        super(context, DATABSE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IMAGE BLOB NOT NULL," +
                "NAME TEXT NOT NULL," +
                "DESCRIPTION TEXT NOT NULL," +
                "RANKINGS TEXT NOT NULL," +
                "DATE TEXT NOT NULL," +
                "TIME TEXT NOT NULL," +
                "SEATS INT NOT NULL," +
                "NOTES TEXT NOT NULL) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public Cursor getAllFavorites() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    public boolean addfavorites(String fname, String fdes, String frankings, String fdate, String ftime, int fseats, byte[] image, String fnotes) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("NAME", fname);
        contentValues.put("DESCRIPTION", fdes);
        contentValues.put("IMAGE", image);
        contentValues.put("RANKINGS", frankings);
        contentValues.put("DATE", fdate);
        contentValues.put("TIME", ftime);
        contentValues.put("SEATS", fseats);
        contentValues.put("NOTES", fnotes);

        Long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public void deleteFavorites(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID =?", new String[]{id});


    }

    public Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public List<classFavorites> getAllFavorite() {
        String[] column1 = {FAVORITE_COL_1, FAVORITE_COL_2, FAVORITE_COL_3, FAVORITE_COL_4, FAVORITE_COL_5, FAVORITE_COL_6, FAVORITE_COL_7, FAVORITE_COL_8};

        String sortOrder = FAVORITE_COL_1 + "ASC";
        List<classFavorites> favoritelist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,column1,null,null,null,null,sortOrder);

        if(cursor.moveToFirst()){
            do{
                classFavorites classf = new classFavorites();
                classf.setNAME(cursor.getString(cursor.getColumnIndex(FAVORITE_COL_1)));
                classf.setDATE(cursor.getString(cursor.getColumnIndex(FAVORITE_COL_5)));
                classf.setDESCRIPTION(cursor.getString(cursor.getColumnIndex(FAVORITE_COL_3)));
                classf.setTIME(cursor.getString(cursor.getColumnIndex(FAVORITE_COL_6)));
                classf.setIMAGE(cursor.getString(cursor.getColumnIndex(FAVORITE_COL_2)));
                classf.setRANKINGS(cursor.getString(cursor.getColumnIndex(FAVORITE_COL_4)));
                classf.setNOTES(cursor.getString(cursor.getColumnIndex(FAVORITE_COL_8)));
                classf.setSEATS(cursor.getString(cursor.getColumnIndex(FAVORITE_COL_7)));

            favoritelist.add(classf);
            }
            while (cursor.moveToNext());
    }
        cursor.close();
        db.close();

        return  favoritelist;
}


}
