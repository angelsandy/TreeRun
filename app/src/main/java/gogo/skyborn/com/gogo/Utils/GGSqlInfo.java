package gogo.skyborn.com.gogo.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import gogo.skyborn.com.gogo.Models.GGOutfit;
import gogo.skyborn.com.gogo.Models.GGRoutine;
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
    public static final String TABLE_ROUTINE_COLUMN_BACKGROUND = "routineBackground";
    public static final String TABLE_ROUTINE_COLUMN_ICON = "routineIcon";
    public static final String TABLE_ROUTINE_COLUMN_NAMEROUTINE = "routineName";
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
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_BAG_NAME + " (" + TABLE_BAG_COLUMN_ID + " TEXT PRIMARY KEY, " + TABLE_BAG_COLUMN_ITEMNAME + " TEXT NOT NULL);");
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TIMEWAKE_NAME + " (" + TABLE_TIMEWAKE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_TIMEWAKE_COLUMN_TIME + " TEXT NOT NULL," + TABLE_USER_COLUMN_FIRSTNAME + "TEXT NOT NULL);");
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_ROUTINE_NAME + " (" + TABLE_ROUTINE_COLUMN_ID + " TEXT, " + TABLE_ROUTINE_COLUMN_NAMEROUTINE + " TEXT," + TABLE_ROUTINE_COLUMN_ICON + " TEXT );");
        }
    }

    public void addOutfit(GGOutfit outfit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_BAG_COLUMN_ID, outfit.getmId());
        values.put(TABLE_BAG_COLUMN_ITEMNAME, outfit.getmImage());
        db.insert(TABLE_BAG_NAME, null, values);
        db.close();
    }


    public void addTime(String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_TIMEWAKE_COLUMN_TIME, time);
        db.insert(TABLE_TIMEWAKE_NAME, null, values);
        db.close();
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

    public void addRoutine(GGRoutine routine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_ROUTINE_COLUMN_ID, routine.getmId());
        values.put(TABLE_ROUTINE_COLUMN_NAMEROUTINE, routine.getmRoutineName());
        values.put(TABLE_ROUTINE_COLUMN_ICON, String.valueOf(routine.getmIconType()));
        db.insert(TABLE_ROUTINE_NAME, null, values);
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_ROUTINE_NAME + "", null);
        if (c.moveToFirst()) {
            Log.e("Imprimir--->", c.getString(0));
        }
        db.close();
    }

    public boolean deleteRoutine(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean delete;
        delete = db.delete(TABLE_ROUTINE_NAME, TABLE_ROUTINE_COLUMN_ID + "=?", new String[]{id}) > 0;
        db.close();
        return delete;
    }

    public boolean checkPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT " + TABLE_USER_COLUMN_PASSWORD + " FROM " + TABLE_USER_NAME + " WHERE " + TABLE_USER_COLUMN_EMAIL + "='" + email + "'";
        String pass = null;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            pass = c.getString(0);
        }
        c.close();
        db.close();
        if (pass != null && pass.equals(password)) {
            return true;
        }
        return false;
    }

    public GGUser findUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT " + TABLE_USER_COLUMN_FIRSTNAME + ", " + TABLE_USER_COLUMN_EMAIL + ", " + TABLE_USER_COLUMN_PICTUREWEB + " FROM " + TABLE_USER_NAME + " WHERE " + TABLE_USER_COLUMN_EMAIL + "='" + email + "'";
        GGUser user = new GGUser();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            user.setmName(c.getString(0));
            user.setmEmail(c.getString(1));
            user.setmPhoto(c.getString(2));
        }
        c.close();
        db.close();
        return user;
    }

    public String findTime() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT " + TABLE_TIMEWAKE_COLUMN_TIME + " FROM " + TABLE_TIMEWAKE_NAME;
        Cursor c = db.rawQuery(selectQuery, null);
        String time = null;
        if (c.moveToFirst()) {
            time = c.getString(0);
        }
        c.close();
        db.close();
        return time;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BAG_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMEWAKE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUTINE_NAME);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<String> findRoutine() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        String selectQuery = "SELECT " + TABLE_ROUTINE_COLUMN_NAMEROUTINE + " FROM " + TABLE_ROUTINE_NAME;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                arrayList.add(c.getString(0));

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return arrayList;
    }
}
