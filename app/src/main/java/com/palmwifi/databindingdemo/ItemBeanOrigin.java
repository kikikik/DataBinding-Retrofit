package com.palmwifi.databindingdemo;

import android.databinding.BaseObservable;

import java.io.Serializable;

/**
 * 用于Retrofit
 * Created by Kiki on 2017/5/17.
 */

public class ItemBeanOrigin extends BaseObservable implements Serializable, IRecyclerItem {
    private int nid;
    private String vcimgurl;
    private String vcname;
    private String vcsubhead;
    private String vcrating;
    private String vcresurl;
    private String vcorigin;
    private int itemType = ListAdapter.VIDEO;
    private int spanSize = 2;

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getVcimgurl() {
        return vcimgurl;
    }

    public void setVcimgurl(String vcimgurl) {
        this.vcimgurl = vcimgurl;
    }

    public String getVcname() {
        return vcname;
    }

    public void setVcname(String vcname) {
        this.vcname = vcname;
    }

    public String getVcsubhead() {
        return vcsubhead;
    }

    public void setVcsubhead(String vcsubhead) {
        this.vcsubhead = vcsubhead;
    }

    public String getVcrating() {
        return vcrating;
    }

    public void setVcrating(String vcrating) {
        this.vcrating = vcrating;
    }

    public String getVcresurl() {
        return vcresurl;
    }

    public void setVcresurl(String vcresurl) {
        this.vcresurl = vcresurl;
    }

    public String getVcorigin() {
        return vcorigin;
    }

    public void setVcorigin(String vcorigin) {
        this.vcorigin = vcorigin;
    }


    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    @Override
    public int getSpanSize() {
        return spanSize;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
