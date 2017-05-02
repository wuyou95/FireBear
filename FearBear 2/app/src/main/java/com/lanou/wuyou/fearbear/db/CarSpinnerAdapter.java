package com.lanou.wuyou.fearbear.db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou.wuyou.fearbear.entity.CarEntity;

import java.util.List;

/**
 * Created by dllo on 17/4/18.
 */

public class CarSpinnerAdapter extends BaseAdapter{

    private List<CarEntity> cars;
    private LayoutInflater inflater;

    public CarSpinnerAdapter(List<CarEntity> cars, Context context) {
        this.cars = cars;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cars == null ? 0 : cars.size();
    }

    @Override
    public Object getItem(int position) {
        return cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cars.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(android.R.layout.simple_spinner_item,null);
        }
        TextView textView = (TextView) convertView;
        CarEntity carEntity = (CarEntity) getItem(position);
        textView.setText(carEntity.getName());
        return convertView;
    }
}
