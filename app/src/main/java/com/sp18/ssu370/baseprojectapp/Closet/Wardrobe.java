package com.sp18.ssu370.baseprojectapp.Closet;

import java.util.ArrayList;

/**
 * Created by Matt Riedel on 3/17/2018.
 */

public class Wardrobe {

    private ArrayList<Outfit> outfits;
    private ArrayList<Article> wardrobe;
    private ArrayList<Tag> tags;


    Wardrobe() {
        outfits = new ArrayList<>();
        wardrobe = new ArrayList<>();
        tags = new ArrayList<>();
    }

    private Tag newtag(String name) {
        Tag newtag = new Tag();
        newtag.setName(name);
        for (int i = 0; i < tags.size(); i++) {
            if(tags.get(i) == PRO) {
                newtag.addPro(tags.get(i).getName());
                tags.get(i).addPro(name);
            }
            if (tags.get(i) == CON) {
                newtag.addCon(tags.get(i).getName());
                tags.get(i).addCon(name);
            }

        }
        tags.add(newtag);
        return newtag;
    }


    public void addArticle() {
        Article cloth = new Article();

        //user inout required
        cloth.setLocation(GetLocation());
        cloth.setImage(GetImage());


        //get tags
        while (true) {
            boolean done = false;
            String addtag;
            //get user string input
            addtag = GetTagName();
            boolean intags = false;
            for (int i = 0; i < tags.size(); i++) {
                if (addtag == tags.get(i).getName()) {
                    intags = true;
                    cloth.addTag(tags.get(i));
                }
            }
            if (!intags) {
                cloth.addTag(newtag(addtag));
            }

            if(done) {break;}

        }

        wardrobe.add(cloth);
    }

    public Outfit makeOutfit(Article article) {

        Outfit newOutfit = new Outfit();
        ArrayList<Tag> outfitTags;

        //find top

        //find bottom

        // find jacket

        // find shoes

        // add accessories


        return newOutfit;

    }
    public void addOutfit(Outfit outfit) {
        this.outfits.add(outfit);
    }

    public void addclothes(Article article) {
        this.wardrobe.add(article);
    }
}


