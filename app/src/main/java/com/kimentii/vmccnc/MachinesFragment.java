package com.kimentii.vmccnc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MachinesFragment extends Fragment {
    public static final int LAYOUT_TYPE_GRID = 1;
    public static final int LAYOUT_TYPE_LIST = 2;
    public static final String TAG = MachinesFragment.class.getSimpleName();
    private static final String EXTRA_MACHINES_ARRAY_LIST = "extra_machines_array_list";
    private static final String EXTRA_LAYOUT_TYPE = "extra_layout_type";

    private RecyclerView mRecyclerView;
    private MachineAdapter mMachineAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_machines, container, false);
        Bundle args = getArguments();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rc_machines);
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 150);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                noOfColumns));
        ArrayList<Machine> machineArrayList = (ArrayList<Machine>) args.getSerializable(EXTRA_MACHINES_ARRAY_LIST);

        updateUI(machineArrayList);

        return view;
    }

    public static MachinesFragment newInstance(ArrayList<Machine> machineArrayList, int layoutType) {
        MachinesFragment fragment = new MachinesFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_MACHINES_ARRAY_LIST, machineArrayList);
        args.putInt(EXTRA_LAYOUT_TYPE, layoutType);
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
