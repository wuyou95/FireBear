package com.lanou.wuyou.fearbear;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.lanou.wuyou.fearbear.entity.CarEntity;

import java.util.List;

/**
 * Created by dllo on 17/4/18.
 */

public class CarSpinnerAdapter extends ArrayAdapter{
    private List<CarEntity> carEntities;
    private Context context;
    private int currentPos;

    public int getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }

    public CarSpinnerAdapter(List<CarEntity> carEntities, Context context) {
        super(context,0);
        this.carEntities = carEntities;
        this.context = context;
    }

    @Override
    public int getCount() {
        return carEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return carEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return carEntities.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item,parent,false);
        }
        TextView textView = (TextView) convertView;
        CarEntity carEntity = (CarEntity) getItem(position);
        textView.setText(carEntity.getName());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item,parent,false);
        CheckedTextView textView = (CheckedTextView) convertView;
        CarEntity carEntity = (CarEntity) getItem(position);
        textView.setText(carEntity.getName());
        return convertView;
    }
}
