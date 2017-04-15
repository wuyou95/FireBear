package com.wuqi.dev.sqlite.db;

import com.wuqi.dev.sqlite.app.BearApplication;

import java.util.List;

/**
 * Created by Risky57 on 2017/4/13.
 */

public class DatabaseTool implements TableCarOperation,TableRecordsoperation{
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
//    private static Context sContext;
//
//    public static void setContext (Context context) {
//        if (!(context instanceof Application)){
//            throw new IllegalArgumentException("你传递的Context不正确, 必须传递Application的Context");
//        }
//        sContext = context;
//    }

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
