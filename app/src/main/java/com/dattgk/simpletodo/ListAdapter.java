package com.dattgk.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<TodoList> {

    public ListAdapter(Context context, int resource, List<TodoList> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.activity_line_todo_list, null);
        }

        TodoList p = getItem(pos);

        if (p != null) {

            TextView tv1 = (TextView)v.findViewById(R.id.tvName);
            tv1.setText(p.name);
            TextView tv2 = (TextView)v.findViewById(R.id.tvPriority);
            tv2.setText(p.priority);
            TextView tv3 = (TextView)v.findViewById(R.id.tvDate);
            tv3.setText(p.date);
        }

        return v;
    }

}
