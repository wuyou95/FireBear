package com.lanou.wuyou.fearbear.db;

import com.lanou.wuyou.fearbear.entity.CarEntity;
import com.lanou.wuyou.fearbear.entity.MoneyEntity;
import com.lanou.wuyou.fearbear.entity.RecordsEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dllo on 17/4/18.
 */

public class RxSQLite {
    public static Observable<List<CarEntity>> queryAllCars(){
        return Observable.just(0)
                .map(new Function<Integer, List<CarEntity>>() {
                    @Override
                    public List<CarEntity> apply(@NonNull Integer integer) throws Exception {
                        return DatabaseTool.getInstance().queryCars();
                    }
                });
    }
    public static void addCar(CarEntity carEntity){
        Observable.just(carEntity)
                .map(new Function<CarEntity, Integer>() {
                    @Override
                    public Integer apply(@NonNull CarEntity carEntity) throws Exception {
                        DatabaseTool.getInstance().addCar(carEntity);
                        return 0;
                    }
                }).subscribeOn(Schedulers.io()).subscribe();
    }
    public static Observable<List<MoneyEntity>> queryMonthMoney(){
        return Observable.just(0)
                .map(new Function<Integer, List<MoneyEntity>>() {
                    @Override
                    public List<MoneyEntity> apply(@NonNull Integer integer) throws Exception {
                        return DatabaseTool.getInstance().queryMonthMoney();
                    }
                });
    }
    public static Observable<List<RecordsEntity>> queryRecords(){
        return Observable.just(0)
                .map(new Function<Integer, List<RecordsEntity>>() {
                    @Override
                    public List<RecordsEntity> apply(@NonNull Integer integer) throws Exception {
                        return DatabaseTool.getInstance().querySelectedRecords();
                    }
                });
    }
}
