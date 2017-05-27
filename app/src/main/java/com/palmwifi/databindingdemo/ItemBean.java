package com.palmwifi.databindingdemo;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import java.io.Serializable;

/**
 * Created by Kiki on 2017/5/17.
 */

public class ItemBean extends BaseObservable implements Serializable, IRecyclerItem {
    private ObservableInt nid = new ObservableInt();
    private ObservableField<String> vcimgurl = new ObservableField<>();
    private ObservableField<String> vcname = new ObservableField<>();
    private ObservableField<String> vcsubhead = new ObservableField<>();
    private ObservableField<String> vcrating = new ObservableField<>();
    private ObservableField<String> vcresurl = new ObservableField<>();
    private ObservableField<String> vcorigin = new ObservableField<>();
    private ObservableField<ItemBeanOrigin> itemOrigin = new ObservableField<>();

    private int itemType = ListAdapter.VIDEO;
    private int spanSize = 2;

    public ItemBean() {
        itemOrigin.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                ItemBeanOrigin itemBeanOrigin = itemOrigin.get();
                if (itemBeanOrigin != null) {
                    nid.set(itemBeanOrigin.getNid());
                    vcimgurl.set(itemBeanOrigin.getVcimgurl());
                    vcname.set(itemBeanOrigin.getVcname());
                    vcsubhead.set(itemBeanOrigin.getVcsubhead());
                    vcrating.set(itemBeanOrigin.getVcrating());
                    vcresurl.set(itemBeanOrigin.getVcresurl());
                    vcorigin.set(itemBeanOrigin.getVcorigin());
                }
            }
        });
    }

    public ObservableField<ItemBeanOrigin> getItemOrigin() {
        return itemOrigin;
    }

    public void setItemOrigin(ItemBeanOrigin itemOrigin) {
        this.itemOrigin.set(itemOrigin);
    }

    public ObservableInt getNid() {
        return nid;
    }

    public void setNid(ObservableInt nid) {
        this.nid = nid;
    }

    @Bindable
    public String getVcimgurl() {
        return vcimgurl.get();
    }

    public void setVcimgurl(String vcimgurl) {
        this.vcimgurl.set(vcimgurl);
    }

    @Bindable
    public String getVcname() {
        return vcname.get();
    }

    public void setVcname(String vcname) {
        this.vcname.set(vcname);
    }

    @Bindable
    public String getVcsubhead() {
        return vcsubhead.get();
    }

    public void setVcsubhead(String vcsubhead) {
        this.vcsubhead.set(vcsubhead);
    }

    @Bindable
    public String getVcrating() {
        return vcrating.get();
    }

    public void setVcrating(String vcrating) {
        this.vcrating.set(vcrating);
    }

    @Bindable
    public String getVcresurl() {
        return vcresurl.get();
    }

    public void setVcresurl(String vcresurl) {
        this.vcresurl.set(vcresurl);
    }

    @Bindable
    public String getVcorigin() {
        return vcorigin.get();
    }

    public void setVcorigin(String vcorigin) {
        this.vcorigin.set(vcorigin);
    }


    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    @Bindable
    @Override
    public int getSpanSize() {
        return spanSize;
    }

    @Bindable
    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
