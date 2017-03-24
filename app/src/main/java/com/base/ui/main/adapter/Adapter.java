package com.base.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.R;

import java.util.ArrayList;

/**
 * Created by Phong on 2/2/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> lists;

    public Adapter(Context context, ArrayList<String> lists) {
        this.context = context;
        this.lists = lists;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.view_content_main, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
