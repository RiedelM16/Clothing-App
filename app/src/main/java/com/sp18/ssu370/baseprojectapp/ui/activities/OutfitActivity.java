package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.database.Cursor;

public class OutfitActivity extends MainActivity {

    public String creatConString(String tags){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < tags.length();i++){
            if (tags.charAt(i) == '1') {
                //look up tag at id i and grab its con string
                // combine it with all other strings
            }
        }

        return buffer.toString();
    }

    public Cursor findMatch(Cursor articletomatch, Cursor articleData, Cursor tagData){
        Cursor match;
        boolean matched = false;
        String tags = articletomatch.getString(1);

        String potentialTags;
        while (!matched){
           potentialTags = articleData.getString(1);
        }
        match = articleDB.getAllData();
        return match;
    }
}
