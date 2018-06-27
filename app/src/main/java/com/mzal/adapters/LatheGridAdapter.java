package com.mzal.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mzal.ImageStorage;
import com.mzal.ItemAdapter;
import com.mzal.ItemHolder;
import com.mzal.R;
import com.mzal.activities.AutomaticLineActivity;
import com.mzal.activities.LatheActivity;
import com.mzal.dto.Lathe;

import java.util.List;

public class LatheGridAdapter extends ItemAdapter<Lathe> {
    public static final String TAG = LatheGridAdapter.class.getSimpleName();

    private List<Lathe> mAutomaticLines;
    private Context mContext;

    public LatheGridAdapter(Context context, List<Lathe> items) {
        this.mContext = context;
        this.mAutomaticLines = items;
    }

    @Override
    public LatheHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater
                .inflate(R.layout.item_grid_machine, parent, false);
        return new LatheHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder<Lathe> holder, int position) {
        Lathe lathe = mAutomaticLines.get(position);
        holder.bindItem(lathe, position);
    }

    @Override
    public int getItemCount() {
        return mAutomaticLines == null ? 0 : mAutomaticLines.size();
    }

    @Override
    public void setItems(List<Lathe> items) {
        this.mAutomaticLines = items;
    }

    class LatheHolder extends ItemHolder<Lathe> implements View.OnClickListener {
        private Lathe mLathe;
        private TextView mNameTextView;
        private TextView mIdTextView;
        private ImageView mPhotoImageView;

        public LatheHolder(View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.tv_name);
            mIdTextView = itemView.findViewById(R.id.tv_id);
            mPhotoImageView = itemView.findViewById(R.id.iv_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void bindItem(Lathe item, int position) {
            this.mLathe = item;
            Log.d(TAG, "showing list.");
            mNameTextView.setText(item.getModel());
            ImageStorage.setImageFromUrlToImageView(mPhotoImageView, Lathe.IMAGE_FOLDER, item.getPhoto1());
            //mPhotoImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.machine1));
            mIdTextView.setText(item.getModel());
        }

        @Override
        public void onClick(View view) {
            Intent intent = LatheActivity.getStartIntent(mContext, mLathe);
            mContext.startActivity(intent);
        }
    }
}