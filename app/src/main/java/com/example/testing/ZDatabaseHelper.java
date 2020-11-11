package com.example.testing;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;

public class ZDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "wordstore.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "words"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_TRANS = "trans";

    public ZDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_WORD
                + " TEXT, " + COLUMN_TRANS + " TEXT);");
        // добавление начальных данных
        /*db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_WORD
                + ", " + COLUMN_TRANS  + ") VALUES ('Word', 'Translation');");*/
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
