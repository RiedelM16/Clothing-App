package com.sp18.ssu370.baseprojectapp.m_Recycler;

/**
 * Created by Gabri on 4/30/2018.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sp18.ssu370.baseprojectapp.R;


public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;
    ImageView img;

    public MyViewHolder(View itemView) {
        super(itemView);

        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        img= (ImageView) itemView.findViewById(R.id.loadImg);

    }
}

