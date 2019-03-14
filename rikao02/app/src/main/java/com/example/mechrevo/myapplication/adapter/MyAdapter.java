package com.example.mechrevo.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mechrevo.myapplication.R;
import com.example.mechrevo.myapplication.bean.JsonBean;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<JsonBean.ResultBean.DataBean> list;

    public MyAdapter(Context context, List<JsonBean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        switch (type) {
            case 0:
                ViewHolder1 holder1 = null;
                if (convertView == null) {
                    holder1 = new ViewHolder1();
                    convertView = View.inflate(context, R.layout.item, null);
                    holder1.tv11 = convertView.findViewById(R.id.tv11);
                    holder1.image11 = convertView.findViewById(R.id.image11);
                    convertView.setTag(holder1);
                } else {
                    holder1 = (ViewHolder1) convertView.getTag();
                }
                holder1.tv11.setText(list.get(position).getTitle());
                Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(holder1.image11);
                break;
            case 1:
                ViewHolder2 holder2 = null;
                if (convertView == null) {
                    holder2 = new ViewHolder2();
                    convertView = View.inflate(context, R.layout.item1, null);
                    holder2.tv21 = convertView.findViewById(R.id.tv21);
                    convertView.setTag(holder2);
                } else {
                    holder2 = (ViewHolder2) convertView.getTag();
                }
                holder2.tv21.setText(list.get(position).getTitle());
                break;

        }

        return convertView;
    }

    class ViewHolder1 {
        TextView tv11;
        ImageView image11;
    }

    class ViewHolder2 {
        TextView tv21;
    }


    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
