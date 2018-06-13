package com.kimentii.vmccnc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static final String TAG = MainActivity.class.getSimpleName();

    private static final String BROADCAST_FILTER = "com.kimentii.vmccnc.MainActivity";
    private static final String EXTRA_DATA_ARRAY = "com.kimentii.vmccnc.extra.DATA_ARRAY";
    private static final String EXTRA_DATA_TYPE = "com.kimentii.cmcncc.extra.DATA_TYPE";

    public static final int DATA_TYPE_AUTOMATIC_LINE = 1;
    public static final int DATA_TYPE_LATHE = 2;
    public static final int DATA_TYPE_LIVETOOL = 3;
    public static final int DATA_TYPE_TUBE = 4;

    private ViewPager mViewPager;
    private ArrayList<Machine> mMachineArrayList;
    private MainActivityBroadCastReceiver mMainActivityBroadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter(BROADCAST_FILTER);
        mMainActivityBroadCastReceiver = new MainActivityBroadCastReceiver();
        registerReceiver(mMainActivityBroadCastReceiver, intentFilter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Vertical Machining Centers");

        mViewPager = findViewById(R.id.vp_machines);
        NetworkIntentService.startActionGetAutomaticLines(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mMachineArrayList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mMachineArrayList.add(new Machine());
        }

        mViewPager.setAdapter(new GridPagerAdapter(fragmentManager, mMachineArrayList));

        TabLayout tabLayout = findViewById(R.id.tl_dots);
        tabLayout.setupWithViewPager(mViewPager, true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMainActivityBroadCastReceiver);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        final TabLayout tabLayout = findViewById(R.id.tl_dots);
        switch (id) {
            case R.id.action_change_view:
                Toolbar toolbar = findViewById(R.id.toolbar);
                if (mViewPager.getAdapter() instanceof GridPagerAdapter) {
                    mViewPager.setAdapter(new ListPagerAdapter(fragmentManager, mMachineArrayList));
                    toolbar.getMenu().findItem(R.id.action_change_view).setIcon(R.drawable.ic_view_grid);
                    tabLayout.setVisibility(View.INVISIBLE);
                } else {
                    mViewPager.setAdapter(new GridPagerAdapter(fragmentManager, mMachineArrayList));
                    toolbar.getMenu().findItem(R.id.action_change_view).setIcon(R.drawable.ic_view_list);
                    tabLayout.setVisibility(View.VISIBLE);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class GridPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Machine> mMachineArrayList;
        private int mItemsPerTab;
        private int mColumnsNum;
        private volatile int mTabsNum;

        public GridPagerAdapter(FragmentManager fm, ArrayList<Machine> machineArrayList) {
            super(fm);
            this.mMachineArrayList = machineArrayList;
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
            int rowsNum = (int) (dpHeight / 150);
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            mColumnsNum = (int) (dpWidth / 150);
            mItemsPerTab = rowsNum * mColumnsNum;
            mTabsNum = (int) Math.ceil((double) mMachineArrayList.size() / mItemsPerTab);
            Log.d(TAG, "Tabs num: " + mTabsNum);
            Log.d(TAG, "mColumnsNum: " + mColumnsNum);
        }

        @Override
        public Fragment getItem(int position) {
            ArrayList<Machine> tabMachines = new ArrayList<>();
            for (int i = position * mItemsPerTab;
                 (i < position * mItemsPerTab + mItemsPerTab) && i < mMachineArrayList.size();
                 i++) {
                tabMachines.add(mMachineArrayList.get(i));
            }
            Log.d(TAG, "getItem");
            return MachinesFragmentGrid.newInstance(tabMachines, mColumnsNum);
        }

        @Override
        public int getCount() {
            return mTabsNum;
        }
    }

    private class ListPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Machine> mMachineArrayList;

        public ListPagerAdapter(FragmentManager fm, ArrayList<Machine> machineArrayList) {
            super(fm);
            mMachineArrayList = machineArrayList;
        }

        @Override
        public Fragment getItem(int position) {
            return MachinesFragmentList.newInstance(mMachineArrayList);
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

    public static void sendDataViaBroadcastReceiver(Context context, int dataType, ArrayList<Serializable> data) {
        Intent broadcastIntent = new Intent(BROADCAST_FILTER);
        broadcastIntent.putExtra(EXTRA_DATA_TYPE, dataType);
        broadcastIntent.putExtra(EXTRA_DATA_ARRAY, data);
        context.sendBroadcast(broadcastIntent);
    }

    class MainActivityBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundleData = intent.getExtras();
            if (bundleData != null) {
                int dataType = bundleData.getInt(EXTRA_DATA_TYPE);
                ArrayList<Serializable> data = (ArrayList<Serializable>) bundleData.getSerializable(EXTRA_DATA_ARRAY);
                switch (dataType) {
                    case DATA_TYPE_AUTOMATIC_LINE:
                        for (int i = 0; i < data.size(); i++) {
                            Log.d(TAG, "onReceive: id: " + ((AutomaticLine) data.get(i)).getId());
                        }
                        break;
                    case DATA_TYPE_LATHE:
                        break;
                    case DATA_TYPE_LIVETOOL:
                        break;
                    case DATA_TYPE_TUBE:
                        break;
                }
            }

        }
    }
}
