package com.example.simplemonkey.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.simplemonkey.R;
import com.example.simplemonkey.model.Movement;

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

        Movement movement = movements.get(position);
        tvName.setText(movement.getName());
        tvAmount.setText(Double.toString(movement.getAmount()));
        tvDate.setText(movement.getDateToString());

        return convertView;
    }
}
