package com.kimentii.vmccnc;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.vp_machines);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                ArrayList<Machine> machines = new ArrayList<>();
                machines.add(new Machine("machine 1"));
                machines.add(new Machine("machine 1"));
                machines.add(new Machine("machine 1"));
                machines.add(new Machine("machine 1"));
                return MachinesFragment.newInstance(machines, MachinesFragment.LAYOUT_TYPE_GRID);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        TabLayout tabLayout = findViewById(R.id.tl_dots);
        tabLayout.setupWithViewPager(mViewPager, true);
    }
}
