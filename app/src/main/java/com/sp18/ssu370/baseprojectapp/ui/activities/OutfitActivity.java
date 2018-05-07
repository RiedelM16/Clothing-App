package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.database.Cursor;

public class OutfitActivity extends MainActivity {

    public Cursor findMatch(Cursor articletomatch, Cursor articleData, Cursor tagData){
        Cursor match;
        boolean matched = false;
        String potentialTags;
        while (!matched){
           potentialTags = articleData.getString(1);
        }
        match = articleDB.getAllData();
        return match;
    }
}
