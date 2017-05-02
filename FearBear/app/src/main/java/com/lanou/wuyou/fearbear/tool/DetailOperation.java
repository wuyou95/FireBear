package com.lanou.wuyou.fearbear.tool;

import com.lanou.wuyou.fearbear.entity.CarDetailEntity;

import java.util.List;

/**
 * Created by dllo on 17/4/26.
 */

public interface DetailOperation {
//    void addCar(CarEntity car);
//    void removeCar(int id);
//    void updateCar(CarEntity car);
//    List<CarEntity> queryCars();
//    CarEntity querySelectedCar();
//    void addCarDetail()
    void addDetail(CarDetailEntity detail);
    List<CarDetailEntity> queryStails();

}
