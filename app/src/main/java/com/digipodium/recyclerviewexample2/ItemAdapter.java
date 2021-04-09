package com.digipodium.recyclerviewexample2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private LinkedList<ItemModel> mDatalist;
    private LayoutInflater inflater;

    public ItemAdapter(Context c, LinkedList<ItemModel> mDatalist) {
        this.mDatalist = mDatalist;
        inflater = LayoutInflater.from(c);
    }

    @NonNull
    @Override
    public ItemAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = inflater.inflate(R.layout.data_item, parent, false);
        return new ItemHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemHolder holder, int position) {
        ItemModel model = mDatalist.get(position);
        holder.icon.setImageResource(model.icon);
        holder.datatext.setText(model.text);
    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView datatext;
        ItemAdapter adapter;

        public ItemHolder(@NonNull View itemView, ItemAdapter adapter) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            datatext = itemView.findViewById(R.id.datatext);
            this.adapter = adapter;
            itemView.setOnClickListener(view -> {
                int pos = getLayoutPosition();
                ItemModel model = mDatalist.get(pos);
                model.text = ">" + model.text;
                mDatalist.set(pos, model);
                adapter.notifyDataSetChanged();
            });
        }
    }
}
