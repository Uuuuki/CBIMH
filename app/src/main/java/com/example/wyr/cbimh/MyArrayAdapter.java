package com.example.wyr.cbimh;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by wyr on 2018/3/22.
 */

public class MyArrayAdapter<T> extends ArrayAdapter {
    //构造方法
    public MyArrayAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
    }
    //复写这个方法，使返回的数据没有最后一项
    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}
