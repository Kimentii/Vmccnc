package com.kimentii.vmccnc.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.kimentii.vmccnc.AdapterGenerator;
import com.kimentii.vmccnc.InformationFragment;
import com.kimentii.vmccnc.ItemStorage;
import com.kimentii.vmccnc.MachinesFragmentGrid;
import com.kimentii.vmccnc.MachinesFragmentList;
import com.kimentii.vmccnc.NetworkIntentService;
import com.kimentii.vmccnc.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static final String TAG = MainActivity.class.getSimpleName();

    private static final String BROADCAST_FILTER = "com.kimentii.vmccnc.activities.MainActivity";

    private ViewPager mViewPager;
    private MenuItem mSelectedMenuItem;
    private boolean areClicksEnabled;
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

        mViewPager = findViewById(R.id.vp_machines);

        NetworkIntentService.startActionUpdateData(this);
        disableEventListeners();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new SingleFragmentPagerAdapter(getSupportFragmentManager(),
                InformationFragment.newInstance(R.layout.fragment_loading)));

        TabLayout tabLayout = findViewById(R.id.tl_dots);
        tabLayout.setupWithViewPager(mViewPager, true);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
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
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        if (areClicksEnabled) {
            int id = item.getItemId();
            FragmentManager fragmentManager = getSupportFragmentManager();
            final TabLayout tabLayout = findViewById(R.id.tl_dots);
            switch (id) {
                case R.id.action_change_view:
                    Toolbar toolbar = findViewById(R.id.toolbar);
                    ArrayList<? extends AdapterGenerator> data = getSectionData();
                    if (mViewPager.getAdapter() instanceof GridPagerAdapter) {
                        mViewPager.setAdapter(new ListPagerAdapter(fragmentManager, data));
                        toolbar.getMenu().findItem(R.id.action_change_view).setIcon(R.drawable.ic_view_grid);
                        tabLayout.setVisibility(View.INVISIBLE);
                    } else {
                        mViewPager.setAdapter(new GridPagerAdapter(fragmentManager, data));
                        toolbar.getMenu().findItem(R.id.action_change_view).setIcon(R.drawable.ic_view_list);
                        tabLayout.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.action_show_cart:
                    Intent intent = CartActivity.getStartIntent(MainActivity.this);
                    startActivity(intent);
                    break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onNavigationItemSelected: ");
        if (areClicksEnabled) {
            mSelectedMenuItem = item;

            ArrayList<? extends AdapterGenerator> data = getSectionData();
            updateSectionData(getSectionData());

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            switch (item.getItemId()) {
                case R.id.category_automatic_lines:
                    toolbar.setTitle("Automatic lines");
                    break;
                case R.id.category_lathes:
                    toolbar.setTitle("Lathes");
                    break;
                case R.id.category_live_tools:
                    toolbar.setTitle("Live tools");
                    break;
                case R.id.category_tubes:
                    toolbar.setTitle("Tubes");
                    break;
            }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    private void disableEventListeners() {
        areClicksEnabled = false;
    }

    private void enableEventListeners() {
        areClicksEnabled = true;
    }

    private ArrayList<? extends AdapterGenerator> getSectionData() {
        if (mSelectedMenuItem != null) {
            switch (mSelectedMenuItem.getItemId()) {
                case R.id.category_automatic_lines:
                    return ItemStorage.getAutomaticLines();
                case R.id.category_lathes:
                    return ItemStorage.getLathes();
                case R.id.category_live_tools:
                    return ItemStorage.getLivetools();
                case R.id.category_tubes:
                    return ItemStorage.getTubes();
            }
        } else {
            return ItemStorage.getAutomaticLines();
        }
        return null;
    }

    private <T extends AdapterGenerator> void updateSectionData(ArrayList<T> adapterGenerators) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Log.d(TAG, "updateSectionData: data size: " + adapterGenerators.size());
        if (mViewPager.getAdapter() instanceof ListPagerAdapter) {
            mViewPager.setAdapter(new ListPagerAdapter<>(fragmentManager, adapterGenerators));
        } else {
            mViewPager.setAdapter(new GridPagerAdapter<>(fragmentManager, adapterGenerators));
        }
    }

    private class GridPagerAdapter<T extends AdapterGenerator> extends FragmentStatePagerAdapter {

        private ArrayList<T> mMachineArrayList;
        private int mItemsPerTab;
        private int mColumnsNum;
        private volatile int mTabsNum;

        public GridPagerAdapter(FragmentManager fm, ArrayList<T> machineArrayList) {
            super(fm);
            this.mMachineArrayList = machineArrayList;
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
            int rowsNum = (int) (dpHeight / 150);
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            mColumnsNum = (int) (dpWidth / 150);
            mItemsPerTab = rowsNum * mColumnsNum;
            mTabsNum = (int) Math.ceil((double) machineArrayList.size() / mItemsPerTab);
            Log.d(TAG, "Tabs num: " + mTabsNum);
            Log.d(TAG, "mColumnsNum: " + mColumnsNum);
        }

        @Override
        public Fragment getItem(int position) {
            ArrayList<AdapterGenerator> tabMachines = new ArrayList<>();
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

    private class ListPagerAdapter<T extends AdapterGenerator> extends FragmentStatePagerAdapter {

        private ArrayList<T> mItemArrayList;

        public ListPagerAdapter(FragmentManager fm, ArrayList<T> itemArrayList) {
            super(fm);
            mItemArrayList = itemArrayList;
        }

        @Override
        public Fragment getItem(int position) {
            return MachinesFragmentList.newInstance((ArrayList<AdapterGenerator>) mItemArrayList);
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

    private class SingleFragmentPagerAdapter extends FragmentStatePagerAdapter {

        Fragment mFragment;

        public SingleFragmentPagerAdapter(FragmentManager fm, Fragment fragment) {
            super(fm);
            mFragment = fragment;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragment;
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

    public static void notifyDataStorageChanged(Context context) {
        Intent broadcastIntent = new Intent(BROADCAST_FILTER);
        context.sendBroadcast(broadcastIntent);
    }

    class MainActivityBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            updateSectionData(getSectionData());
            enableEventListeners();
        }
    }
}
