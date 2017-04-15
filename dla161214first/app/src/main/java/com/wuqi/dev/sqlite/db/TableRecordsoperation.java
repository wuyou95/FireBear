package com.wuqi.dev.sqlite.db;

import java.util.List;

/**
 * Created by dllo on 17/4/13.
 */

public interface TableRecordsoperation {
    void addRecords(RecordsEntity recordsEntity);
    void removeRecords(int id);
    void updateRecords(RecordsEntity recordsEntity);
    List<RecordsEntity> querySelectedRecords();
    List<RecordsEntity> querySelectedYearRecords();
    List<RecordsEntity> querySelectedHalfTearRecords();
    List<RecordsEntity> querySelectedThreeMonthsRecords();
    List<MoneyEntity> queryMonthMoney();
    List<MoneyEntity> queryYearMoney();
}
