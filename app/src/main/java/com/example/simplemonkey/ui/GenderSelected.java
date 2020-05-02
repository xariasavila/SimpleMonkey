package com.example.simplemonkey.ui;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simplemonkey.R;

public class GenderSelected extends BaseAdapter {
    Context context;
    int gender[];
    String[] genderString;
    LayoutInflater inflter;

    public GenderSelected(Context applicationContext, int[] gender, String[] genderString) {
        this.context = applicationContext;
        this.gender = gender;
        this.genderString = genderString;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return gender.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinner_gender, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView genero = (TextView) view.findViewById(R.id.tvGender);
        icon.setImageResource(gender[i]);
        genero.setText(genderString[i]);
        return view;
    }
}