package com.palmwifi.databindingdemo;

import android.databinding.BaseObservable;

/**
 * Created by Kiki on 2017/4/28.
 */

public class TestBean  extends BaseObservable {
    public String name;
    public String password;

    public TestBean() {
    }

    public TestBean(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
