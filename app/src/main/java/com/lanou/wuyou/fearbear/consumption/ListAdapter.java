package com.lanou.wuyou.fearbear.consumption;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.wuyou.fearbear.R;
import com.lanou.wuyou.fearbear.entity.CarEntity;
import com.lanou.wuyou.fearbear.tool.OnViewClickListener;

import java.util.List;

/**
 * Created by dllo on 17/4/19.
 */

public class ListAdapter extends BaseAdapter {
    private List<CarEntity> cars;
    private Context context;
    private OnViewClickListener onViewClickListener;

    public void setOnViewClickListener(OnViewClickListener onViewClickListener) {
        this.onViewClickListener = onViewClickListener;
    }

    public ListAdapter(Context context) {
        this.context = context;
    }

    public void setCars(List<CarEntity> cars) {
        this.cars = cars;
        notifyDataSetChanged();
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
        return cars == null ? 0 : position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        CarEntity entity = cars.get(position);
        holder.textView.setText(entity.getName());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewClickListener.click(position);
            }
        });
        return convertView;
    }



    class Holder{
        TextView textView;
        ImageView imageView;
        public Holder(View view){
            textView = (TextView) view.findViewById(R.id.name_car);
            imageView = (ImageView) view.findViewById(R.id.delete_iv);
        }
    }
}
