package com.lanou.wuyou.fearbear.entity;

/**
 * Created by Risky57 on 2017/4/13.
 */

public class CarEntity {

    private  int _id;
    private String name;
    private int selected;
    private int model;
    private String uuid;

    public CarEntity () {
    }


    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_id () {
        return _id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public int getSelected () {
        return selected;
    }

    public void setSelected (int selected) {
        this.selected = selected;
    }

    public int getModel () {
        return model;
    }

    public void setModel (int model) {
        this.model = model;
    }

    public String getUuid () {
        return uuid;
    }

    public void setUuid (String uuid) {
        this.uuid = uuid;
    }
}
