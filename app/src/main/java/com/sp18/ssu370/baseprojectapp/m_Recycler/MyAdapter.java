package com.sp18.ssu370.baseprojectapp.m_Recycler;

/**
 * Created by Gabri on 4/30/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.sp18.ssu370.baseprojectapp.R;

import com.sp18.ssu370.baseprojectapp.loadImg;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context c;
    ArrayList<loadImg> loadingImgs;

    public MyAdapter(Context c, ArrayList<loadImg> loadingImgs) {
        this.c = c;
        this.loadingImgs = loadingImgs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        loadImg s=loadingImgs.get(position);

        holder.nameTxt.setText(s.getName());
        Picasso.with(c).load(s.getUri()).placeholder(R.drawable.placeholder).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return loadingImgs.size();
    }
}

