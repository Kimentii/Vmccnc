package com.mzal;

import android.support.v7.widget.RecyclerView;

import java.util.List;


public abstract class ItemAdapter<T extends AdapterGenerator> extends RecyclerView.Adapter<ItemHolder<T>> {

    public abstract void setItems(List<T> items);
}
