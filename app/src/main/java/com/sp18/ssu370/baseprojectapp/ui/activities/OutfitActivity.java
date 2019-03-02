package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sp18.ssu370.baseprojectapp.Closet.Outfit;
import com.sp18.ssu370.baseprojectapp.R;

import java.io.File;
import java.util.Random;

import static java.lang.Math.abs;

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
        TAG.close();
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

        db.close();
        art.close();
        if (matched)
            return --i;
        return 0;
    }

    public int getScore(String tags1, String tags2){
        int score = 0;
        Cursor TAG = tagDB.getAllData();
        for (int i = 0; i < tags1.length(); i++){
            if (tags1.charAt(i) =='1'){
                TAG.moveToPosition(i);
                String test = TAG.getString(2);
                for (int j = 0; j < test.length();j++) {
                    if (test.charAt(j) == '2') {
                        if (tags2.length() >= j && tags2.charAt(j) == '1')
                            score += 1;
                    }
                }
            }
        }
        TAG.close();
        return score;
    }

    public int findBestMatch(Cursor ArticleToMatch){
        Cursor db = tagDB.getAllData();
        Cursor art = articleDB.getAllData();

        int[] score = new int[art.getCount()];
        String tags = ArticleToMatch.getString(1);
        String tagsconlist = creatConString(tags, db.getCount());
        for (int i = 0; i < art.getCount(); i++){
            art.moveToPosition(i);
            if (!art.getString(2).equals(ArticleToMatch.getString(2))){
                db.moveToPosition(i);
                String contotest = art.getString(1);
                contotest = creatConString(contotest, db.getCount());
                if (checkMatch(tags, contotest) && checkMatch(art.getString(1), tagsconlist)){
                    score[i] = getScore(ArticleToMatch.getString(1), art.getString(1));
                }
                //matched = checkMatch(tags, contotest);
            }

        }

        int max = -1;
        int maxindex = 0;
        for (int i = 0; i < score.length; i++){
            if (score[i] > max){
                max = score[i];
                maxindex = i;
            }
        }
        db.close();
        art.close();
        return maxindex;
    }

    public void GenerateOutfit(int type){
        Cursor Articles = articleDB.getAllData();
        Cursor match = articleDB.getAllData();
        Random rand = new Random();
        int value = (int) System.currentTimeMillis ();
        Articles.moveToPosition(abs(value % Articles.getCount()));
        if (type == 1)
            match.moveToPosition(findMatch(Articles));
        if (type == 2)
            match.moveToPosition(findBestMatch(Articles));
        String pathBot = match.getString(3);
        String pathTop = Articles.getString(3);

        /*
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeFile(pathTop);
        BitmapDrawable art = new BitmapDrawable(res, bitmap);
        bitmap = BitmapFactory.decodeFile(pathBot);
        BitmapDrawable sel  = new BitmapDrawable(res, bitmap);
        */

        if(Articles.getString(2).equals("1")) {
            File file = new File(pathTop);
            Glide.with(this).load(Uri.fromFile(file)).into(Topview);
            file = new File(pathBot);
            Glide.with(this).load(Uri.fromFile(file)).into(Botview);
            //Topview.setImageDrawable(art);
            //Botview.setImageDrawable(sel);
        }
        else{
            File file = new File(pathBot);
            Glide.with(this).load(Uri.fromFile(file)).into(Topview);
            file = new File(pathTop);
            Glide.with(this).load(Uri.fromFile(file)).into(Botview);

            //Topview.setImageDrawable(sel);
            //Botview.setImageDrawable(art);
        }
        Articles.close();
        match.close();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_view);
        Botview = findViewById(R.id.cloth_bot);
        Topview = findViewById(R.id.cloth_top);
        Topview.setRotation(90);
        Botview.setRotation(90);
        GenerateOutfit(1);


        Button bestmatch = findViewById(R.id.bestmatch);
        bestmatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenerateOutfit(2);
            }
        });
        Button reroll = findViewById(R.id.reroll);
        reroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(OutfitActivity.this, OutfitActivity.class);
                //startActivity(intent);
                GenerateOutfit(1);
            }
        });





    }
}
