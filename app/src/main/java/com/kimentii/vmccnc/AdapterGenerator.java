package com.kimentii.vmccnc;

import android.content.Context;

import java.io.Serializable;
import java.util.List;

public interface AdapterGenerator<T extends AdapterGenerator> extends Serializable {

    ItemAdapter<T> getListAdapter(Context context, List<T> items);

    ItemAdapter<T> getGridAdapter(Context context, List<T> items);
}
