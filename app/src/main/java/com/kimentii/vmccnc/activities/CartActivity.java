package com.kimentii.vmccnc.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.kimentii.vmccnc.ItemStorage;
import com.kimentii.vmccnc.R;

public class CartActivity extends AppCompatActivity {

    private ListView mCartListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mCartListView = findViewById(R.id.lv_cart_items);
        mCartListView.setAdapter(new CartItemsAdapter());
    }

    class CartItemsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return ItemStorage.getCart().size();
        }

        @Override
        public Object getItem(int position) {
            return ItemStorage.getCart().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return ItemStorage.getCart().get(position).getCartView(CartActivity.this);
        }
    }
}
