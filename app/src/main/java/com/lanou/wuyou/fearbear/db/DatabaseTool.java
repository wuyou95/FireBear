package com.lanou.wuyou.fearbear.db;

import com.lanou.wuyou.fearbear.app.BearApplication;
import com.lanou.wuyou.fearbear.entity.CarEntity;
import com.lanou.wuyou.fearbear.entity.MoneyEntity;
import com.lanou.wuyou.fearbear.entity.RecordsEntity;
import com.lanou.wuyou.fearbear.tool.TableCarOperation;
import com.lanou.wuyou.fearbear.tool.TableRecordsoperation;

import java.util.List;

/**
 * Created by Risky57 on 2017/4/13.
 */

public class DatabaseTool implements TableCarOperation,TableRecordsoperation {
    private static class SingletonHolder {
        private static final DatabaseTool INSTANCE = new DatabaseTool();
    }

    public static DatabaseTool getInstance () {
        return SingletonHolder.INSTANCE;
    }

    private BearSQLiteHelper mHelper;
    private TableCarOperation mCarOperation;
    private TableRecordsoperation recordsoperation;

    // Realm
    private DatabaseTool () {// GreenDao
        mHelper = new BearSQLiteHelper(BearApplication.getContext());
        mCarOperation = new CarOperationAndroid(mHelper);
        recordsoperation = new RecordsOperationAndroid(mHelper);

    }

    @Override
    public void addRecords(RecordsEntity recordsEntity) {
        recordsoperation.addRecords(recordsEntity);
    }

    @Override
    public void removeRecords(int id) {
        recordsoperation.removeRecords(id);
    }

    @Override
    public void updateRecords(RecordsEntity recordsEntity) {
        recordsoperation.updateRecords(recordsEntity);
    }

    @Override
    public List<RecordsEntity> querySelectedRecords() {
        return recordsoperation.querySelectedRecords();
    }

    @Override
    public List<RecordsEntity> querySelectedYearRecords() {
        return recordsoperation.querySelectedYearRecords();
    }

    @Override
    public List<RecordsEntity> querySelectedHalfTearRecords() {
        return recordsoperation.querySelectedHalfTearRecords();
    }

    @Override
    public List<RecordsEntity> querySelectedThreeMonthsRecords() {
        return recordsoperation.querySelectedThreeMonthsRecords();
    }

    @Override
    public List<MoneyEntity> queryMonthMoney() {
        return recordsoperation.queryMonthMoney();
    }

    @Override
    public List<MoneyEntity> queryYearMoney() {
        return recordsoperation.queryYearMoney();
    }

    @Override
    public void addCar (CarEntity car) {
        mCarOperation.addCar(car);
    }

    public void removeCar (int id) {
        mCarOperation.removeCar(id);
    }

    public void updateCar(CarEntity car){
        mCarOperation.updateCar(car);
    }



    public List<CarEntity> queryCars () {
        return mCarOperation.queryCars();
    }

    public CarEntity querySelectedCar(){
        return mCarOperation.querySelectedCar();
    }

    // 更改选中的车辆
    public void changeSelectedCar(int id){
        mCarOperation.changeSelectedCar(id);
    }

    public void changeSelectedCar(CarEntity newSelectedCar){
        mCarOperation.changeSelectedCar(newSelectedCar);
    }



}
