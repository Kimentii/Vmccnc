package com.kimentii.vmccnc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MachinesFragment extends Fragment {
    public static final String TAG = MachinesFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private MachineAdapter mMachineAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_machines, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rc_machines);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                5));
        ArrayList<Machine> machines = new ArrayList<>();
        machines.add(new Machine("machine 1"));
        machines.add(new Machine("machine 1"));
        machines.add(new Machine("machine 1"));
        machines.add(new Machine("machine 1"));
        updateUI(machines);

        return view;
    }

    public static MachinesFragment newInstance() {
        MachinesFragment fragment = new MachinesFragment();
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

        public void bindApp(Machine machine, int position) {
            this.mMachine = machine;
            Log.d(TAG, "showing list.");
            mNameTextView.setText(machine.getName());
        }

        public MachineHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNameTextView = itemView.findViewById(R.id.tv_name);
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
            holder.bindApp(machine, position);
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
