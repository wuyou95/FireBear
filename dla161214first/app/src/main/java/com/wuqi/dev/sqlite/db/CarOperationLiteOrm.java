package com.wuqi.dev.sqlite.db;

import java.util.List;

/**
 * Created by Risky57 on 2017/4/13.
 */

public class CarOperationLiteOrm implements TableCarOperation {
    @Override
    public void addCar (CarEntity car) {

    }

    @Override
    public void removeCar (int id) {

    }

    @Override
    public void updateCar (CarEntity car) {

    }

    @Override
    public List<CarEntity> queryCars () {
        return null;
    }

    @Override
    public CarEntity querySelectedCar () {
        return null;
    }

    @Override
    public void changeSelectedCar (int id) {

    }

    @Override
    public void changeSelectedCar (CarEntity newSelectedCar) {

    }
}
