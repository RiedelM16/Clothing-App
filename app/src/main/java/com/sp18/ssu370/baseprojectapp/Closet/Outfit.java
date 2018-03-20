package com.sp18.ssu370.baseprojectapp.Closet;

import java.util.ArrayList;

/**
 * Created by Matt Riedel on 3/17/2018.
 */

public class Outfit {
    private Article top; // 0
    private Article bottom; // 1
    private Article jacket; // 2
    private Article shoe; // 3
    private ArrayList<Article> accessories; // 4

    Outfit() {
        this.top = new Article();
        this.bottom = new Article();
        this.jacket = new Article();
        this.shoe = new Article();
        this.accessories = new ArrayList<>();

    }



    public void addAccessorie(Article accessorie) {
        this.accessories.add(accessorie);
    }


}
