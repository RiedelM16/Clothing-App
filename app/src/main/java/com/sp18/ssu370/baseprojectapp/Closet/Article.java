package com.sp18.ssu370.baseprojectapp.Closet;

import android.media.Image;

import java.util.ArrayList;

/**
 * Created by Matt Riedel on 3/17/2018.
 */

public class Article {

    private ArrayList<Tag> tags;
    private int location; //upper, lower, jump suit
    private Image image;

    Article() {
        this.image = null;
        this.tags = new ArrayList<>();
        this.location = -1;
    }

    Article(Image image, ArrayList<Tag> tags, int location) {
        this.image = image;
        this.tags = tags;
        this.location = location;

    }

    public void setLocation(int location) {
        this.location = location;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }





}
