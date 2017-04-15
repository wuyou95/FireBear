package com.wuqi.dev.sqlite.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Risky57 on 2017/4/13.
 */

public class CarOperationAndroid implements TableCarOperation {

    private SQLiteOpenHelper mHelper;

    public CarOperationAndroid (SQLiteOpenHelper helper) {
        mHelper = helper;
    }

    @Override
    public void addCar (CarEntity car) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (car.get_id() != 0) {
            values.put(BearSQLiteHelper._ID, car.get_id());
        }
        values.put(BearSQLiteHelper.NAME, car.getName());
        values.put(BearSQLiteHelper.SELECTED, car.getSelected());
        values.put(BearSQLiteHelper.MODEL, car.getModel());
        values.put(BearSQLiteHelper.UUID, car.getUuid());
        db.insert(BearSQLiteHelper.CARS_TBL, null, values);
        db.close();
    }

    @Override
    public void removeCar (int id) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String whereClause = BearSQLiteHelper._ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.delete(BearSQLiteHelper.CARS_TBL, whereClause, whereArgs);
        db.close();
    }

    @Override
    public void updateCar (CarEntity car) {
        updateCar(openDatabase(), true, car);
    }

    private void updateCar (SQLiteDatabase db, boolean isClose, CarEntity car) {
        ContentValues values = new ContentValues();
        values.put(BearSQLiteHelper.NAME, car.getName());
        values.put(BearSQLiteHelper.SELECTED, car.getSelected());
        values.put(BearSQLiteHelper.MODEL, car.getModel());
        values.put(BearSQLiteHelper.UUID, car.getUuid());
        String whereClause = BearSQLiteHelper._ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(car.get_id())};
        db.update(BearSQLiteHelper.CARS_TBL, values, whereClause, whereArgs);
        if (isClose) {
            closeDatabase(db);
        }
    }

    @Override
    public List<CarEntity> queryCars () {
        List<CarEntity> cars = new ArrayList<>(5);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.query(BearSQLiteHelper.CARS_TBL, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int indexId = cursor.getColumnIndex(BearSQLiteHelper._ID);
            int indexName = cursor.getColumnIndex(BearSQLiteHelper.NAME);
            int indexSelected = cursor.getColumnIndex(BearSQLiteHelper.SELECTED);
            int indexModel = cursor.getColumnIndex(BearSQLiteHelper.MODEL);
            int indexUUID = cursor.getColumnIndex(BearSQLiteHelper.UUID);
            do {
                int id = cursor.getInt(indexId);
                String name = cursor.getString(indexName);
                int selected = cursor.getInt(indexSelected);
                int model = cursor.getInt(indexModel);
                String uuid = cursor.getString(indexUUID);
                CarEntity car = new CarEntity(id);
                car.setName(name);
                car.setSelected(selected);
                car.setModel(model);
                car.setUuid(uuid);

                cars.add(car);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return cars;
    }

    @Override
    public CarEntity querySelectedCar () {
        return querySelectedCar(openDatabase(), true);
    }

    // 查询出当前选中的小车
    private CarEntity querySelectedCar(SQLiteDatabase db, boolean isClose){
        String selection = BearSQLiteHelper.SELECTED + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(1)};
        Cursor cursor = db.query(
                BearSQLiteHelper.CARS_TBL,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            int indexId = cursor.getColumnIndex(BearSQLiteHelper._ID);
            int indexName = cursor.getColumnIndex(BearSQLiteHelper.NAME);
            int indexSelected = cursor.getColumnIndex(BearSQLiteHelper.SELECTED);
            int indexModel = cursor.getColumnIndex(BearSQLiteHelper.MODEL);
            int indexUUID = cursor.getColumnIndex(BearSQLiteHelper.UUID);
            int id = cursor.getInt(indexId);
            String name = cursor.getString(indexName);
            int selected = cursor.getInt(indexSelected);
            int model = cursor.getInt(indexModel);
            String uuid = cursor.getString(indexUUID);
            CarEntity car = new CarEntity(id);
            car.setName(name);
            car.setSelected(selected);
            car.setModel(model);
            car.setUuid(uuid);
            cursor.close();
            return car;
        }
        if (isClose) {
            closeDatabase(db);
        }
        return null;
    }

    @Override
    public void changeSelectedCar (int id) {
        SQLiteDatabase db = openDatabase();
        // 先把原来选中的车辆设置为不选中
        CarEntity currentSelectedCar = querySelectedCar(db, false);
        currentSelectedCar.setSelected(0);
        updateCar(currentSelectedCar);
        // 将新选中的车设置为选中状态
        ContentValues values = new ContentValues();
        values.put(BearSQLiteHelper.SELECTED, 1);
        String whereClause = BearSQLiteHelper._ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.update(BearSQLiteHelper.CARS_TBL,
                values,
                whereClause,
                whereArgs);
        closeDatabase(db);
    }

    @Override
    public void changeSelectedCar (CarEntity newSelectedCar) {
        SQLiteDatabase db = openDatabase();
        // 先把原来选中的车辆设置为不选中
        CarEntity currentSelectedCar = querySelectedCar(db, false);
        currentSelectedCar.setSelected(0);
        updateCar(db, false, currentSelectedCar);
        // 将新选中的车设置为选中状态
        newSelectedCar.setSelected(1);
        updateCar(db, false, newSelectedCar);
        closeDatabase(db);
    }

    private SQLiteDatabase openDatabase(){
        return mHelper.getWritableDatabase();
    }

    private void closeDatabase(SQLiteDatabase db){
        if (db != null) {
            db.close();
        }
    }
}
