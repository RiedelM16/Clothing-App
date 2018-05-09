package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sp18.ssu370.baseprojectapp.Closet.Outfit;
import com.sp18.ssu370.baseprojectapp.R;

import java.util.Random;

public class OutfitActivity extends MainActivity {

    private ImageView Botview;
    private ImageView Topview;


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
    public int findMin(int one, int two){
        if(one <= two )
            return one;
        else
            return two;
    }

    public boolean checkMatch(String article1, String article2){
        for (int i = 0; i < findMin(article1.length(), article2.length()); i++){
            if (article1.charAt(i) == '1' && article2.charAt(i) == '1'){
                return false;
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
        String tagsconlist = creatConString(tags, tagcount);
        String potentialTags;
        int i = 0;
        while (!matched && i < art.getCount()){
            art.moveToPosition(i);
            if (!art.getString(2).equals(articletomatch.getString(2))){
                db.moveToPosition(i);
                String contotest = art.getString(1);
                contotest = creatConString(contotest, tagcount);
                if (checkMatch(tags, contotest) && checkMatch(art.getString(1), tagsconlist)){
                    matched = true;
                }
                //matched = checkMatch(tags, contotest);
            }
            i++;
        }
        if (matched)
            return --i;
        return 0;
    }

    public void GenerateOutfit(){
        Cursor Articles = articleDB.getAllData();
        Cursor match = articleDB.getAllData();
        Random rand = new Random();
        int value = (int) System.currentTimeMillis ();
        Articles.moveToPosition(value % Articles.getCount());
        match.moveToPosition(findMatch(Articles));
        String pathBot = match.getString(3);
        String pathTop = Articles.getString(3);

        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeFile(pathTop);
        BitmapDrawable art = new BitmapDrawable(res, bitmap);
        bitmap = BitmapFactory.decodeFile(pathBot);
        BitmapDrawable sel  = new BitmapDrawable(res, bitmap);

        if(Articles.getString(2).equals("1")) {
            Topview.setImageDrawable(art);
            Botview.setImageDrawable(sel);
        }
        else{
            Topview.setImageDrawable(sel);
            Botview.setImageDrawable(art);
        }
        Topview.setRotation(90);
        Botview.setRotation(90);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_view);
        Botview = findViewById(R.id.cloth_bot);
        Topview = findViewById(R.id.cloth_top);
        GenerateOutfit();


        Button reroll = findViewById(R.id.reroll);
        reroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(OutfitActivity.this, OutfitActivity.class);
                //startActivity(intent);
                //n GenerateOutfit();
            }
        });

        /*
        Cursor Articles = articleDB.getAllData();
        Cursor match = articleDB.getAllData();
        Random rand = new Random();
        int value = rand.nextInt(Articles.getCount());
        Articles.moveToPosition(value);



        match.moveToPosition(findMatch(Articles));
        String pathBot = match.getString(3);
        String pathTop = Articles.getString(3);

        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeFile(pathTop);
        BitmapDrawable art = new BitmapDrawable(res, bitmap);
        bitmap = BitmapFactory.decodeFile(pathBot);
        BitmapDrawable sel  = new BitmapDrawable(res, bitmap);



        if(Articles.getString(2).equals("1")) {
            Topview.setImageDrawable(art);
            Botview.setImageDrawable(sel);
        }
        else{
            Topview.setImageDrawable(sel);
            Botview.setImageDrawable(art);
        }
        Topview.setRotation(90);
        Botview.setRotation(90);
        */



    }
}
