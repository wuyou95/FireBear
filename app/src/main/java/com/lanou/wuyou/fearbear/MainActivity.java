package com.lanou.wuyou.fearbear;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou.wuyou.fearbear.add.AddActivity;
import com.lanou.wuyou.fearbear.addoil.OilAddActivity;
import com.lanou.wuyou.fearbear.cast.OilCastFragment;
import com.lanou.wuyou.fearbear.consumption.ListAdapter;
import com.lanou.wuyou.fearbear.consumption.OilFirstFragment;
import com.lanou.wuyou.fearbear.db.DatabaseTool;
import com.lanou.wuyou.fearbear.db.RxSQLite;
import com.lanou.wuyou.fearbear.entity.CarEntity;
import com.lanou.wuyou.fearbear.ranking.RankingFragment;
import com.lanou.wuyou.fearbear.tool.OnViewClickListener;
import com.lanou.wuyou.fearbear.tool.SlidingMenu;
import com.lanou.wuyou.fearbear.total.OilTotalCastFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainAdapter adapter;
    private List<Fragment> fragments;
    private SlidingMenu slidingMenu;
    private List<CarEntity> cars;
    private Spinner spinner;
    private CarSpinnerAdapter spinnerAdapter;
    private CarEntity myCar;
    private AlertDialog dialog;
    private ListView listView;
    private ListAdapter listAdapter;
    private TextView textView;
    private int newPosition;
    private int a;
    private long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slidingMenu = (SlidingMenu) findViewById(R.id.sliding_menu);
        spinner = (Spinner) findViewById(R.id.spinner);

        fragments = new ArrayList<>();

        fragments.add(new OilFirstFragment());
        fragments.add(new RankingFragment());
        fragments.add(new OilCastFragment());
        fragments.add(new OilTotalCastFragment());

        adapter = new MainAdapter(getSupportFragmentManager(),fragments);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < adapter.getCount(); i++) {
            View tabView = adapter.getTabView(i,this);
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(tabView);
        }

        cars = new ArrayList<>();
        myCar = new CarEntity();
        query1();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

                if (position == spinner.getCount() - 1){
                    Log.d("222", "spinner.getCount():" + spinner.getCount());
                    dialog = new AlertDialog.Builder(MainActivity.this).create();
                    view = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_dialog, parent, false);
                    dialog.setView(view);

                    listView = (ListView) view.findViewById(R.id.list_item_dialog);
                    RxSQLite.queryAllCars()
                            .map(new Function<List<CarEntity>, List<CarEntity>>() {
                                @Override
                                public List<CarEntity> apply(@NonNull List<CarEntity> carEntities) throws Exception {
                                    return DatabaseTool.getInstance().queryCars();
                                }
                            }).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<CarEntity>>() {
                                @Override
                                public void accept(@NonNull List<CarEntity> carEntities) throws Exception {
                                    listAdapter = new ListAdapter(MainActivity.this);
                                    listAdapter.setCars(carEntities);
                                    listView.setAdapter(listAdapter);
                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            Intent intent = new Intent(MainActivity.this,AddActivity.class);
                                            startActivityForResult(intent,0);
                                        }
                                    });
                                    listAdapter.setOnViewClickListener(new OnViewClickListener() {
                                        @Override
                                        public void click( int position) {
                                            newPosition = position;
                                            CustomDialog();
                                        }
                                    });
                                }
                            });
                    ImageView imageView = (ImageView) view.findViewById(R.id.add_iv);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, AddActivity.class);
                            startActivity(intent);
                        }
                    });
                    textView = (TextView) view.findViewById(R.id.back_tv);
                    textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                    spinner.setSelection(spinnerAdapter.getCurrentPos());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void toggleMenu(View view){
        slidingMenu.toggle();
    }

    private void CustomDialog(){
        DialogInterface.OnClickListener dialogOnclickListener=new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case Dialog.BUTTON_POSITIVE:
                        int newId = cars.get(newPosition).get_id();
                        DatabaseTool.getInstance().removeCar(newId);
                        Toast.makeText(MainActivity.this, "删除:" + String.valueOf(newPosition), Toast.LENGTH_SHORT).show();
                        query();
                        query1();
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        Toast.makeText(MainActivity.this, "取消" , Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("删除车辆");
        builder.setMessage("删除将不可恢复.\n您确定删除" + cars.get(newPosition).getName() + ",以及它的所有油耗数据吗?");
        builder.setIcon(R.mipmap.warning);
        builder.setPositiveButton("确认",dialogOnclickListener);
        builder.setNegativeButton("取消", dialogOnclickListener);
        builder.create().show();
    }
    private void query(){
        RxSQLite.queryAllCars()
                .map(new Function<List<CarEntity>, List<CarEntity>>() {
                    @Override
                    public List<CarEntity> apply(@NonNull List<CarEntity> carEntities) throws Exception {
                        return DatabaseTool.getInstance().queryCars();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CarEntity>>() {
                    @Override
                    public void accept(@NonNull List<CarEntity> carEntities) throws Exception {
                        listAdapter.setCars(carEntities);
                        listView.setAdapter(listAdapter);
                    }
                });
    }
    private void query1(){
        RxSQLite.queryAllCars()
                .map(new Function<List<CarEntity>, List<CarEntity>>() {
                    @Override
                    public List<CarEntity> apply(@NonNull List<CarEntity> carEntities) throws Exception {
                        CarEntity car = new CarEntity();
                        car.setName("车辆管理");
                        car.set_id(-1);
                        carEntities.add(car);
                        if (carEntities.size() == 1){
                            myCar.setName("我的小车");
                            myCar.setSelected(1);
                            carEntities.add(0, myCar);
                            DatabaseTool.getInstance().addCar(myCar);
                        }
                        cars = carEntities;
                        return carEntities;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CarEntity>>() {
                    @Override
                    public void accept(@NonNull List<CarEntity> carEntities) throws Exception {
                        spinnerAdapter = new CarSpinnerAdapter(cars,MainActivity.this);
                        spinnerAdapter.notifyDataSetChanged();
                        spinner.setAdapter(spinnerAdapter);
                    }
                });
    }
    public void add(View view){
        Intent intent = new Intent(MainActivity.this,OilAddActivity.class);
        startActivity(intent);
    }
}
