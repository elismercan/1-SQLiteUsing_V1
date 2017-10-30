package com.example.java_oglen.a1_sqliteusing;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    private static String dbName="proje.db";
    private static   int dbVersion=1;

    public DB(Context context) {
        super(context,dbName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="CREATE TABLE IF NOT EXISTS `kisiler` (\n" +
                "\t`kid`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`adi`\tTEXT,\n" +
                "\t`soyadi`\tTEXT,\n" +
                "\t`mail`\tTEXT,\n" +
                "\t`telefon`\tTEXT\n" +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS kisiler");
        onCreate(db);
    }
}

