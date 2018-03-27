package com.example.wyr.cbimh;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wyr on 2018/3/24.
 */

public class PaddingDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;


    public PaddingDecoration(Context context) {
        dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.padding);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //outRect.top = dividerHeight;
        //outRect.bottom = dividerHeight;

        outRect.top = dividerHeight;
        outRect.bottom = dividerHeight;
        outRect.left = dividerHeight;
        outRect.right = dividerHeight;
    }

}
