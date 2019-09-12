package com.mad.lk.Film_Details_Maintain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.ColorSpace;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class db_film_details_maintain extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Tharasha.db";


    public  db_film_details_maintain(Context context){
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    //----------------------------------------Creating Table--------------------------------------
    public  void onCreate(SQLiteDatabase db){

        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + usersMaster.Users.TABLE_NAME + " ( " +
                        usersMaster.Users.COL_1 + " Text(50)PRIMARY KEY ," +
                        usersMaster.Users.COL_2 + " TEXT(50)," +
                        usersMaster.Users.COL_3 + " TEXT(50)," +
                        usersMaster.Users.COL_4 + " TEXT(50)," +
                        usersMaster.Users.COL_5 + " TEXT(50), " +
                        usersMaster.Users.COL_6 + " TEXT(50), " +
                        usersMaster.Users.COL_7 + " TEXT(50)," +
                        usersMaster.Users.COL_8 + " blob not null," +
                        usersMaster.Users.COL_9+ " blob not null)";


        db.execSQL(SQL_CREATE_ENTRIES);

    }



    //-------------------------------Upgrade method---------------------------------------------

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

       db.execSQL("DROP TABLE IF EXISTS "+ usersMaster.Users.TABLE_NAME);
       onCreate(db);
    }


//----------------------------------------------------Add method----------------------------------------------------------------------------------------------------------------------------
    public void Add(String Film_ID,String Film_Name, String Role1, String Role2, String Role3, String Role4, String Director_Name,byte[] Photo1,byte[] Photo2)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(usersMaster.Users.COL_1, Film_ID);
        values.put(usersMaster.Users.COL_2, Film_Name);
        values.put(usersMaster.Users.COL_3, Role1);
        values.put(usersMaster.Users.COL_4, Role2);
        values.put(usersMaster.Users.COL_5, Role3);
        values.put(usersMaster.Users.COL_6, Role4);
        values.put(usersMaster.Users.COL_7, Director_Name);
        values.put(usersMaster.Users.COL_8, Photo1);
        values.put(usersMaster.Users.COL_9, Photo2);

        long newRowId = db.insert(usersMaster.Users.TABLE_NAME, null, values);
    }
    //-------------------------------------------end adding method--------------------------------------------------------------------------------------------------------------------------------


//---------------------------------------------------Update method----------------------------------------------------------------------------------------------------------------------------------

    public void Update(String Film_ID,String Film_Name, String Role1, String Role2, String Role3, String Role4, String Director_Name) {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(usersMaster.Users.COL_1, Film_ID);
        values.put(usersMaster.Users.COL_2, Film_Name);
        values.put(usersMaster.Users.COL_3, Role1);
        values.put(usersMaster.Users.COL_4, Role2);
        values.put(usersMaster.Users.COL_5, Role3);
        values.put(usersMaster.Users.COL_6, Role4);
        values.put(usersMaster.Users.COL_7, Director_Name);


     //   String selection = usersMaster.Users.COL_2 + " LIKE ?";
       // String[] selectionArgs = {Film_Name};

        //int count = db.update(
          //      usersMaster.Users.TABLE_NAME,
            //    values,
              //  selection,
                //selectionArgs
        //);
        db.update(usersMaster.Users.TABLE_NAME, values, "Film_ID = ?", new String[] {Film_ID});

    }
    //-------------------------------end update method------------------------------------------------------------------------------------------------------------------------------------------



    //-------------------------------------------------Delete method-----------------------------------------------------------------------------------------------------------------------------
    public Integer Delete(String FilmID) {
        SQLiteDatabase db = getReadableDatabase();

        ///String selection = usersMaster.Users.COL_2 + " LIKE ?";
        //String[] selectionArgs = {Film_Name};

       // db.delete(usersMaster.Users.TABLE_NAME, selection, selectionArgs);

       // res = this.getWritableDatabase().delete("Film_Details_Maintain","Film_Name = '"+Film_Name+"'",null);

        //Toast.makeText(context, "User deleted!", Toast.LENGTH_SHORT).show();
        return db.delete(usersMaster.Users.TABLE_NAME,"Film_ID = ?", new String[] {FilmID});
    }
