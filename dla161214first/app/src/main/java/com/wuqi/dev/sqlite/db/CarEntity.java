package com.wuqi.dev.sqlite.db;

/**
 * Created by Risky57 on 2017/4/13.
 */

public class CarEntity {

    private final int _id;
    private String name;
    private int selected;
    private int model;
    private String uuid;

    public CarEntity (int _id) {
        this._id = _id;
    }

    @Override
    public String toString () {
        return "CarEntity{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", selected=" + selected +
                '}';
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
