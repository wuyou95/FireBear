package com.lanou.wuyou.fearbear.oilrecords;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.lanou.wuyou.fearbear.R;
import com.lanou.wuyou.fearbear.db.RxSQLite;
import com.lanou.wuyou.fearbear.entity.RecordsEntity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dllo on 17/5/8.
 */

public class OilRecordsActivity extends AppCompatActivity{
    private OilRecordsAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil_records);
        listView = (ListView) findViewById(R.id.list_oil_records);
        adapter = new OilRecordsAdapter(this);
        RxSQLite.queryRecords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<RecordsEntity>>() {
                    @Override
                    public void accept(@NonNull List<RecordsEntity> recordsEntities) throws Exception {
                        adapter.setRecordsEntities(recordsEntities);
                        listView.setAdapter(adapter);
                    }
                });
    }
}
