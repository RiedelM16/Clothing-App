package com.sp18.ssu370.baseprojectapp.Closet;

import java.util.ArrayList;

/**
 * Created by Matt Riedel on 3/17/2018.
 */

public class Wardrobe {

    private ArrayList<Outfit> outfits;
    private ArrayList<Article> wardrobe;

    Wardrobe() {
        outfits = new ArrayList<>();
        wardrobe = new ArrayList<>();
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


