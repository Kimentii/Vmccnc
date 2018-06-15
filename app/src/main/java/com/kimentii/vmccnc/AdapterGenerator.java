package com.kimentii.vmccnc;

import android.support.v7.widget.RecyclerView;

public interface AdapterGenerator {

    RecyclerView.Adapter<RecyclerView.ViewHolder> getListAdapter();

    RecyclerView.Adapter<RecyclerView.ViewHolder> getGridAdapter();
}
