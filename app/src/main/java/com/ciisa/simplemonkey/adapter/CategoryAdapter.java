package com.ciisa.simplemonkey.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ciisa.simplemonkey.R;
import com.ciisa.simplemonkey.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Category> categories;
    LayoutInflater inflater;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Category getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categories.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.spinner_item_category, null);
        Category category = categories.get(position);
        TextView tvCategory = convertView.findViewById(R.id.tvCategory);
        tvCategory.setText(category.getName());
        tvCategory.setTextColor(Color.parseColor(category.getColor()));

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.spinner_item_category, null);
        Category category = categories.get(position);
        TextView tvCategory = convertView.findViewById(R.id.tvCategory);
        tvCategory.setText(category.getName());
        tvCategory.setTextColor(Color.parseColor(category.getColor()));


        return convertView;
    }
}
