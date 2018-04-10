package com.qingmang.moudle.entity;

/**
 * Created by xiejingbao on 2018/3/20.
 */

public class UtilBox {
    private int type;
    private String name;
    private int res;

    public UtilBox(int type, String name, int res) {
        this.type = type;
        this.name = name;
        this.res = res;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
