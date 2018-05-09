package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.sp18.ssu370.baseprojectapp.R;

public class OutfitActivity extends MainActivity {

    public String convertToString(boolean[] bool){
        StringBuffer con = new StringBuffer();
        for (int i = 0; i < bool.length; i++){
            if (bool[i]){
                con.append("1");
            }
            else{
                con.append("0");
            }
        }
        return con.toString();
    }
    public int findMax(int one, int two){
        if(one >= two )
            return one;
        else
            return two;
    }

    public boolean checkMatch(String article1, String article2){
        for (int i = 0; i < findMax(article1.length(), article2.length()); i++){
            if (article1.charAt(i) == '1' || article2.charAt(i) == '1'){
                if (article1.charAt(i) == '0' || article2.charAt(i) == '0'){
                    return false;
                }
            }
        }
        return true;
    }

    public String creatConString(String tags, int count){
        boolean[] bool = new boolean[count];
        Cursor TAG = tagDB.getAllData();
        for (int i = 0; i < tags.length();i++){
            if (tags.charAt(i) == '1') {
                TAG.moveToPosition(i);
                //look up tag at id i and grab its con string
                //Cursor res = tagDB.getRowbyID(Integer.toString(i+1));
                String test = TAG.getString(2);
                for (int j = 0; j < test.length();j++){
                    if (test.charAt(j) == '1'){
                        bool[j] = true;
                    }
                }
            }
        }
        return convertToString(bool);
    }

    public int findMatch(Cursor articletomatch){
        Cursor db = tagDB.getAllData();
        Cursor art = articleDB.getAllData();
        int tagcount = db.getCount();
        boolean matched = false;
        String tags = articletomatch.getString(1);
        tags = creatConString(tags, tagcount);
        String potentialTags;
        int i = 0;
        while (!matched && i < tagcount){
            art.moveToPosition(i);
            if (!art.getString(2).equals(articletomatch.getString(2))){
                db.moveToPosition(i);
                String contotest = art.getString(1);
                contotest = creatConString(contotest, tagcount);
                String temp1 = articletomatch.getString(2);
                //potentialTags = db.getString(1);
                //potentialTags = creatConString(potentialTags, tagcount);
                matched = checkMatch(tags, contotest);
            }
            i++;
        }
        if (matched)
            return --i;
        return -1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_view);
        Cursor Articles = articleDB.getAllData();
        Articles.moveToPosition(0);
        Cursor match = articleDB.getAllData();
        match.moveToPosition(findMatch(Articles));
        String pathBot = match.getString(3);
        String pathTop = Articles.getString(3);

        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeFile(pathTop);
        BitmapDrawable bd = new BitmapDrawable(res, bitmap);
        ImageView Topview = findViewById(R.id.cloth_top);
        Topview.setImageDrawable(bd);
        Topview.setRotation(90);

        bitmap = BitmapFactory.decodeFile(pathBot);
        bd = new BitmapDrawable(res, bitmap);
        ImageView Botview = findViewById(R.id.cloth_bot);
        Botview.setImageDrawable(bd);
        Botview.setRotation(90);



    }
}
