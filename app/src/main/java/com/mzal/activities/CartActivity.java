package com.mzal.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.mzal.AdapterGenerator;
import com.mzal.ItemStorage;
import com.mzal.R;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = CartActivity.class.getSimpleName();

    private ListView mCartListView;
    private Toolbar mToolbar;
    private ActionMode.Callback mActionModeCallback;
    private ArrayList<AdapterGenerator> mSelectedItems = new ArrayList<>();
    private boolean mIsActionMode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mCartListView = findViewById(R.id.lv_cart_items);
        mCartListView.setAdapter(new CartItemsAdapter());
        mActionModeCallback = new ToolbarActionModeCallback();
//        mMultiChoiceModeListener = new AbsListView.MultiChoiceModeListener() {
//            private ArrayList<AdapterGenerator> selectedItems = new ArrayList<>();
//
//            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//                mode.getMenuInflater().inflate(R.menu.toolbar_cart_action_mode, menu);
//                return true;
//            }
//
//            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//                return false;
//            }
//
//            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//                int id = item.getItemId();
//                switch (id) {
//                    case R.id.action_delete_selected:
//                        Log.d(TAG, "onActionItemClicked: action delete");
//                        ItemStorage.getCartItems().removeAll(selectedItems);
//                        ((BaseAdapter) mCartListView.getAdapter()).notifyDataSetChanged();
//                        mode.finish();
//                        break;
//                }
//                return false;
//            }
//
//            public void onDestroyActionMode(ActionMode mode) {
//            }
//
//            public void onItemCheckedStateChanged(ActionMode mode,
//                                                  int position, long id, boolean checked) {
//                if (checked) {
//                    selectedItems.add((AdapterGenerator) mCartListView.getAdapter().getItem(position));
//                } else {
//                    selectedItems.remove((AdapterGenerator) mCartListView.getAdapter().getItem(position));
//                }
//                Log.d(TAG, "onItemCheckedStateChanged: selected items size: " + selectedItems.size());
//            }
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_enable_edit_mode:
                startSupportActionMode(mActionModeCallback);
                mIsActionMode = true;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AdapterGenerator clickedItem = (AdapterGenerator) parent.getItemAtPosition(position);
        if (!isSelectedItem(clickedItem)) {
            mSelectedItems.add(clickedItem);
            highlightView(view);
        } else {
            mSelectedItems.remove(clickedItem);
            unhighlightView(view);
        }

    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, CartActivity.class);
        return intent;
    }

    private void highlightView(View view) {
        view.setBackgroundColor(getResources().getColor(R.color.selected_list_item));
    }

    private void unhighlightView(View view) {
        view.setBackgroundColor(getResources().getColor(R.color.not_selected_list_item));
    }

    private boolean isSelectedItem(AdapterGenerator item) {
        return (mSelectedItems.indexOf(item) != -1);
    }

    class ToolbarActionModeCallback implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.toolbar_cart_action_mode, menu);
            mCartListView.setOnItemClickListener(CartActivity.this);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.action_delete_selected:
                    ItemStorage.getCartItems().removeAll(mSelectedItems);
                    mode.finish();
                    mIsActionMode = false;
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            ((BaseAdapter) mCartListView.getAdapter()).notifyDataSetChanged();
            mSelectedItems = new ArrayList<>();
            mCartListView.setOnItemClickListener(null);
        }
    }

    class CartItemsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return ItemStorage.getCartItems().size();
        }

        @Override
        public Object getItem(int position) {
            return ItemStorage.getCartItems().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            AdapterGenerator adapterGenerator = ItemStorage.getCartItems().get(position);
            View view = adapterGenerator.getCartView(CartActivity.this);
            Log.d(TAG, "getView: hasExpandedActionView: " + mToolbar.hasExpandedActionView());
            if (mIsActionMode && isSelectedItem(adapterGenerator)) {
                highlightView(view);
            }
            return view;
        }
    }
}