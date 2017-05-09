package com.lanou.wuyou.fearbear.oilrecords;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou.wuyou.fearbear.R;
import com.lanou.wuyou.fearbear.entity.RecordsEntity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by dllo on 17/5/8.
 */

public class OilRecordsAdapter extends BaseAdapter{
    private List<RecordsEntity> recordsEntities;
    private Context context;

    public OilRecordsAdapter(Context context) {
        this.context = context;
    }

    public void setRecordsEntities(List<RecordsEntity> recordsEntities) {
        this.recordsEntities = recordsEntities;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (recordsEntities == null){
            return 0;
        }
        return recordsEntities.size() == 0 ? 0 : recordsEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return recordsEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return recordsEntities.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_oil_records,parent,false);
            holder = new MainHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MainHolder) convertView.getTag();
        }

        holder.dateTv.setText(String.valueOf(getDateToString((int)recordsEntities.get(position).getDate())));
        holder.kiloTv.setText(Integer.toString(recordsEntities.get(position).getOdometer());

//        holder.consumTv.setText(recordsEntities.get(position).);
        return convertView;
    }
    class MainHolder{
        private TextView dateTv,kiloTv,consumTv;
        public MainHolder(View view){
            dateTv = (TextView) view.findViewById(R.id.time_tv);
            kiloTv = (TextView) view.findViewById(R.id.kilometer_tv);
            consumTv = (TextView) view.findViewById(R.id.consumption_per_tv);
        }
    }
    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }
}
