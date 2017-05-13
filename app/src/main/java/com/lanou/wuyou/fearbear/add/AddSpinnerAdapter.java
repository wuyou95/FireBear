package com.lanou.wuyou.fearbear.add;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.lanou.wuyou.fearbear.entity.BrandEntity;

/**
 * Created by dllo on 17/4/24.
 */

public class AddSpinnerAdapter extends BaseAdapter{
    private BrandEntity brandEntity;
    private Context context;

    public AddSpinnerAdapter(Context context) {
        this.context = context;
    }

    public void setEntities(BrandEntity brandEntity) {
     this.brandEntity = brandEntity;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
       return brandEntity.getNames().size() == 0 ? 0 : brandEntity.getNames().size();
    }

    @Override
    public Object getItem(int position) {
        return brandEntity.getNames().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item,parent,false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(brandEntity.getNames().get(position));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item,parent,false);
        CheckedTextView textView = (CheckedTextView) convertView;
        textView.setText(brandEntity.getNames().get(position));
        return convertView;
    }
}
