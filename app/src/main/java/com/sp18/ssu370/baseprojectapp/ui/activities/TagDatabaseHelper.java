package com.sp18.ssu370.baseprojectapp.ui.activities;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class TagDatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Tags.db";
    public static final String TABLE_NAME = "Article";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Con";

    public TagDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase Clothing) {
        Clothing.execSQL("Create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Con TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Clothing, int i, int i1) {
        Clothing.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(Clothing);
    }

    public boolean InsertData(String name, String con) {
        SQLiteDatabase Clothing = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, con);
        long result = Clothing.insert(TABLE_NAME,null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String id, String tag, String location, String season){
        SQLiteDatabase Clothing = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, tag);
        contentValues.put(COL_3, location);
        Clothing.update(TABLE_NAME, contentValues, "ID = ?", new String[]{ id});
        return true;
    }


    public Cursor getAllData() {
        SQLiteDatabase Clothing = this.getWritableDatabase();
        Cursor results = Clothing.rawQuery("select * from "+TABLE_NAME,null);
        return results;
    }
}
