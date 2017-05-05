package com.lanou.wuyou.fearbear.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Risky57 on 2017/4/13.
 */

class BearSQLiteHelper extends SQLiteOpenHelper {

    private static final String NAME_DB = "oil_db(1).";
    private static final int VERSION = 8;

    static final String CARS_TBL = "cars_tbl";
    static final String RECORDS_TBL = "records_tbl";
    static final String DETAIL_YBL = "detail_tbl";

    static final String _ID = "_id";
    static final String NAME = "name";
    static final String SELECTED = "selected";
    static final String MODEL = "model";
    static final String UUID = "uuid";
    static final String RID = "_id";
    static final String DATE = "date";
    static final String ODOMETER = "odometer";
    static final String PRICE = "price";
    static final String TYPE = "type";
    static final String GASSUP = "gassup";
    static final String REMARK = "remark";
    static final String CARLD = "carId";
    static final String FORGET = "forget";
    static final String LIGHTON = "lightOn";
    static final String STATIONLD = "stationId";
    static final String YUAN = "yuan";

    BearSQLiteHelper (Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        String sqlCar = "create table " + CARS_TBL + " (" + _ID +
                " integer primary key autoincrement," +
                NAME + " text," +
                SELECTED + " integer," +
                MODEL + " integer," +
                UUID + " integer);";
        db.execSQL(sqlCar);
        String sqlRecords = "create table " + RECORDS_TBL + " (" + RID +
                " integer primary key autoincrement," +
                DATE + " long," + ODOMETER + " integer," + PRICE + " double," + YUAN + "yuan," + TYPE + " integer," + GASSUP + " integer," +
                REMARK + " integer," + CARLD + " integer," + FORGET + " integer," + LIGHTON + " integer," + STATIONLD +" integer);";
        db.execSQL(sqlRecords);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(VERSION);
    }
}
