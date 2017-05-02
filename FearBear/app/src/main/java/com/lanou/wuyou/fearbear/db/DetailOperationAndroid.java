//package com.lanou.wuyou.fearbear.db;
//
//import android.content.ContentValues;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import com.lanou.wuyou.fearbear.entity.CarDetailEntity;
//import com.lanou.wuyou.fearbear.tool.DetailOperation;
//
//import java.util.List;
//
///**
// * Created by dllo on 17/4/26.
// */
//
//public class DetailOperationAndroid implements DetailOperation{
//    private SQLiteOpenHelper helper;
//
//    public DetailOperationAndroid(SQLiteOpenHelper helper) {
//        this.helper = helper;
//    }
//
//    @Override
//    public void addDetail(CarDetailEntity detail) {
//        SQLiteDatabase db = helper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        if (detail.getId() != 0){
//            values.put(BearSQLiteHelper.RID,detail.getId());
//        }
//        values.put(BearSQLiteHelper.BRAND,detail.getName());
//    }
//
//    @Override
//    public List<CarDetailEntity> queryStails() {
//        return null;
//    }
//}
