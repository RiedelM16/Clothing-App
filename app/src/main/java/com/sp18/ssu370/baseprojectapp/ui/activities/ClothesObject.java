package com.sp18.ssu370.baseprojectapp.ui.activities;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sp18.ssu370.baseprojectapp.R;

/**
 * Created by Gabri on 3/20/2018.
 */

public enum ClothesObject {
    TOPS(R.string.tops_camera, R.layout.tops_view),
    BOTTOMS(R.string.bottoms_camera, R.layout.bottoms_view),
    ACCESSORIES(R.string.accessories_camera, R.layout.accessories_view),
    SHOES(R.string.shoes_camera, R.layout.shoes_view),
    OUTERWEAR(R.string.outerwear_camera, R.layout.outerwear_view),
    DRESSES(R.string.dresses_camera, R.layout.dresses_view);

    private int mTitleResId;
    private int mLayoutResId;

    ClothesObject(int titleResId, int layoutResId) {
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
