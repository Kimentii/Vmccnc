package com.kimentii.vmccnc;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ItemHolder<T extends AdapterGenerator> extends RecyclerView.ViewHolder {

    public ItemHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindMachine(T item, int position);
}
