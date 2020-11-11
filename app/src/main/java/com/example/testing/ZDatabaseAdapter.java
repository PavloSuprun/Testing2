package com.example.testing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class ZDatabaseAdapter {

    private ZDatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public ZDatabaseAdapter(Context context){
        dbHelper = new ZDatabaseHelper(context.getApplicationContext());
    }

    public ZDatabaseAdapter open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {ZDatabaseHelper.COLUMN_ID, ZDatabaseHelper.COLUMN_WORD, ZDatabaseHelper.COLUMN_TRANS};
        return  database.query(ZDatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

    public List<ItemModel> getWords(){
        ArrayList<ItemModel> words = new ArrayList<>();
        Cursor cursor = getAllEntries();
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(ZDatabaseHelper.COLUMN_ID));
                String word = cursor.getString(cursor.getColumnIndex(ZDatabaseHelper.COLUMN_WORD));
                String trans = cursor.getString(cursor.getColumnIndex(ZDatabaseHelper.COLUMN_TRANS));
                words.add(new ItemModel(id, word, trans));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return  words;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, ZDatabaseHelper.TABLE);
    }

    public ItemModel getWord(long id){
        ItemModel sword = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",ZDatabaseHelper.TABLE, ZDatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            String word = cursor.getString(cursor.getColumnIndex(ZDatabaseHelper.COLUMN_WORD));
            String trans = cursor.getString(cursor.getColumnIndex(ZDatabaseHelper.COLUMN_TRANS));
            sword = new ItemModel(id, word, trans);
        }
        cursor.close();
        return sword;
    }

    public long insert(ItemModel word){

        ContentValues cv = new ContentValues();
        cv.put(ZDatabaseHelper.COLUMN_WORD, word.getWord());
        cv.put(ZDatabaseHelper.COLUMN_TRANS, word.getTranslation());

        return  database.insert(ZDatabaseHelper.TABLE, null, cv);
    }

    public long delete(long wordId){

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(wordId)};
        return database.delete(ZDatabaseHelper.TABLE, whereClause, whereArgs);
    }

    public long update(ItemModel word){

        String whereClause = ZDatabaseHelper.COLUMN_ID + "=" + String.valueOf(word.getId());
        ContentValues cv = new ContentValues();
        cv.put(ZDatabaseHelper.COLUMN_WORD, word.getWord());
        cv.put(ZDatabaseHelper.COLUMN_TRANS, word.getTranslation());
        return database.update(ZDatabaseHelper.TABLE, cv, whereClause, null);
    }
}
