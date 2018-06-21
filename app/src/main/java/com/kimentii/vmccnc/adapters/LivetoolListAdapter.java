package com.kimentii.vmccnc.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kimentii.vmccnc.ImageStorage;
import com.kimentii.vmccnc.ItemAdapter;
import com.kimentii.vmccnc.ItemHolder;
import com.kimentii.vmccnc.R;
import com.kimentii.vmccnc.activities.LivetoolActivity;
import com.kimentii.vmccnc.dto.Livetool;

import java.util.List;

public class LivetoolListAdapter extends ItemAdapter<Livetool> {
    public static final String TAG = LivetoolGridAdapter.class.getSimpleName();

    private List<Livetool> mLivetools;
    private Context mContext;

    public LivetoolListAdapter(Context context, List<Livetool> items) {
        this.mContext = context;
        this.mLivetools = items;
    }

    @Override
    public LivetoolHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater
                .inflate(R.layout.item_list_machine, parent, false);
        return new LivetoolHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder<Livetool> holder, int position) {
        Livetool Livetool = mLivetools.get(position);
        holder.bindItem(Livetool, position);
    }

    @Override
    public int getItemCount() {
        return mLivetools == null ? 0 : mLivetools.size();
    }

    @Override
    public void setItems(List<Livetool> items) {
        this.mLivetools = items;
    }

    class LivetoolHolder extends ItemHolder<Livetool> implements View.OnClickListener {
        private Livetool mLivetool;
        private TextView mNameTextView;
        private TextView mIdTextView;
        private ImageView mPhotoImageView;

        public LivetoolHolder(View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.tv_name);
            mIdTextView = itemView.findViewById(R.id.tv_id);
            mPhotoImageView = itemView.findViewById(R.id.iv_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void bindItem(Livetool item, int position) {
            this.mLivetool = item;
            Log.d(TAG, "showing list.");
            mNameTextView.setText(item.getModel_of_machine_cnc());
            ImageStorage.setImageFromUrlToImageView(mPhotoImageView, Livetool.IMAGE_FOLDER, item.getPhotoNames().get(0));
            mIdTextView.setText(item.getModel_of_machine_cnc());
        }

        @Override
        public void onClick(View view) {
            Intent intent = LivetoolActivity.getStartIntent(mContext, mLivetool);
            mContext.startActivity(intent);
        }
    }
}