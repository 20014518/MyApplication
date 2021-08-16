package sg.edu.rp.c346.id20014518.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "hobby.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_HOBBY = "Hobby";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createHobbyTableSql = "CREATE TABLE " + TABLE_HOBBY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createHobbyTableSql);
        Log.i("info", createHobbyTableSql + "\ncreated tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOBBY);
        onCreate(db);
    }

    public long insertHobby(String title, String description, int stars) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_STARS, stars);
        // Insert the row into the TABLE_HOBBY
        long result = db.insert(TABLE_HOBBY, null, values);
        // Close the database connection
        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }

    public ArrayList<Hobby> getAllHobbies() {
        ArrayList<Hobby> hobbieslist = new ArrayList<Hobby>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TITLE + "," + COLUMN_DESCRIPTION + ","
                + COLUMN_STARS + " FROM " + TABLE_HOBBY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                int stars = cursor.getInt(3);

                Hobby newHobby = new Hobby(id, title, description, stars);
                hobbieslist.add(newHobby);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return hobbieslist;
    }

    public ArrayList<Hobby> getAllHobbiesByStars(int starsFilter) {
        ArrayList<Hobby> hobbieslist = new ArrayList<Hobby>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_STARS};
        String condition = COLUMN_STARS + ">= ?";
        String[] args = {String.valueOf(starsFilter)};

        Cursor cursor;
        cursor = db.query(TABLE_HOBBY, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                int stars = cursor.getInt(3);

                Hobby newHobby = new Hobby(id, title, description, stars);
                hobbieslist.add(newHobby);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return hobbieslist;
    }



    public int updateHobby(Hobby data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_DESCRIPTION, data.getDescription());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_HOBBY, values, condition, args);
        db.close();
        return result;
    }


    public int deleteHobby(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_HOBBY, condition, args);
        db.close();
        return result;
    }

}

