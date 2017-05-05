package com.lanou.wuyou.fearbear.consumption;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou.wuyou.fearbear.R;
import com.lanou.wuyou.fearbear.db.RxSQLite;
import com.lanou.wuyou.fearbear.entity.RecordsEntity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dllo on 17/4/17.
 */

public class OilFirstFragment extends Fragment{
    private LinearLayout linearLayout;
    private int count = 0;
    private TextView textTop,oilFirstTv;
    private ImageView pointIv,pointIv1,pointIv2,pointIv3,pointIv4,leftIv,rightIv;
    private long[] dates;
    private LinearChartView customView;
    private float[] consume;
    private int[] odometer;
    private float[] consumeA;
    private float[] price;
    private float[] oilConsumption = new float[]{5,18,25,12,20,8,3,27,14,8,19,2,9,27,8,7,28,8,12,5,15,2,4,24,8,9,23,6,22,15,4,14,28,5,7};
    private float[] yearConsumption = new float[]{5,27,2,4,10,25,7,4,22,8,5,18};
    private float[] halfConsumption = new float[]{8,14,26,9,2,13};
    private float[] threeConsumption = new float[]{3,28,19};

    public static String getYearToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        return sf.format(d);
    }
    public static String getMonthToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("MM");
        return sf.format(d);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout = (LinearLayout) view.findViewById(R.id.point_linear);
        textTop = (TextView) view.findViewById(R.id.text_top);
        leftIv = (ImageView) view.findViewById(R.id.left_oil_first);
        rightIv = (ImageView) view.findViewById(R.id.right_oil_first);
        oilFirstTv = (TextView) view.findViewById(R.id.oil_first_tv);
        customView = (LinearChartView) view.findViewById(R.id.custom_view);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_oil_first,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        selectedRecords();
        customView.setCOUNT_X_MARK(35);
        customView.setConsume(oilConsumption);

        for (int i = 0; i < 5; i++) {
            pointIv = new ImageView(getContext());
            pointIv.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            pointIv.setLayoutParams(params);
            if (i == 0) {
                pointIv.setImageResource(R.mipmap.point_white);
            } else {
                pointIv.setImageResource(R.mipmap.point_grey);
            }
            linearLayout.addView(pointIv);
        }
        rightIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (count){
                    case 0:
                       one();
                        break;
                    case 1:
                        two();
                        break;
                    case 2:
                        three();
                        break;
                    case 3:
                        four();
                        break;
                    case 4:
                        zero();
                        break;
                }
            }
        });
        leftIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (count){
                    case 0:
                        four();
                        break;
                    case 4:
                       three();
                        break;
                    case 3:
                        two();
                        break;
                    case 2:
                        one();
                        break;
                    case 1:
                        zero();
                        break;
                }
            }
        });
    }
    public void selectedRecords(){
        RxSQLite.selectedRecords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<RecordsEntity>>() {
                    @Override
                    public void accept(@NonNull List<RecordsEntity> recordsEntities) throws Exception {
                        dates = new long[recordsEntities.size()];
                        for (int i = 0; i < recordsEntities.size(); i++) {
                            dates[i] = recordsEntities.get(i).getDate();
                            getYearToString(dates[i]);
                            Log.d("111", getMonthToString(dates[i]));
                        }
                        consume = new float[recordsEntities.size()];
                        odometer = new int[recordsEntities.size()];
                        consumeA = new float[recordsEntities.size()];
                        price = new float[recordsEntities.size()];
                        for (int i = 0; i < recordsEntities.size(); i++) {
                            price[i] = (float) recordsEntities.get(i).getPrice();
                            consume[i] = (float) recordsEntities.get(i).getYuan();
                            odometer[i] = recordsEntities.get(i).getOdometer();
                        }
                    }
                });
    }

    public void one(){
        customView.setCOUNT_X_MARK(12);
        customView.setConsume(yearConsumption);
        count = 1;
        textTop.setText("最近一年油耗统计曲线");
        linearLayout.removeAllViewsInLayout();
        for (int i = 0; i < 5; i++) {
            pointIv1 = new ImageView(getContext());
            pointIv1.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            pointIv1.setLayoutParams(params);
            if (i == 1) {
                pointIv1.setImageResource(R.mipmap.point_white);
            } else {
                pointIv1.setImageResource(R.mipmap.point_grey);
            }
            linearLayout.addView(pointIv1);
        }
    }
    public void two(){
        customView.setCOUNT_X_MARK(6);
        customView.setConsume(halfConsumption);
        count = 2;
        textTop.setText("最近半年油耗统计曲线");
        linearLayout.removeAllViewsInLayout();
        for (int i = 0; i < 5; i++) {
            pointIv2 = new ImageView(getContext());
            pointIv2.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            pointIv2.setLayoutParams(params);
            if (i == 2) {
                pointIv2.setImageResource(R.mipmap.point_white);
            } else {
                pointIv2.setImageResource(R.mipmap.point_grey);
            }
            linearLayout.addView(pointIv2);
        }
    }
    private void three(){
        customView.setCOUNT_X_MARK(3);
        customView.setConsume(threeConsumption);
        count = 3;
        textTop.setText("最近三个月油耗统计曲线");
        linearLayout.removeAllViewsInLayout();
        for (int i = 0; i < 5; i++) {
            pointIv3 = new ImageView(getContext());
            pointIv3.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            pointIv3.setLayoutParams(params);
            if (i == 3) {
                pointIv3.setImageResource(R.mipmap.point_white);
            } else {
                pointIv3.setImageResource(R.mipmap.point_grey);
            }
            linearLayout.addView(pointIv3);
        }
    }
    private void four(){
        customView.setCOUNT_X_MARK(12);
        customView.setConsume(yearConsumption);
        count = 4;
        textTop.setText("同城车型油耗基准线");
        linearLayout.removeAllViewsInLayout();
        for (int i = 0; i < 5; i++) {
            pointIv4 = new ImageView(getContext());
            pointIv4.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            pointIv4.setLayoutParams(params);
            if (i == 4) {
                pointIv4.setImageResource(R.mipmap.point_white);
            } else {
                pointIv4.setImageResource(R.mipmap.point_grey);
            }
            linearLayout.addView(pointIv4);
        }
    }
    private void zero(){
        customView.setCOUNT_X_MARK(35);
        customView.setConsume(oilConsumption);
        count = 0;
        textTop.setText("     油耗统计曲线");
        linearLayout.removeAllViewsInLayout();
        for (int i = 0; i < 5; i++) {
            pointIv = new ImageView(getContext());
            pointIv.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            pointIv.setLayoutParams(params);
            if (i == 0) {
                pointIv.setImageResource(R.mipmap.point_white);
            } else {
                pointIv.setImageResource(R.mipmap.point_grey);
            }
            linearLayout.addView(pointIv);
        }
    }
}
