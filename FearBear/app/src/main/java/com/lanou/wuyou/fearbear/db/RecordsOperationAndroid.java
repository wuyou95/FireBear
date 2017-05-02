package com.lanou.wuyou.fearbear.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lanou.wuyou.fearbear.entity.MoneyEntity;
import com.lanou.wuyou.fearbear.entity.RecordsEntity;
import com.lanou.wuyou.fearbear.tool.TableRecordsoperation;

import java.util.ArrayList;
import java.util.List;

import static com.lanou.wuyou.fearbear.db.BearSQLiteHelper.RID;

/**
 * Created by dllo on 17/4/13.
 */

public class RecordsOperationAndroid implements TableRecordsoperation {
    private BearSQLiteHelper helper;

    public RecordsOperationAndroid(BearSQLiteHelper helper) {
        this.helper = helper;
    }

    //    static final String DATE = "date";
//    static final String ODOMETER = "odometer";
//    static final String PRICE = "price";
//    static final String TYPE = "type";
//    static final String GASSUP = "gassup";
//    static final String REMARK = "remark";
//    static final String CARLD = "carld";
//    static final String FORGET = "forget";
//    static final String LIGHTON = "lightOn";
//    static final String STATIONLD = "stationld";
    @Override
    public void addRecords(RecordsEntity recordsEntity) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (recordsEntity.getId() != 0) {
            values.put(RID, recordsEntity.getId());
        }
        values.put(BearSQLiteHelper.DATE, recordsEntity.getDate());
        values.put(BearSQLiteHelper.ODOMETER, recordsEntity.getOdometer());
        values.put(BearSQLiteHelper.PRICE, recordsEntity.getPrice());
        values.put(BearSQLiteHelper.TYPE, recordsEntity.getType());
        values.put(BearSQLiteHelper.GASSUP, recordsEntity.getGassup());
        values.put(BearSQLiteHelper.REMARK, recordsEntity.getRemark());
        values.put(BearSQLiteHelper.CARLD, recordsEntity.getRemark());
        values.put(BearSQLiteHelper.FORGET, recordsEntity.getForget());
        values.put(BearSQLiteHelper.LIGHTON, recordsEntity.getLightOn());
        values.put(BearSQLiteHelper.STATIONLD, recordsEntity.getStationld());
        db.insert(BearSQLiteHelper.RECORDS_TBL, null, values);
        db.close();
    }

    @Override
    public void removeRecords(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String whereClause = RID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.delete(BearSQLiteHelper.RECORDS_TBL, whereClause, whereArgs);
        db.close();
    }

    @Override
    public void updateRecords(RecordsEntity recordsEntity) {
        updateRecords(openDatabase(), true, recordsEntity);
    }

    private void updateRecords(SQLiteDatabase db, boolean isClose, RecordsEntity recordsEntity) {
        ContentValues values = new ContentValues();
        values.put(BearSQLiteHelper.DATE, recordsEntity.getDate());
        values.put(BearSQLiteHelper.ODOMETER, recordsEntity.getOdometer());
        values.put(BearSQLiteHelper.PRICE, recordsEntity.getPrice());
        values.put(BearSQLiteHelper.TYPE, recordsEntity.getType());
        values.put(BearSQLiteHelper.GASSUP, recordsEntity.getGassup());
        values.put(BearSQLiteHelper.REMARK, recordsEntity.getRemark());
        values.put(BearSQLiteHelper.CARLD, recordsEntity.getCarld());
        values.put(BearSQLiteHelper.FORGET, recordsEntity.getForget());
        values.put(BearSQLiteHelper.LIGHTON, recordsEntity.getLightOn());
        values.put(BearSQLiteHelper.STATIONLD, recordsEntity.getStationld());
        String whereClause = RID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(recordsEntity.getId())};
        db.update(BearSQLiteHelper.RECORDS_TBL, values, whereClause, whereArgs);
        if (isClose) {
            closeDatebase(db);
        }
    }

    @Override
    public List<RecordsEntity> querySelectedRecords() {
        List<RecordsEntity> recordsEntities = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "SELECT A.*\n" +
                "FROM records_tbl as " +
                "" +
                ", cars_tbl AS B\n" +
                "where\n" +
                "A.carId = B.\"_id\"\n" +
                "AND\n" +
                "B.selected = 1;";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            int indexId = cursor.getColumnIndex(RID);
            int indexDate = cursor.getColumnIndex(BearSQLiteHelper.DATE);
            int indesOdometer = cursor.getColumnIndex(BearSQLiteHelper.ODOMETER);
            int indexPrice = cursor.getColumnIndex(BearSQLiteHelper.PRICE);
            int indexType = cursor.getColumnIndex(BearSQLiteHelper.TYPE);
            int indexGassup = cursor.getColumnIndex(BearSQLiteHelper.GASSUP);
            int indexRemark = cursor.getColumnIndex(BearSQLiteHelper.REMARK);
            int indexCarld = cursor.getColumnIndex(BearSQLiteHelper.CARLD);
            int indexForget = cursor.getColumnIndex(BearSQLiteHelper.FORGET);
            int indexLightOn = cursor.getColumnIndex(BearSQLiteHelper.LIGHTON);
            int indexStationld = cursor.getColumnIndex(BearSQLiteHelper.STATIONLD);
            do {
                int id = cursor.getInt(indexId);
                long date = cursor.getLong(indexDate);
                int odometer = cursor.getInt(indesOdometer);
                double price = cursor.getDouble(indexPrice);
                int type = cursor.getInt(indexType);
                int gassup = cursor.getInt(indexGassup);
                String remark = cursor.getString(indexRemark);
                int carld = cursor.getInt(indexCarld);
                int forget = cursor.getInt(indexForget);
                int lightOn = cursor.getInt(indexLightOn);
                int stationld = cursor.getInt(indexStationld);
                RecordsEntity record = new RecordsEntity(id);
                record.setDate(date);
                record.setCarld(carld);
                record.setForget(forget);
                record.setGassup(gassup);
                record.setLightOn(lightOn);
                record.setOdometer(odometer);
                record.setPrice(price);
                record.setRemark(remark);
                record.setStationld(stationld);
                record.setType(type);
                recordsEntities.add(record);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return recordsEntities;
    }

    @Override
    public List<RecordsEntity> querySelectedYearRecords() {
        List<RecordsEntity> recordsEntities = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "SELECT A.date, A.odometer, A.price, A.yuan, A.carId, datetime(A.date / 1000, 'unixepoch')\n" +
               "from records_tbl as A, cars_tbl as B\n" +
               "WHERE A.date > (\n" +
               "    strftime('%s', datetime('now', '-12 month')) * 1000\n" +
               ")\n" +
               "AND\n" +
               "A.carId = B.\"_id\"\n" +
               "AND\n" +
               "B.selected = 1;";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            int indexId = cursor.getColumnIndex(RID);
            int indexDate = cursor.getColumnIndex(BearSQLiteHelper.DATE);
            int indesOdometer = cursor.getColumnIndex(BearSQLiteHelper.ODOMETER);
            int indexPrice = cursor.getColumnIndex(BearSQLiteHelper.PRICE);
            int indexType = cursor.getColumnIndex(BearSQLiteHelper.TYPE);
            int indexGassup = cursor.getColumnIndex(BearSQLiteHelper.GASSUP);
            int indexRemark = cursor.getColumnIndex(BearSQLiteHelper.REMARK);
            int indexCarld = cursor.getColumnIndex(BearSQLiteHelper.CARLD);
            int indexForget = cursor.getColumnIndex(BearSQLiteHelper.FORGET);
            int indexLightOn = cursor.getColumnIndex(BearSQLiteHelper.LIGHTON);
            int indexStationld = cursor.getColumnIndex(BearSQLiteHelper.STATIONLD);
            do {
                int id = cursor.getInt(indexId);
                long date = cursor.getLong(indexDate);
                int odometer = cursor.getInt(indesOdometer);
                double price = cursor.getDouble(indexPrice);
                int type = cursor.getInt(indexType);
                int gassup = cursor.getInt(indexGassup);
                String remark = cursor.getString(indexRemark);
                int carld = cursor.getInt(indexCarld);
                int forget = cursor.getInt(indexForget);
                int lightOn = cursor.getInt(indexLightOn);
                int stationld = cursor.getInt(indexStationld);
                RecordsEntity record = new RecordsEntity(id);
                record.setDate(date);
                record.setCarld(carld);
                record.setForget(forget);
                record.setGassup(gassup);
                record.setLightOn(lightOn);
                record.setOdometer(odometer);
                record.setPrice(price);
                record.setRemark(remark);
                record.setStationld(stationld);
                record.setType(type);
                recordsEntities.add(record);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return null;
    }

    @Override
    public List<RecordsEntity> querySelectedHalfTearRecords() {
        List<RecordsEntity> recordsEntities = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "SELECT A.date, A.odometer, A.price, A.yuan, A.carId, datetime(A.date / 1000, 'unixepoch')\n" +
                "from records_tbl as A, cars_tbl as B\n" +
                "WHERE A.date > (\n" +
                "    strftime('%s', datetime('now', '-6 month')) * 1000\n" +
                ")\n" +
                "AND\n" +
                "A.carId = B.\"_id\"\n" +
                "AND\n" +
                "B.selected = 1;";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            int indexId = cursor.getColumnIndex(RID);
            int indexDate = cursor.getColumnIndex(BearSQLiteHelper.DATE);
            int indesOdometer = cursor.getColumnIndex(BearSQLiteHelper.ODOMETER);
            int indexPrice = cursor.getColumnIndex(BearSQLiteHelper.PRICE);
            int indexType = cursor.getColumnIndex(BearSQLiteHelper.TYPE);
            int indexGassup = cursor.getColumnIndex(BearSQLiteHelper.GASSUP);
            int indexRemark = cursor.getColumnIndex(BearSQLiteHelper.REMARK);
            int indexCarld = cursor.getColumnIndex(BearSQLiteHelper.CARLD);
            int indexForget = cursor.getColumnIndex(BearSQLiteHelper.FORGET);
            int indexLightOn = cursor.getColumnIndex(BearSQLiteHelper.LIGHTON);
            int indexStationld = cursor.getColumnIndex(BearSQLiteHelper.STATIONLD);
            do {
                int id = cursor.getInt(indexId);
                long date = cursor.getLong(indexDate);
                int odometer = cursor.getInt(indesOdometer);
                double price = cursor.getDouble(indexPrice);
                int type = cursor.getInt(indexType);
                int gassup = cursor.getInt(indexGassup);
                String remark = cursor.getString(indexRemark);
                int carld = cursor.getInt(indexCarld);
                int forget = cursor.getInt(indexForget);
                int lightOn = cursor.getInt(indexLightOn);
                int stationld = cursor.getInt(indexStationld);
                RecordsEntity record = new RecordsEntity(id);
                record.setDate(date);
                record.setCarld(carld);
                record.setForget(forget);
                record.setGassup(gassup);
                record.setLightOn(lightOn);
                record.setOdometer(odometer);
                record.setPrice(price);
                record.setRemark(remark);
                record.setStationld(stationld);
                record.setType(type);
                recordsEntities.add(record);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return null;
    }

    @Override
    public List<RecordsEntity> querySelectedThreeMonthsRecords() {
        List<RecordsEntity> recordsEntities = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "SELECT A.date, A.odometer, A.price, A.yuan, A.carId, datetime(A.date / 1000, 'unixepoch')\n" +
                "from records_tbl as A, cars_tbl as B\n" +
                "WHERE A.date > (\n" +
                "    strftime('%s', datetime('now', '-3 month')) * 1000\n" +
                ")\n" +
                "AND\n" +
                "A.carId = B.\"_id\"\n" +
                "AND\n" +
                "B.selected = 1;";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            int indexId = cursor.getColumnIndex(RID);
            int indexDate = cursor.getColumnIndex(BearSQLiteHelper.DATE);
            int indesOdometer = cursor.getColumnIndex(BearSQLiteHelper.ODOMETER);
            int indexPrice = cursor.getColumnIndex(BearSQLiteHelper.PRICE);
            int indexType = cursor.getColumnIndex(BearSQLiteHelper.TYPE);
            int indexGassup = cursor.getColumnIndex(BearSQLiteHelper.GASSUP);
            int indexRemark = cursor.getColumnIndex(BearSQLiteHelper.REMARK);
            int indexCarld = cursor.getColumnIndex(BearSQLiteHelper.CARLD);
            int indexForget = cursor.getColumnIndex(BearSQLiteHelper.FORGET);
            int indexLightOn = cursor.getColumnIndex(BearSQLiteHelper.LIGHTON);
            int indexStationld = cursor.getColumnIndex(BearSQLiteHelper.STATIONLD);
            do {
                int id = cursor.getInt(indexId);
                long date = cursor.getLong(indexDate);
                int odometer = cursor.getInt(indesOdometer);
                double price = cursor.getDouble(indexPrice);
                int type = cursor.getInt(indexType);
                int gassup = cursor.getInt(indexGassup);
                String remark = cursor.getString(indexRemark);
                int carld = cursor.getInt(indexCarld);
                int forget = cursor.getInt(indexForget);
                int lightOn = cursor.getInt(indexLightOn);
                int stationld = cursor.getInt(indexStationld);
                RecordsEntity record = new RecordsEntity(id);
                record.setDate(date);
                record.setCarld(carld);
                record.setForget(forget);
                record.setGassup(gassup);
                record.setLightOn(lightOn);
                record.setOdometer(odometer);
                record.setPrice(price);
                record.setRemark(remark);
                record.setStationld(stationld);
                record.setType(type);
                recordsEntities.add(record);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return null;
    }

    @Override
    public List<MoneyEntity> queryMonthMoney() {
        List<MoneyEntity> moneyEntities = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select" +
                " strftime('%Y-%m',datetime(A.date / 1000, 'unixepoch'),'localtime') time," +
                " sum(A.yuan) money" +
                " from records_tbl as A, cars_tbl as B" +
                " where A.carId = B._id and B.selected = 1" +
                " GROUP BY time" +
                " ORDER BY time;";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            int indexTime = cursor.getColumnIndex("time");
            int indexMoney = cursor.getColumnIndex("money");
            do {
                String time = cursor.getString(indexTime);
                float money = cursor.getFloat(indexMoney);
                MoneyEntity moneyEntity = new MoneyEntity();
                moneyEntity.setTime(time);
                moneyEntity.setMoney(money);
                moneyEntities.add(moneyEntity);

            }while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return moneyEntities;
    }

    @Override
    public List<MoneyEntity> queryYearMoney() {
        List<MoneyEntity> moneyEntities = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select" +
                " strftime('%Y',datetime(A.date / 1000, 'unixepoch'),'localtime') time," +
                " sum(A.yuan) money" +
                " from records_tbl as A, cars_tbl as B" +
                " where A.carId = B._id and B.selected = 1" +
                " GROUP BY time" +
                " ORDER BY time;";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            int indexTime = cursor.getColumnIndex("time");
            int indexMoney = cursor.getColumnIndex("money");
            do {
                String time = cursor.getString(indexTime);
                float money = cursor.getFloat(indexMoney);
                MoneyEntity moneyEntity = new MoneyEntity();
                moneyEntity.setTime(time);
                moneyEntity.setMoney(money);
                moneyEntities.add(moneyEntity);

            }while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return moneyEntities;
    }

    private SQLiteDatabase openDatabase(){
        return helper.getWritableDatabase();
    }
    private void closeDatebase(SQLiteDatabase db){
        if (db != null){
            db.close();
        }
    }
}
