package com.kimentii.vmccnc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.kimentii.vmccnc.dto.AutomaticLine;

public class AutomaticLineActivity extends AppCompatActivity {
    private static final String TAG = AutomaticLineActivity.class.getSimpleName();
    private static final String EXTRA_ADAPTER_GENERATOR = "com.kimentii.vmccnc.AutomaticLineActivity.extra.ADAPTER_GENERATOR";

    private ImageView mPhotoImageView;
    private TextView mTypeTextView;
    private TextView mManufacturerTextView;
    private TextView mCountryTextView;
    private TextView mCncTextView;
    private TextView mCncFullTextView;
    private TextView mWorkpieceTextView;
    private TextView mWorkpieceWeightTextView;
    private TextView mSizeTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatic_line);

        Intent intent = getIntent();
        AdapterGenerator<AutomaticLine> adapterGenerator = (AdapterGenerator) intent.getExtras()
                .getSerializable(EXTRA_ADAPTER_GENERATOR);
        AutomaticLine automaticLine = (AutomaticLine) adapterGenerator;

        mPhotoImageView = findViewById(R.id.iv_photo);
        mTypeTextView = findViewById(R.id.tv_type);
        mManufacturerTextView = findViewById(R.id.tv_manufacturer);
        mCountryTextView = findViewById(R.id.tv_country);
        mCncTextView = findViewById(R.id.tv_cnc);
        mCncFullTextView = findViewById(R.id.tv_cnc_full);
        mWorkpieceTextView = findViewById(R.id.tv_workpiece);
        mWorkpieceWeightTextView = findViewById(R.id.tv_workpiece_weight);
        mSizeTextView = findViewById(R.id.tv_size);

        ImageStorage.setImageFromUrlToImageView(mPhotoImageView, AutomaticLine.IMAGE_FOLDER,
                automaticLine.getPhoto1());
        mTypeTextView.setText(automaticLine.getType_en());
        mManufacturerTextView.setText(automaticLine.getManufacturer_en());
        mCountryTextView.setText(automaticLine.getCountry_en());
        mCncTextView.setText(automaticLine.getCNC_en());
        mCncFullTextView.setText(automaticLine.getCNC_full_en());
        mWorkpieceTextView.setText(automaticLine.getWorkpiece_en());
        mWorkpieceWeightTextView.setText(automaticLine.getWorkpiece_weight());
        mSizeTextView.setText(String.format(getString(R.string.two_dimensional_size), String.valueOf(automaticLine.getLine_width()),
                String.valueOf(automaticLine.getLine_hight())));

    }

    public static Intent getStartIntent(Context context, AdapterGenerator adapterGenerator) {
        Intent intent = new Intent(context, AutomaticLineActivity.class);
        intent.putExtra(EXTRA_ADAPTER_GENERATOR, adapterGenerator);
        return intent;
    }
}
