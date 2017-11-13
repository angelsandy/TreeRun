package gogo.skyborn.com.gogo.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import gogo.skyborn.com.gogo.Models.GGUser;

/**
 * Created by Sandy on 11/12/2017.
 */

public class GGSqlInfo extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Gogo.db";
    public static final String TABLE_USER_NAME = "user";
    public static final String TABLE_BAG_NAME = "bag";
    public static final String TABLE_ROUTINE_NAME = "routine";
    public static final String TABLE_TIMEWAKE_NAME = "time";
    public static final String TABLE_USER_COLUMN_ID = "userId";
    public static final String TABLE_BAG_COLUMN_ID = "bagId";
    public static final String TABLE_ROUTINE_COLUMN_ID = "bagId";
    public static final String TABLE_TIMEWAKE_COLUMN_ID = "bagId";
    public static final String TABLE_USER_COLUMN_FIRSTNAME = "firstName";
    public static final String TABLE_TIMEWAKE_COLUMN_TIME = "time";
    public static final String TABLE_ROUTINE_COLUMN_ROUTINEITEM = "routineName";
    public static final String TABLE_BAG_COLUMN_ITEMNAME = "itemName";
    public static final String TABLE_USER_COLUMN_EMAIL = "email";
    public static final String TABLE_USER_COLUMN_PASSWORD = "pass";
    public static final String TABLE_USER_COLUMN_PICTUREWEB = "picture";

    public GGSqlInfo(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USER_NAME + " (" + TABLE_USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_USER_COLUMN_FIRSTNAME + " TEXT NOT NULL DEFAULT '', "
                    + TABLE_USER_COLUMN_EMAIL + " TEXT DEFAULT '', " + TABLE_USER_COLUMN_PICTUREWEB + " TEXT," + TABLE_USER_COLUMN_PASSWORD + " TEXT NOT NULL);");
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_BAG_NAME + " (" + TABLE_BAG_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_BAG_COLUMN_ITEMNAME + " TEXT NOT NULL);");
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TIMEWAKE_NAME + " (" + TABLE_TIMEWAKE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_TIMEWAKE_COLUMN_TIME + " TEXT NOT NULL);");
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_ROUTINE_NAME + " (" + TABLE_ROUTINE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_ROUTINE_COLUMN_ROUTINEITEM + " TEXT NOT NULL);");
        }
    }

    public void addUser(GGUser user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_USER_COLUMN_FIRSTNAME, user.getmName());
        values.put(TABLE_USER_COLUMN_EMAIL, user.getmEmail());
        values.put(TABLE_USER_COLUMN_PASSWORD, user.getmPassword());
        db.insert(TABLE_USER_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BAG_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMEWAKE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUTINE_NAME);
        onCreate(sqLiteDatabase);
    }
}
