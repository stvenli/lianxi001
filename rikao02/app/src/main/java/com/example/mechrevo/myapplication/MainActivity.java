package com.example.mechrevo.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.mechrevo.myapplication.adapter.MyAdapter;
import com.example.mechrevo.myapplication.base.BaseActivity;
import com.example.mechrevo.myapplication.bean.JsonBean;
import com.example.mechrevo.myapplication.util.HttpUtils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class MainActivity extends BaseActivity {

    private String url = "http://v.juhe.cn/toutiao/index?type=14&key=b6c3b1850433a790be8f9c1bef28e424";

    private ListView lv;
    private SmartRefreshLayout sma;
    private List<JsonBean.ResultBean.DataBean> list;
    private MyAdapter adapter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        sma = bindView(R.id.sma);
        lv = bindView(R.id.lv);
    }

    @Override
    protected void initData() {

        HttpUtils.httpAsynTask(url, new HttpUtils.CallBackString() {
            @Override
            public void getData(String s) {
                Gson gson = new Gson();
                JsonBean jsonBean = gson.fromJson(s, JsonBean.class);
                list = jsonBean.getResult().getData();
                adapter = new MyAdapter(MainActivity.this, list);
                lv.setAdapter(adapter);
            }
        });

    }

    @Override
    protected void bindEvent() {
        sma.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                HttpUtils.httpAsynTask(url, new HttpUtils.CallBackString() {
                    @Override
                    public void getData(String s) {
                        Gson gson = new Gson();
                        JsonBean jsonBean = gson.fromJson(s, JsonBean.class);
                        list = jsonBean.getResult().getData();
                        adapter = new MyAdapter(MainActivity.this,list);
                        lv.setAdapter(adapter);
                        sma.finishRefresh();
                    }
                });

            }
        });

        sma.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                HttpUtils.httpAsynTask(url, new HttpUtils.CallBackString() {
                    @Override
                    public void getData(String s) {
                        Gson gson = new Gson();
                        JsonBean jsonBean = gson.fromJson(s, JsonBean.class);
                        List<JsonBean.ResultBean.DataBean> list2 = jsonBean.getResult().getData();
                        list.addAll(list2);
                        adapter.notifyDataSetChanged();
                        sma.finishLoadMore();
                    }
                });


            }
        });




    }
}

