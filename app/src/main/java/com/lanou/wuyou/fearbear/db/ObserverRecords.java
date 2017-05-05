package com.lanou.wuyou.fearbear.db;

import com.lanou.wuyou.fearbear.entity.MoneyEntity;
import com.lanou.wuyou.fearbear.entity.RecordsEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static android.R.attr.id;

/**
 * Created by dllo on 17/4/15.
 */

public class ObserverRecords {
    public static void addRecords(RecordsEntity recordsEntity){
        Observable.just(recordsEntity).map(new Function<RecordsEntity, String>() {
            @Override
            public String apply(@NonNull RecordsEntity recordsEntity) throws Exception {
                DatabaseTool.getInstance().addRecords(recordsEntity);
                return null;
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }
    public static void deleteRecords(RecordsEntity recordsEntity){
        Observable.just(recordsEntity).map(new Function<RecordsEntity, String >() {
            @Override
            public String apply(@NonNull RecordsEntity recordsEntity) throws Exception {
                DatabaseTool.getInstance().removeRecords(id);
                return null;
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }
    public static void updateRecords(RecordsEntity recordsEntity){
        Observable.just(recordsEntity).map(new Function<RecordsEntity, String>() {
            @Override
            public String apply(@NonNull RecordsEntity recordsEntity) throws Exception {
                DatabaseTool.getInstance().updateRecords(recordsEntity);
                return null;
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }
    public static Observable<List<RecordsEntity>> queryYearRecords(){
        return Observable.just("").map(new Function<String, List<RecordsEntity>>() {
            @Override
            public List<RecordsEntity> apply(@NonNull String s) throws Exception {
                return DatabaseTool.getInstance().querySelectedYearRecords();
            }
        });
    }
    public static Observable<List<MoneyEntity>> queryMonthMoney(){
        return Observable.just("").map(new Function<String, List<MoneyEntity>>() {
            @Override
            public List<MoneyEntity> apply(@NonNull String s) throws Exception {
                return DatabaseTool.getInstance().queryMonthMoney();
            }
        });
    }
}
