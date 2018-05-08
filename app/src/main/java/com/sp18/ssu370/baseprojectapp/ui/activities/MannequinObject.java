package com.sp18.ssu370.baseprojectapp.ui.activities;

import com.sp18.ssu370.baseprojectapp.R;


/**
 * Created by Gabri on 4/25/2018.
 */

public enum MannequinObject {
    GIRLLARGE(R.string.gl, R.layout.girl_large),
    GIRLMEDIUM(R.string.GIRLMEDIUM, R.layout.girl_medium),
    GIRLSMALL(R.string.GIRLSMALL, R.layout.girl_small),
    GUYLARGE(R.string.GUYLARGE, R.layout.guy_large),
    GUYMEDIUM(R.string.GUYMEDIUM, R.layout.guy_medium),
    GUYSMALL(R.string.GUYSMALL, R.layout.guy_small);

    private int mTitleResId;
    private int mLayoutResId;

    MannequinObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }
}