//------------------------------------------------end delete method--------------------------------------------------------------------------------------------------------------------------------

//--------------------------------Read all information--------------------------------------------------------------------------------------------------------------------------------------------
    public List ReadAllInformation() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                usersMaster.Users.COL_1,
        usersMaster.Users.COL_2,
        usersMaster.Users.COL_3,
        usersMaster.Users.COL_4,
        usersMaster.Users.COL_5,
        usersMaster.Users.COL_6,
        usersMaster.Users.COL_7,
        usersMaster.Users.COL_8,
        usersMaster.Users.COL_9,

        };

        String sortOrder = usersMaster.Users.COL_1 + " DESC";

        Cursor cursor = db.query(
              usersMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List Films_ID_ = new ArrayList();
        List Films_Name_ = new ArrayList();
        List Roles1_ = new ArrayList();
        List Roles2_ = new ArrayList();
        List Roles3_ = new ArrayList();
        List Roles4_ = new ArrayList();
        List Director_Name_ = new ArrayList();
        List Photo1_ = new ArrayList();
        List Photo2_ = new ArrayList();


        while (cursor.moveToNext()) {
            String Film_ID= cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_1));
            String Film_Name= cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_2));
            String Role1 = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_3));
            String Role2 = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_4));
            String Role3 = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_5));
            String Role4 = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_6));
            String Director_Name = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_7));
            String Photo1 = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_8));
            String Photo2 = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_9));

            Films_ID_.add(Film_ID);
            Films_Name_.add(Film_Name);
            Roles1_.add(Role1);
            Roles2_.add(Role2);
            Roles3_.add(Role3);
            Roles4_.add(Role4);
            Director_Name_.add(Director_Name);
            Photo1_.add(Photo1);
            Photo2_.add(Photo2);

        }

        cursor.close();

        return Films_ID_;
    }
//-------------------------------------------Read Information------------------------------------------------------------------------------------------------------------------

    public boolean readInfo(String Film_Name, String Role1, String Role2, String Role3, String Role4, String Director_Name,byte[] Photo1,byte[] Photo2)
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                usersMaster.Users.COL_2,
                usersMaster.Users.COL_3,
                usersMaster.Users.COL_4,
                usersMaster.Users.COL_5,
                usersMaster.Users.COL_6,
                usersMaster.Users.COL_7,
                usersMaster.Users.COL_8,
                usersMaster.Users.COL_9,

        };

        String selection = usersMaster.Users.COL_2 + " =? ";

        String[] selectionArgs = {Film_Name};

        String sortOrder = usersMaster.Users.COL_1 + " DESC";

        Cursor cursor = db.query(
                usersMaster.Users.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        while (cursor.moveToNext()) {
            String dbfilmid = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_1));
            String dbfilmname = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_2));
            String dbrole1 = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_3));
            String dbrole2 = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_4));
            String dbrole3 = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_5));
            String dbrole4 = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_6));
            String dbdirectorname = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_7));
            String dbphoto1 = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_8));
            String dbphoto2 = cursor.getString(cursor.getColumnIndexOrThrow(usersMaster.Users.COL_9));

        }

        return false;
    }


    //---------------------------------Get all data----------------------------------------------------------------------------------------------------------------------------------------------
    public Cursor getAllData(){

        ArrayList<ColorSpace.Model>alDetails=new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT COL_1,COL_2,COL_3,COL_4,COL_5,COL_6,COL_7 FROM " + usersMaster.Users.TABLE_NAME, null);
        res.moveToFirst();

        return  res;
    }

    public Cursor getFilmID(String Col,SQLiteDatabase sqLiteDatabase){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT COL_1 FROM Film_Details_Maintain" , null);

        return res;

    }
    }
