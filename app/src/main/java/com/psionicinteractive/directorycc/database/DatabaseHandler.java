package com.psionicinteractive.directorycc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.psionicinteractive.directorycc.model.Push;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PSIONIC on 10/26/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "pushManager";

    // Contacts table name
    private static final String TABLE_PUSH = "pushNotifications";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_MESSAGE = "message";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DatabaseHandler(Context context) {
        super(context, "pushManager", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PUSH + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_MESSAGE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_PUSH);
        onCreate(sqLiteDatabase);
    }

    // Adding new push
    public void addPush(Push push) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, push.getTitle()); // Push Name
        values.put(KEY_MESSAGE, push.getMessage()); // Push Phone

        // Inserting Row
        db.insert(TABLE_PUSH, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Push getPush(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PUSH, new String[] { KEY_ID,
                        KEY_TITLE, KEY_MESSAGE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Push push = new Push(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return push
        return push;
    }

    // Getting All Contacts
    public List<Push> getAllPush() {
        List<Push> pushList = new ArrayList<Push>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PUSH;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Push push = new Push();
                push.setID(Integer.parseInt(cursor.getString(0)));
                push.setTitle(cursor.getString(1));
                push.setMessage(cursor.getString(2));
                // Adding push to list
                pushList.add(push);
            } while (cursor.moveToNext());
        }

        // return contact list
        return pushList;
    }

    // Updating single push
    public int updatePush(Push push) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, push.getTitle());
        values.put(KEY_MESSAGE, push.getMessage());

        // updating row
        return db.update(TABLE_PUSH, values, KEY_ID + " = ?",
                new String[] { String.valueOf(push.getID()) });
    }

    // Deleting single push
    public void deletePush(Push push) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PUSH, KEY_ID + " = ?",
                new String[] { String.valueOf(push.getID()) });
        db.close();
    }


    // Getting contacts Count
    public int getPushsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PUSH;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
