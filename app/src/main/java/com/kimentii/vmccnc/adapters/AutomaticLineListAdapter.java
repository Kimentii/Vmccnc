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
import com.kimentii.vmccnc.activities.AutomaticLineActivity;
import com.kimentii.vmccnc.dto.AutomaticLine;

import java.util.List;

public class AutomaticLineListAdapter extends ItemAdapter<AutomaticLine> {
    public static final String TAG = AutomaticLineGridAdapter.class.getSimpleName();

    private List<AutomaticLine> mAutomaticLines;
    private Context mContext;

    public AutomaticLineListAdapter(Context context, List<AutomaticLine> items) {
        this.mContext = context;
        this.mAutomaticLines = items;
    }

    @Override
    public AutomaticLineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater
                .inflate(R.layout.item_list_machine, parent, false);
        return new AutomaticLineHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder<AutomaticLine> holder, int position) {
        AutomaticLine automaticLine = mAutomaticLines.get(position);
        holder.bindItem(automaticLine, position);
    }

    @Override
    public int getItemCount() {
        return mAutomaticLines == null ? 0 : mAutomaticLines.size();
    }

    @Override
    public void setItems(List<AutomaticLine> items) {
        this.mAutomaticLines = items;
    }

    class AutomaticLineHolder extends ItemHolder<AutomaticLine> implements View.OnClickListener {
        private AutomaticLine mLine;
        private TextView mNameTextView;
        private TextView mIdTextView;
        private ImageView mPhotoImageView;

        public AutomaticLineHolder(View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.tv_name);
            mIdTextView = itemView.findViewById(R.id.tv_id);
            mPhotoImageView = itemView.findViewById(R.id.iv_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void bindItem(AutomaticLine item, int position) {
            this.mLine = item;
            Log.d(TAG, "showing list.");
            mNameTextView.setText(item.getCNC_en());
            ImageStorage.setImageFromUrlToImageView(mPhotoImageView,
                    AutomaticLine.IMAGE_FOLDER, item.getPhoto1());
            mIdTextView.setText(item.getId());
        }

        @Override
        public void onClick(View view) {
            Intent intent = AutomaticLineActivity.getStartIntent(mContext, mLine);
            mContext.startActivity(intent);
        }
    }
}