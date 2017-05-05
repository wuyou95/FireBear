package com.lanou.wuyou.fearbear;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dllo on 17/4/17.
 */

public class MainAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;
    private String[] titles = {"油耗","排名","油费","费用"};
    private int[] images = {
            R.mipmap.line_chart,
            R.mipmap.cup,
            R.mipmap.oil_cast,
            R.mipmap.oil_total_cast,
    };

    public MainAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
    public View getTabView(int position, Context context){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View tab = layoutInflater.inflate(R.layout.view_main_tab,null);
        TextView textView = (TextView) tab.findViewById(R.id.tab_tv);
        textView.setText(titles[position]);
        ImageView imageView = (ImageView) tab.findViewById(R.id.tab_iv);
        imageView.setImageResource(images[position]);
        return tab;
    }
}
