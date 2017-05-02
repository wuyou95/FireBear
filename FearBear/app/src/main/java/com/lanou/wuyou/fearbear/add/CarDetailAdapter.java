package com.lanou.wuyou.fearbear.add;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lanou.wuyou.fearbear.entity.CarDetailEntity;

/**
 * Created by dllo on 17/4/25.
 */

public class CarDetailAdapter extends BaseAdapter{
    private Context context;
    private CarDetailEntity entity;
    @Override
    public int getCount() {
        return entity.getEngine() == null ? 0 : entity.getEngine().length();
    }

    @Override
    public Object getItem(int position) {
        return entity.getEngine();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
