package com.example.safam.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.safam.myapplication.UserContract.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by safam on 26/03/2018.
 */

public class LoginDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myusers.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public LoginDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_USERS_TABLE = "CREATE TABLE " +
                UsersTable.TABLE_NAME + " ( " +
                UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UsersTable.COLUMN_NAME + " TEXT, " +
                UsersTable.COLUMN_PASSWORD + " TEXT, " +
                UsersTable.COLUMN_EMAIL + " TEXT, " +
                UsersTable.COLUMN_SCORE + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_USERS_TABLE);
        fillUser();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UsersTable.TABLE_NAME);
        onCreate(db);

    }

    private void fillUser() {
        User user1 = new User("safa","123","123456",0);
        addUser(user1);

        User user2 = new User("insat","123" ,"123456789",0);
        addUser(user2);
    }




    private void addUser(User users) {
        ContentValues cv = new ContentValues();
        cv.put(UsersTable.COLUMN_NAME, users.getName());
        cv.put(UsersTable.COLUMN_PASSWORD, users.getPassword());
        cv.put(UsersTable.COLUMN_SCORE, users.getScore());
        cv.put(UsersTable.COLUMN_EMAIL, users.getEmail());
        db.insert(UsersTable.TABLE_NAME, null, cv);
    }



    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + UsersTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                User user = new User();
                user.setName(c.getString(c.getColumnIndex(UsersTable.COLUMN_NAME)));
                user.setPassword(c.getString(c.getColumnIndex(UsersTable.COLUMN_PASSWORD)));
                user.setScore(c.getInt(c.getColumnIndex(UsersTable.COLUMN_SCORE)));
                userList.add(user);
            } while (c.moveToNext());
        }

        c.close();
        return userList;
    }

    public int gettheScore(String nom) {

        User user = new User();
        db = getReadableDatabase();
        int i=-999999;
        Cursor c = db.rawQuery("SELECT * FROM " +UsersTable.TABLE_NAME+" WHERE name = '"+nom+"'", null);
        c.moveToFirst();

        user.setName(c.getString(c.getColumnIndex(UsersTable.COLUMN_NAME)));
        user.setPassword(c.getString(c.getColumnIndex(UsersTable.COLUMN_PASSWORD)));
        user.setScore(c.getInt(c.getColumnIndex(UsersTable.COLUMN_SCORE)));

        i= user.getScore();

        c.close();
        return i;

    }

    public void updatetheScore(String nom, int s){

        db.execSQL("UPDATE users SET score="+s+" WHERE name='"+nom+"' ");
    }


}
