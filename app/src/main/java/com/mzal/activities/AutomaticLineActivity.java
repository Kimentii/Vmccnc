package com.mzal.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.view.ActionMode;

import com.mzal.AdapterGenerator;
import com.mzal.ImageStorage;
import com.mzal.ItemStorage;
import com.mzal.R;
import com.mzal.dto.AutomaticLine;

public class AutomaticLineActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = AutomaticLineActivity.class.getSimpleName();
    private static final String EXTRA_ADAPTER_GENERATOR = "com.kimentii.vmccnc.activities.AutomaticLineActivity.extra.ADAPTER_GENERATOR";

    private ImageView mPhotoImageView;
    private TextView mTypeTextView;
    private TextView mManufacturerTextView;
    private TextView mCountryTextView;
    private TextView mCncTextView;
    private TextView mCncFullTextView;
    private TextView mWorkpieceTextView;
    private TextView mWorkpieceWeightTextView;
    private TextView mSizeTextView;

    private AutomaticLine mAutomaticLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatic_line);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        AdapterGenerator<AutomaticLine> adapterGenerator = (AdapterGenerator) intent.getExtras()
                .getSerializable(EXTRA_ADAPTER_GENERATOR);
        mAutomaticLine = (AutomaticLine) adapterGenerator;

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
                mAutomaticLine.getPhoto1());
        mTypeTextView.setText(mAutomaticLine.getType_en());
        mManufacturerTextView.setText(mAutomaticLine.getManufacturer_en());
        mCountryTextView.setText(mAutomaticLine.getCountry_en());
        mCncTextView.setText(mAutomaticLine.getCNC_en());
        mCncFullTextView.setText(mAutomaticLine.getCNC_full_en());
        mWorkpieceTextView.setText(mAutomaticLine.getWorkpiece_en());
        mWorkpieceWeightTextView.setText(mAutomaticLine.getWorkpiece_weight());
        mSizeTextView.setText(String.format(getString(R.string.two_dimensional_size), String.valueOf(mAutomaticLine.getLine_width()),
                String.valueOf(mAutomaticLine.getLine_hight())));

        Button addToCartButton = findViewById(R.id.button_add_to_cart);
        addToCartButton.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        ItemStorage.addToCart(mAutomaticLine);
        Toast.makeText(this, "Added to cart.", Toast.LENGTH_SHORT).show();
    }

    public static Intent getStartIntent(Context context, AdapterGenerator adapterGenerator) {
        Intent intent = new Intent(context, AutomaticLineActivity.class);
        intent.putExtra(EXTRA_ADAPTER_GENERATOR, adapterGenerator);
        return intent;
    }
}
