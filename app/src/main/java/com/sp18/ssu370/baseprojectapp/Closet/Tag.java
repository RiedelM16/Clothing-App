package com.sp18.ssu370.baseprojectapp.Closet;

import java.util.ArrayList;

/**
 * Created by Matt Riedel on 3/17/2018.
 */

public class Tag {

    private String name;
    private ArrayList<String> pro;
    private ArrayList<String> con;

    Tag(String name, ArrayList<String> prolist, ArrayList<String> conlist){
        this.name = name;
        this.pro = prolist;
        this.con = conlist;
        //add pros and cons to tag on create
    }

    public ArrayList<String> getPro() {
        return pro;
    }

    public ArrayList<String> getCon() {
        return con;
    }



    public String getName() {
        return name;
    }

    public void addPro(String tag) {
        this.pro.add(tag);
    }

    public void addCon(String tag) {
        this.con.add(tag);
    }


}
