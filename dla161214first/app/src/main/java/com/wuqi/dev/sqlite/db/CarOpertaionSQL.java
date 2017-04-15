package com.wuqi.dev.sqlite.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Risky57 on 2017/4/13.
 */

public class CarOpertaionSQL implements TableCarOperation {

    private SQLiteOpenHelper mHelper;

    public CarOpertaionSQL (SQLiteOpenHelper helper) {
        mHelper = helper;
    }

    @Override
    public void addCar (CarEntity car) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql;
        if (car.get_id() == 0) {
            sql = "insert into cars_tbl (name, selected, model, uuid) values (" + car.getName() + ", " + car.getSelected() + ", " + car.getModel() + ", " + car.getUuid() + ");";
        }else{
            sql = "insert into cars_tbl values (" +car.get_id() + ", " + car.getName() + ", " + car.getSelected() + ", " + car.getModel() + ", " + car.getUuid() + ");";
        }
        db.execSQL(sql);
    }

    @Override
    public void removeCar (int id) {

    }

    @Override
    public void updateCar (CarEntity car) {

    }

    @Override
    public List<CarEntity> queryCars () {
        return null;
    }

    @Override
    public CarEntity querySelectedCar () {
        return null;
    }

    @Override
    public void changeSelectedCar (int id) {

    }

    @Override
    public void changeSelectedCar (CarEntity newSelectedCar) {

    }
}
