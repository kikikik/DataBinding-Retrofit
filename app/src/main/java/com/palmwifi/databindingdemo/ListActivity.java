package com.palmwifi.databindingdemo;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class ListActivity extends Activity {

    private ListAdapter listAdapter;
    private SwipeRefreshLayout refresh;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        listAdapter = new ListAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(listAdapter);
        loadData(true);

        refresh.setOnRefreshListener(() -> loadData(false));
    }

    private void loadData(boolean isFrist) {
        if (isFrist) {
            page = 0;
        } else {
            page++;
        }
        HttpUtils.getInstance(this).getData(page, new Subscriber<List<List<ItemBeanOrigin>>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(ListActivity.this, "请检查网络...", Toast.LENGTH_SHORT).show();
                refresh.setRefreshing(false);
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onNext(final List<List<ItemBeanOrigin>> lists) {
                runOnUiThread(() -> {
                    List<ItemBean> result = new ArrayList<>();
                    if (result != null) {
                        for (int i = 0; i < lists.size(); i++) {
                            if (lists.get(i) != null) {
                                for (int j = 0; j < lists.get(i).size(); j++) {
                                    if (lists.get(i).get(j) != null) {
                                        ItemBean itemBean = new ItemBean();
                                        itemBean.setItemOrigin(lists.get(i).get(j));
                                        result.add(itemBean);
                                    }
                                }
                            }
                        }
//                        lists.stream().filter(a -> a != null).forEach(a -> {
//                            a.forEach(b -> {
//                                ItemBean itemBean = new ItemBean();
//                                itemBean.setItemOrigin(b);
//                                result.add(itemBean);
//                            });
//                        });
                    }
                    listAdapter.setNewData(result);
                    refresh.setRefreshing(false);
                });
            }
        });
    }
}