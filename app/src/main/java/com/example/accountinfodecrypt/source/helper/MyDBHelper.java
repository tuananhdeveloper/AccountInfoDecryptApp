package com.example.accountinfodecrypt.source.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.example.accountinfodecrypt.source.AccountInfoData;
/**
 * Created by Nguyen Tuan Anh on 20/07/2022.
 * FPT Software
 * tuananhprogrammer@gmail.com
 */
public class MyDBHelper extends SQLiteOpenHelper {

    private static final String SQL_QUERY_CREATE = "CREATE TABLE "
            + AccountInfoData.Account.TABLE_NAME + " (" + AccountInfoData.Account._ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + AccountInfoData.Account._TITLE + " TEXT NOT NULL, "
            + AccountInfoData.Account._CONTENT + " TEXT NOT NULL, "
            + AccountInfoData.Account._PASSWORD + " TEXT NOT NULL"
            + ");";

    private static final String SQL_QUERY_DROP = "DROP TABLE IF EXISTS "
            + AccountInfoData.Account.TABLE_NAME + ";";

    public MyDBHelper(@Nullable Context context) {
        super(context, AccountInfoData.DATABASE_NAME, null, AccountInfoData.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_QUERY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_QUERY_DROP);
        onCreate(db);
    }
}
