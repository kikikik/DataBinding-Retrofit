package com.palmwifi.databindingdemo;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Kiki on 2017/5/17.
 */

public class ListAdapter extends BaseQuickAdapter<ItemBean, BaseViewHolder> {
    public static final int VIDEO = 1;
    private BaseUtils.Rect rect;
    private final ViewDataBinding binding;


    public ListAdapter(List<ItemBean> data, Activity activity) {
        super(R.layout.item_layout, data);
        this.rect = BaseUtils.calculateImgSize(1.3333f, 10, 2, activity);
        binding = DataBindingUtil.inflate(LayoutInflater
                .from(activity), R.layout.item_layout, getRecyclerView(), false);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemBean item) {
        binding.setVariable(com.palmwifi.databindingdemo.BR.itemBean, item);
        helper.setText(R.id.origin, item.getVcorigin());
        ImageView imageView = helper.getView(R.id.img);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();
        params.height = rect.getHeight();
        params.width = rect.getWidth();
        imageView.setLayoutParams(params);
        Glide.with(mContext).load(item.getVcimgurl()).into(imageView);
        helper.setText(R.id.title, item.getVcname())
                .setText(R.id.desc, item.getVcrating());
    }
}
