package com.kimentii.vmccnc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MachinesFragmentGrid extends Fragment {
    public static final String TAG = MachinesFragmentGrid.class.getSimpleName();
    private static final String EXTRA_MACHINES_ARRAY_LIST = "extra_machines_array_list";
    private static final String EXTRA_SPAN_COUNT = "extra_span_count";

    private RecyclerView mRecyclerView;
    private MachineAdapter mMachineAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_machines, container, false);
        Bundle args = getArguments();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rc_machines);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                args.getInt(EXTRA_SPAN_COUNT)));
        ArrayList<Machine> machineArrayList = (ArrayList<Machine>) args.getSerializable(EXTRA_MACHINES_ARRAY_LIST);

        updateUI(machineArrayList);

        return view;
    }

    public static MachinesFragmentGrid newInstance(ArrayList<Machine> machineArrayList, int spanCount) {
        MachinesFragmentGrid fragment = new MachinesFragmentGrid();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_MACHINES_ARRAY_LIST, machineArrayList);
        args.putInt(EXTRA_SPAN_COUNT, spanCount);
        fragment.setArguments(args);
        return fragment;
    }

    private void updateUI(ArrayList<Machine> machineArrayList) {
        if (mMachineAdapter == null) {
            mMachineAdapter = new MachineAdapter(machineArrayList);
            mRecyclerView.setAdapter(mMachineAdapter);
        } else {
            mMachineAdapter.setApps(machineArrayList);
            mMachineAdapter.notifyDataSetChanged();
        }
    }


    private class MachineHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Machine mMachine;
        private TextView mNameTextView;
        private TextView mIdTextView;
        private ImageView mPhotoImageView;

        public void bindMachine(Machine machine, int position) {
            this.mMachine = machine;
            Log.d(TAG, "showing list.");
            mNameTextView.setText(machine.getName());
            mPhotoImageView.setImageDrawable(getActivity().getResources().getDrawable(machine.getImageId()));
            mIdTextView.setText(machine.getId());
        }

        public MachineHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNameTextView = itemView.findViewById(R.id.tv_name);
            mIdTextView = itemView.findViewById(R.id.tv_id);
            mPhotoImageView = itemView.findViewById(R.id.iv_photo);
        }

        @Override
        public void onClick(View view) {

        }
    }

    private class MachineAdapter extends RecyclerView.Adapter<MachineHolder> {
        private List<Machine> mMachineList;

        public MachineAdapter(List<Machine> mMachineList) {
            this.mMachineList = mMachineList;
        }

        @Override
        public MachineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.item_grid_machine, parent, false);
            return new MachineHolder(view);
        }

        @Override
        public void onBindViewHolder(MachineHolder holder, int position) {
            Machine machine = mMachineList.get(position);
            holder.bindMachine(machine, position);
        }

        public void setApps(List<Machine> appsList) {
            this.mMachineList = appsList;
        }

        @Override
        public int getItemCount() {
            return mMachineList.size();
        }
    }
}
