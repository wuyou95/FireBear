package com.lanou.wuyou.fearbear.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou.wuyou.fearbear.MainActivity;
import com.lanou.wuyou.fearbear.R;
import com.lanou.wuyou.fearbear.db.DatabaseTool;
import com.lanou.wuyou.fearbear.entity.BrandEntity;
import com.lanou.wuyou.fearbear.entity.CarDetailEntity;
import com.lanou.wuyou.fearbear.entity.CarEntity;
import com.lanou.wuyou.fearbear.retrofit.HttpManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dllo on 17/4/22.
 */

public class AddActivity extends AppCompatActivity{
    private Spinner brandsSpinner,seriesSpinner, typeSpinner;
    private int index,type,series;
    private TextView leftTv,rightTv;
    private RelativeLayout relativeLayout;
    private EditText editText;
    private ImageView imageView;
    private CarEntity carEntity;
    private List<CarEntity> carEntityList;
    private String name1;
    private String name2;
    private String name3;
    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        brandsSpinner = (Spinner) findViewById(R.id.brand_sp);
        seriesSpinner = (Spinner) findViewById(R.id.series_sp);
        typeSpinner = (Spinner) findViewById(R.id.type_sp);
        leftTv = (TextView) findViewById(R.id.text_add_left);
        rightTv = (TextView) findViewById(R.id.text_add_right);
        relativeLayout = (RelativeLayout) findViewById(R.id.view_add);
        editText = (EditText) findViewById(R.id.name_et);
        imageView = (ImageView) findViewById(R.id.imageView2);
        relativeLayout.setVisibility(View.GONE);
        carEntity = new CarEntity();
        carEntityList = new ArrayList<>();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                intent.putExtra("name",editText.getText().toString());
                intent.putExtra("brand", name1);
                intent.putExtra("series",name2);
                intent.putExtra("type",name3);
                intent.putExtra("nameDetail",name);
                startActivityForResult(intent,0);
                Log.d("111", "editText:" + editText.getText().toString());
                Log.d("111", name1 + name2 + name3);
                carEntityList = new ArrayList<>();
                carEntity.setName(editText.getText().toString());
                carEntityList.add(carEntity);
                DatabaseTool.getInstance().addCar(carEntity);
                Toast.makeText(AddActivity.this, "已保存" + editText.getText().toString(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        brands();
        brandsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, final long id) {
                HttpManager.getInstance().getBearApi()
                        .getBrands()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<BrandEntity>() {
                            @Override
                            public void accept(@NonNull BrandEntity brandEntity) throws Exception {
                                index = brandEntity.getIdxes().get(position);
                                name1 = brandEntity.getNames().get(position);
                                Log.d("111", name1);
                                series();
                            }
                        });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        seriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                HttpManager.getInstance().getBearApi()
                        .getCarSeries(index)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<BrandEntity>() {
                            @Override
                            public void accept(@NonNull BrandEntity brandEntity) throws Exception {
                                series = brandEntity.getIdxes().get(position);
                                name2 = brandEntity.getNames().get(position);
                                Log.d("111", name2);
                                type();
                            }
                        });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                HttpManager.getInstance().getBearApi()
                        .getCarType(index,series)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<BrandEntity>() {
                            @Override
                            public void accept(@NonNull BrandEntity brandEntity) throws Exception {
                                type = brandEntity.getIdxes().get(position);
                                name3 = brandEntity.getNames().get(position);
                                Log.d("111", name3);
                                detail();
                            }
                        });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    public void brands(){
        HttpManager.getInstance().getBearApi()
                .getBrands()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BrandEntity>() {
                    @Override
                    public void accept(@NonNull BrandEntity brandEntity) throws Exception {
                        AddSpinnerAdapter addSpinnerAdapter = new AddSpinnerAdapter(AddActivity.this);
                        addSpinnerAdapter.setEntities(brandEntity);
                        brandsSpinner.setAdapter(addSpinnerAdapter);
                    }
                });
    }
    public void series(){
        HttpManager.getInstance().getBearApi()
                .getCarSeries(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BrandEntity>() {
                    @Override
                    public void accept(@NonNull BrandEntity brandEntity) throws Exception {
                        AddSpinnerAdapter addSpinnerAdapter = new AddSpinnerAdapter(AddActivity.this);
                        addSpinnerAdapter.setEntities(brandEntity);
                        seriesSpinner.setAdapter(addSpinnerAdapter);
                    }
                });
    }
    public void type(){
        HttpManager.getInstance().getBearApi()
                .getCarType(index,series)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BrandEntity>() {
                    @Override
                    public void accept(@NonNull BrandEntity brandEntity) throws Exception {
                        AddSpinnerAdapter addSpinnerAdapter = new AddSpinnerAdapter(AddActivity.this);
                        addSpinnerAdapter.setEntities(brandEntity);
                        typeSpinner.setAdapter(addSpinnerAdapter);
                    }
                });
    }
    public void detail(){
        HttpManager.getInstance().getBearApi()
                .getCarDetail(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarDetailEntity>() {
                    @Override
                    public void accept(@NonNull CarDetailEntity carDetailEntity) throws Exception {
                        if (carDetailEntity!= null){
                            relativeLayout.setVisibility(View.VISIBLE);
                            leftTv.setText(carDetailEntity.getEngine());
                            rightTv.setText(carDetailEntity.getGearbox());
                            name = carDetailEntity.getName();
                            int id = carDetailEntity.getId();
                            Toast.makeText(AddActivity.this, "status:" + id, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
