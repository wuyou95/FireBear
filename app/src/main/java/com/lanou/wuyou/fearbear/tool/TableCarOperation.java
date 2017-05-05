package com.lanou.wuyou.fearbear.tool;

import com.lanou.wuyou.fearbear.entity.CarEntity;

import java.util.List;

/**
 * Created by Risky57 on 2017/4/13.
 */

public interface TableCarOperation {

    void addCar(CarEntity car);
    void removeCar(int id);
    void updateCar(CarEntity car);
    List<CarEntity> queryCars();
    CarEntity querySelectedCar();
    void changeSelectedCar(int id);
    void changeSelectedCar(CarEntity newSelectedCar);

}
