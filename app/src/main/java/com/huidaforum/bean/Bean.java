package com.huidaforum.bean;

/**
 * Created by gui on 2017/9/2.
 */

public class Bean {
    private  String name;

    public int getTupian() {
        return tupian;
    }

    public void setTupian(int tupian) {
        this.tupian = tupian;
    }

    private int tupian;
    public Bean(String name) {
        this.name = name;
    }
    public Bean(String name,int tupian) {
        this.name = name;
        this.tupian=tupian;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
