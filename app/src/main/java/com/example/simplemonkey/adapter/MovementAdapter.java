package com.example.simplemonkey.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.simplemonkey.R;
import com.example.simplemonkey.model.Movement;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MovementAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Movement> movements;

    public MovementAdapter(Context context, ArrayList<Movement> movements) {
        this.context = context;
        this.movements = movements;
    }

    @Override
    public int getCount() {
        return movements.size();
    }

    @Override
    public Object getItem(int position) {
        return movements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = View.inflate(context, R.layout.list_item_movement, null);
        }

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvAmount = convertView.findViewById(R.id.tvAmount);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        TextView tvCategory = convertView.findViewById(R.id.tvCategory);
        TextView tvType = convertView.findViewById(R.id.tvType);

        Movement movement = movements.get(position);
        tvName.setText(movement.getName());
        tvAmount.setText(NumberFormat.getInstance().format(movement.getAmount()) + " " + movement.getCurrency());
        tvType.setTextColor(movement.isIncome() ? context.getColor(R.color.colorSuccess) : context.getColor(R.color.colorDanger));
        tvDate.setText(movement.getDateToString());
        tvCategory.setText(movement.getCategory().getName());
        tvCategory.setTextColor(Color.parseColor(movement.getCategory().getColor()));
        tvType.setText(movement.isIncome() ? context.getText(R.string.income) : context.getText(R.string.expense));

        Log.d("ADAPTER", movement.getName() + movement.isIncome());

        return convertView;
    }
}
