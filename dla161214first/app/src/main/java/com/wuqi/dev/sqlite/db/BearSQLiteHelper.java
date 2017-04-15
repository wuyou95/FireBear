package com.wuqi.dev.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Risky57 on 2017/4/13.
 */

class BearSQLiteHelper extends SQLiteOpenHelper {

    private static final String NAME_DB = "oil.db";
    private static final int VERSION = 1;

    static final String CARS_TBL = "cars_tbl";
    static final String RECORDS_TBL = "records_tbl";

    static final String _ID = "_id";
    static final String NAME = "name";
    static final String SELECTED = "selected";
    static final String MODEL = "model";
    static final String UUID = "uuid";
    static final String RID = "id";
    static final String DATE = "date";
    static final String ODOMETER = "odometer";
    static final String PRICE = "price";
    static final String TYPE = "type";
    static final String GASSUP = "gassup";
    static final String REMARK = "remark";
    static final String CARLD = "carld";
    static final String FORGET = "forget";
    static final String LIGHTON = "lightOn";
    static final String STATIONLD = "stationld";

    BearSQLiteHelper (Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        String sqlCar = "create table " + CARS_TBL + " (" + _ID +
                " integer primary key autoincrement," +
                NAME + " text not null," +
                SELECTED + " integer not null," +
                MODEL + " integer," +
                UUID + " integer);";
        db.execSQL(sqlCar);
        String sqlRecords = "create table " + RECORDS_TBL + " (" + RID +
                " integer primary key autoincrement," +
                DATE + " long," + ODOMETER + " integer," + PRICE + " double," + TYPE + " integer," + GASSUP + " integer," +
                REMARK + " integer," + CARLD + " integer," + FORGET + " integer," + LIGHTON + " integer," + STATIONLD +" integer);";
        db.execSQL(sqlRecords);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
