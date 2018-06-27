package com.mzal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MachinesFragmentGrid extends Fragment {
    public static final String TAG = MachinesFragmentGrid.class.getSimpleName();
    private static final String EXTRA_MACHINES_ARRAY_LIST = "extra_machines_array_list";
    private static final String EXTRA_SPAN_COUNT = "extra_span_count";

    private RecyclerView mRecyclerView;
    private ItemAdapter mItemAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_machines, container, false);
        Bundle args = getArguments();
        mRecyclerView = view.findViewById(R.id.rc_machines);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                args.getInt(EXTRA_SPAN_COUNT)));
        ArrayList<AdapterGenerator> machineArrayList = (ArrayList<AdapterGenerator>) args.getSerializable(EXTRA_MACHINES_ARRAY_LIST);

        updateUI(machineArrayList);

        return view;
    }

    public static MachinesFragmentGrid newInstance(ArrayList<AdapterGenerator> machineArrayList, int spanCount) {
        MachinesFragmentGrid fragment = new MachinesFragmentGrid();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_MACHINES_ARRAY_LIST, machineArrayList);
        args.putInt(EXTRA_SPAN_COUNT, spanCount);
        fragment.setArguments(args);
        return fragment;
    }

    private void updateUI(ArrayList<AdapterGenerator> machineArrayList) {
        if (mItemAdapter == null) {
            if (machineArrayList != null && machineArrayList.size() > 0) {
                mItemAdapter = machineArrayList.get(0).getGridAdapter(getContext(), machineArrayList);
                mRecyclerView.setAdapter(mItemAdapter);
            } else {
                Log.d(TAG, "updateUI: machineArrayList is empty");
            }
        } else {
            mItemAdapter.setItems(machineArrayList);
            mItemAdapter.notifyDataSetChanged();
        }
    }

}
