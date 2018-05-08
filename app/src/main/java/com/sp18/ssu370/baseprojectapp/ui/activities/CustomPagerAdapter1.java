package com.sp18.ssu370.baseprojectapp.ui.activities;

import android.support.v4.view.PagerAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Gabri on 3/20/2018.
 */

public class CustomPagerAdapter1 extends PagerAdapter{
    private Context mContext;

    public CustomPagerAdapter1(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        ClothesObject clothesObject = ClothesObject.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(clothesObject.getLayoutResId(), collection, false);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return ClothesObject.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        ClothesObject customPagerEnum = ClothesObject.values()[position];
        return mContext.getString(customPagerEnum.getTitleResId());
    }
}
