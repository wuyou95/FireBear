package com.lanou.wuyou.fearbear.total;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lanou.wuyou.fearbear.R;

/**
 * Created by dllo on 17/4/17.
 */

public class OilTotalCastFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fargment_oil_total_cast,null);
    }
}
