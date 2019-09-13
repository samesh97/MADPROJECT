import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper_Lali extends SQLiteOpenHelper {

    public static final String DATABSE_NAME = "booking.db";
    public static final String TABLE_NAME = "booking";
    public static final String COL_1 = "booking_ID";
    public static final String COL_2 = "seats";
    public static final String COL_3 = "type";

    public DBHelper_Lali(Context context) {
        super(context, DATABSE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,seats int NOT NULL,apinner TEXT NOT NULL) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
