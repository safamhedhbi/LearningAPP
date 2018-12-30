package com.example.safam.myapplication;

import android.provider.BaseColumns;

/**
 * Created by safam on 26/03/2018.
 */

public class UserContract {
    private UserContract() {
    }

    public static class UsersTable implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PASSWORD ="password";
        public static final String COLUMN_EMAIL ="email";
        public static final String COLUMN_SCORE = "score";
    }
}
