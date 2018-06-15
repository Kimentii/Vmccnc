package com.kimentii.vmccnc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MachinesFragmentList extends Fragment {
    public static final String TAG = MachinesFragmentList.class.getSimpleName();
    private static final String EXTRA_MACHINES_ARRAY_LIST = "extra_machines_array_list";

    private RecyclerView mRecyclerView;
    private ItemAdapter mMachineAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_machines, container, false);
        Bundle args = getArguments();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rc_machines);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<AdapterGenerator> machineArrayList = (ArrayList<AdapterGenerator>) args.getSerializable(EXTRA_MACHINES_ARRAY_LIST);

        updateUI(machineArrayList);

        return view;
    }

    public static MachinesFragmentList newInstance(ArrayList<AdapterGenerator> machineArrayList) {
        MachinesFragmentList fragment = new MachinesFragmentList();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_MACHINES_ARRAY_LIST, machineArrayList);
        fragment.setArguments(args);
        return fragment;
    }

    private void updateUI(ArrayList<AdapterGenerator> machineArrayList) {
        if (mMachineAdapter == null) {
            if (machineArrayList != null && machineArrayList.size() > 0) {
                mMachineAdapter = machineArrayList.get(0).getListAdapter(getContext(), machineArrayList);
                mRecyclerView.setAdapter(mMachineAdapter);
            }
        } else {
            mMachineAdapter.setItems(machineArrayList);
            mMachineAdapter.notifyDataSetChanged();
        }
    }
}
