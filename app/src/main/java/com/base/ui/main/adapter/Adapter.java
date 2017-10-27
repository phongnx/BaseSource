package com.base.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Phong on 2/2/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<String> lists;

    public Adapter(Context context, ArrayList<String> lists) {
        this.context = context;
        this.lists = lists;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_content_main, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
