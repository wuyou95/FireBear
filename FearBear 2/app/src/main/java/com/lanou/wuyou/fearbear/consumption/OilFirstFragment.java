package com.lanou.wuyou.fearbear.consumption;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou.wuyou.fearbear.R;
import com.lanou.wuyou.fearbear.db.DatabaseTool;
import com.lanou.wuyou.fearbear.db.RxSQLite;
import com.lanou.wuyou.fearbear.entity.RecordsEntity;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by dllo on 17/4/17.
 */

public class OilFirstFragment extends Fragment{
    private LinearLayout linearLayout;
    private int count = 0;
    private TextView textTop;
    private ImageView pointIv,pointIv1,pointIv2,pointIv3,pointIv4,leftIv,rightIv;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout = (LinearLayout) view.findViewById(R.id.point_linear);
        textTop = (TextView) view.findViewById(R.id.text_top);
        leftIv = (ImageView) view.findViewById(R.id.left_oil_first);
        rightIv = (ImageView) view.findViewById(R.id.right_oil_first);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_oil_first,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
                        break;
                    case 1:
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
                        break;
                    case 2:
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
                        break;
                    case 3:
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
                        break;
                    case 4:
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
                        break;
                }
            }
        });
        leftIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (count){
                    case 0:
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
                        break;
                    case 4:
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
                        break;
                    case 3:
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
                        break;
                    case 2:
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
                        break;
                    case 1:
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
                        break;
                }
            }
        });
    }
    public void queryRecords(){
        RxSQLite.queryRecords()
                .map(new Function<List<RecordsEntity>, List<RecordsEntity>>() {
                    @Override
                    public List<RecordsEntity> apply(@NonNull List<RecordsEntity> recordsEntities) throws Exception {
//                        RecordsEntity recordsEntity = new RecordsEntity();
                        DatabaseTool.getInstance().querySelectedRecords();
                        return null;
                    }
                });
    }
}
