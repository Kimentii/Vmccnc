package com.kimentii.vmccnc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InformationFragment extends Fragment {
    private static final String EXTRA_LAYOUT_ID = "com.kimentii.vmccnc.InformationFragment.LAYOUT_ID";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        int layoutId = arguments.getInt(EXTRA_LAYOUT_ID);
        View view = inflater.inflate(layoutId, container, false);
        return view;
    }

    public static InformationFragment newInstance(int layoutId) {
        InformationFragment fragment = new InformationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_LAYOUT_ID, layoutId);
        fragment.setArguments(bundle);
        return fragment;
    }
}
