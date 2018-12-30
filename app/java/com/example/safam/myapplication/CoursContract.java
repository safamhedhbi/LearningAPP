package com.example.safam.myapplication;


import android.provider.BaseColumns;

public class CoursContract {

    public CoursContract() {
    }

    public static class CoursTable implements BaseColumns {
        public static final String TABLE_NAME = "cours";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PATH ="path";
        public static final String COLUMN_NBR = "chapNbr";
    }
}
