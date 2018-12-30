package com.example.safam.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.safam.myapplication.CoursContract.*;

public class CourDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mycourss.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public CourDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_USERS_TABLE = "CREATE TABLE " +
                CoursTable.TABLE_NAME + " ( " +
                CoursTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CoursTable.COLUMN_NAME + " TEXT, " +
                CoursTable.COLUMN_PATH + " TEXT, " +
                CoursTable.COLUMN_NBR + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_USERS_TABLE);
        fillcours();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CoursTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillcours() {
        Cour c1 = new Cour("Chapitre1","ch1.pdf",1);
        addc(c1);

        Cour c2 = new Cour("Chapitre1","ch2.pdf" ,2);
        addc(c2);
    }
    private void addc(Cour cours) {
        ContentValues cv = new ContentValues();
        cv.put(CoursTable.COLUMN_NAME, cours.getName());
        cv.put(CoursTable.COLUMN_PATH, cours.getPath());
        cv.put(CoursTable.COLUMN_NBR, cours.getChap());
        db.insert(CoursTable.TABLE_NAME, null, cv);
    }

    public String getthePath(int i) {

        Cour cours = new Cour();
        db = getReadableDatabase();
        String s="abcdef";
        Cursor c = db.rawQuery("SELECT * FROM " +CoursTable.TABLE_NAME+" WHERE chap = "+i+"", null);
        c.moveToFirst();

        cours.setName(c.getString(c.getColumnIndex(CoursTable.COLUMN_NAME)));
        cours.setPath(c.getString(c.getColumnIndex(CoursTable.COLUMN_PATH)));
        cours.setChap(c.getInt(c.getColumnIndex(CoursTable.COLUMN_NBR)));

        s= cours.getPath();

        c.close();
        return s;

    }

}
