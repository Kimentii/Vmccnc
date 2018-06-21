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
import com.kimentii.vmccnc.activities.TubeActivity;
import com.kimentii.vmccnc.dto.Tube;

import java.util.List;

public class TubeListAdapter extends ItemAdapter<Tube> {
    public static final String TAG = TubeGridAdapter.class.getSimpleName();

    private List<Tube> mTubes;
    private Context mContext;

    public TubeListAdapter(Context context, List<Tube> items) {
        this.mContext = context;
        this.mTubes = items;
    }

    @Override
    public TubeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater
                .inflate(R.layout.item_list_machine, parent, false);
        return new TubeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder<Tube> holder, int position) {
        Tube Tube = mTubes.get(position);
        holder.bindItem(Tube, position);
    }

    @Override
    public int getItemCount() {
        return mTubes == null ? 0 : mTubes.size();
    }

    @Override
    public void setItems(List<Tube> items) {
        this.mTubes = items;
    }

    class TubeHolder extends ItemHolder<Tube> implements View.OnClickListener {
        private Tube mTube;
        private TextView mNameTextView;
        private TextView mIdTextView;
        private ImageView mPhotoImageView;

        public TubeHolder(View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.tv_name);
            mIdTextView = itemView.findViewById(R.id.tv_id);
            mPhotoImageView = itemView.findViewById(R.id.iv_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void bindItem(Tube item, int position) {
            this.mTube = item;
            Log.d(TAG, "showing list.");
            mNameTextView.setText(item.getFullSystemCNC());
            ImageStorage.setImageFromUrlToImageView(mPhotoImageView, Tube.IMAGE_FOLDER, item.getPhoto1());
            //mPhotoImageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.machine1));
            mIdTextView.setText(item.getDetail());
        }

        @Override
        public void onClick(View view) {
            Intent intent = TubeActivity.getStartIntent(mContext, mTube);
            mContext.startActivity(intent);
        }
    }
}