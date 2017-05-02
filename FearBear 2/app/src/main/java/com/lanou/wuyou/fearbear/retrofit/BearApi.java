package com.lanou.wuyou.fearbear.retrofit;

import com.lanou.wuyou.fearbear.entity.BrandEntity;
import com.lanou.wuyou.fearbear.entity.CarDetailEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dllo on 17/4/24.
 */

public interface BearApi {
    String BEAR_URL = "http://www.xiaoxiongyouhao.com/";

    @GET("models/pinpai.json")
    Observable<BrandEntity> getBrands();

    @GET("models/{index}/che_xi.json")
    Observable<BrandEntity> getCarSeries(@Path("index") int index);

    @GET("models/{indexBrand}/che_xing_{indexSeries}.json")
    Observable<BrandEntity> getCarType(@Path("indexBrand") int brand,
                                       @Path("indexSeries") int series);

    @GET("models/spec.php")
    Observable<CarDetailEntity> getCarDetail(@Query("cheXing") int type);
}
