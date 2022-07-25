package com.example.accountinfodecrypt.source;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Nguyen Tuan Anh on 20/07/2022.
 * FPT Software
 * tuananhprogrammer@gmail.com
 */
public class AccountInfoData {
    public static final String AUTHORITY = "com.tuananh.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/accounts");
    public static final String DATABASE_NAME = "accounts.db";
    public static final int DATABASE_VERSION = 1;

    public class Account implements BaseColumns {
        private Account() {}
        public static final String TABLE_NAME = "account";
        public static final String _ID = "_id";
        public static final String _TITLE = "title";
        public static final String _CONTENT = "content";
        public static final String _PASSWORD = "password";
    }
}
