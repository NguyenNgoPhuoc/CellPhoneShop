package com.example.cellphoneshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PhoneAdapter extends BaseAdapter {
    Context context;
    List<Phone> list;

    public PhoneAdapter(Context context, List<Phone> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Phone getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.phone_item, null);
        }

        ((TextView)view.findViewById(R.id.tvCode)).setText(getItem(i).getCode());
        ((TextView)view.findViewById(R.id.tvName)).setText(getItem(i).getName());
        ((TextView)view.findViewById(R.id.tvPrice)).setText(getItem(i).getPrice()+"");

        return view;
    }
}
