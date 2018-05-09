package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Clothing.db";
    public static final String TABLE_NAME = "Article";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Tags";
    public static final String COL_3 = "Location";
    public static final String COL_4 = "Season";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase Clothing) {
        Clothing.execSQL("Create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Tags TEXT,Location TEXT,Season TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Clothing, int i, int i1) {
        Clothing.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(Clothing);
    }

    public boolean InsertData(String tag, String location, String season) {
        SQLiteDatabase Clothing = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, tag);
        contentValues.put(COL_3, location);
        contentValues.put(COL_4, season);
        long result = Clothing.insert(TABLE_NAME,null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public int deleteAll(){
        SQLiteDatabase Clothing = this.getWritableDatabase();
        return Clothing.delete(TABLE_NAME,"1",null);
    }

    public Cursor getRowbyID(String id){
        SQLiteDatabase Clothing = this.getWritableDatabase();
        String search = "select * from "+TABLE_NAME+" where ID = ?";
        Cursor res = Clothing.rawQuery("select * from "+TABLE_NAME+" where ID = ?", new String[] {id});
        return res;
    }

    public boolean updateData(String id, String tag, String location, String season){
        SQLiteDatabase Clothing = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, tag);
        contentValues.put(COL_3, location);
        contentValues.put(COL_4, season);
        Clothing.update(TABLE_NAME, contentValues, "ID = ?", new String[]{ id});
        return true;
    }


    public Cursor getAllData() {
        SQLiteDatabase Clothing = this.getWritableDatabase();
        Cursor results = Clothing.rawQuery("select * from "+TABLE_NAME,null);
        return results;
    }
}
