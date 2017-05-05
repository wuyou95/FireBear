package com.lanou.wuyou.fearbear.addoil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.lanou.wuyou.fearbear.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dllo on 17/4/22.
 */

public class OilAddActivity extends AppCompatActivity {
    private EditText dateEt, timeEt;
    private ImageView backIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_oil);
        dateEt = (EditText) findViewById(R.id.date_et);
        timeEt = (EditText) findViewById(R.id.time_et);
        backIv = (ImageView) findViewById(R.id.back_iv);
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        Date date1 = new Date(System.currentTimeMillis());
        String str = format.format(date);
        String string = format1.format(date1);
        timeEt.setText(string);
        dateEt.setText(str);
    }
}
