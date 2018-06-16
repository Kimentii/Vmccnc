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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static final String TAG = MainActivity.class.getSimpleName();

    private static final String BROADCAST_FILTER = "com.kimentii.vmccnc.MainActivity";
    private static final String EXTRA_DATA_ARRAY = "com.kimentii.vmccnc.extra.DATA_ARRAY";
    private static final String EXTRA_DATA_TYPE = "com.kimentii.cmcncc.extra.DATA_TYPE";

    private ViewPager mViewPager;
    private MenuItem mSelectedMenuItem;
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
                    // TODO replace automatic lines
                    mViewPager.setAdapter(new ListPagerAdapter(fragmentManager, ItemStorage.getAutomaticLines()));
                    toolbar.getMenu().findItem(R.id.action_change_view).setIcon(R.drawable.ic_view_grid);
                    tabLayout.setVisibility(View.INVISIBLE);
                } else {
                    // TODO replace automatic lines
                    mViewPager.setAdapter(new GridPagerAdapter(fragmentManager, ItemStorage.getAutomaticLines()));
                    toolbar.getMenu().findItem(R.id.action_change_view).setIcon(R.drawable.ic_view_list);
                    tabLayout.setVisibility(View.VISIBLE);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.d(TAG, "onNavigationItemSelected: ");
        mSelectedMenuItem = item;
        ArrayList<? extends AdapterGenerator> data = getSectionData();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private ArrayList<? extends AdapterGenerator> getSectionData() {
        if (mSelectedMenuItem != null) {
            switch (mSelectedMenuItem.getItemId()) {
                case R.id.category_automatic_lines:
                    return ItemStorage.getAutomaticLines();
                case R.id.category_lathes:
                    return ItemStorage.getLathes();
                // TODO add cases
            }
        } else {
            return ItemStorage.getAutomaticLines();
        }
        return null;
    }

    private class GridPagerAdapter<T extends AdapterGenerator> extends FragmentStatePagerAdapter {

        private ArrayList<T> mMachineArrayList;
        private int mItemsPerTab;
        private int mColumnsNum;
        private volatile int mTabsNum;

        public GridPagerAdapter(FragmentManager fm, ArrayList<T> machineArrayList) {
            super(fm);
            // TODO: MainActivity has field with the same name.
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

    public static void notifyDataStorageChanged(Context context) {
        Intent broadcastIntent = new Intent(BROADCAST_FILTER);
        context.sendBroadcast(broadcastIntent);
    }

    class MainActivityBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (mViewPager.getAdapter() instanceof GridPagerAdapter) {
                // TODO replace automatic lines
                mViewPager.setAdapter(new ListPagerAdapter(fragmentManager, ItemStorage.getAutomaticLines()));
            } else {
                // TODO replace automatic lines
                mViewPager.setAdapter(new GridPagerAdapter(fragmentManager, ItemStorage.getAutomaticLines()));
            }
        }
    }
}
