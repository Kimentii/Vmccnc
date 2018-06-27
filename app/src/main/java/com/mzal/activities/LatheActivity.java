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

import com.mzal.AdapterGenerator;
import com.mzal.ImageStorage;
import com.mzal.ItemStorage;
import com.mzal.R;
import com.mzal.dto.Lathe;

public class LatheActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String EXTRA_ADAPTER_GENERATOR = "com.kimentii.vmccnc.activities.LatheActivity.extra.ADAPTER_GENERATOR";

    private Lathe mLathe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lathe);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button addToCartButton = findViewById(R.id.button_add_to_cart);
        addToCartButton.setOnClickListener(this);

        AdapterGenerator<Lathe> adapterGenerator = (AdapterGenerator<Lathe>) getIntent().getExtras()
                .getSerializable(EXTRA_ADAPTER_GENERATOR);
        mLathe = (Lathe) adapterGenerator;

        TextView manufacturerTextView = findViewById(R.id.tv_manufacturer);
        TextView countryTextView = findViewById(R.id.tv_country);
        TextView cncTextView = findViewById(R.id.tv_cnc);
        TextView yearTextView = findViewById(R.id.tv_year);
        TextView locationTextView = findViewById(R.id.tv_location);
        TextView maxCuttingDiameterTextView = findViewById(R.id.tv_max_cutting_diameter);
        TextView cuttingDiameterTextView = findViewById(R.id.tv_cutting_diameter);
        TextView barCapacityTextView = findViewById(R.id.tv_bar_capacity);
        TextView xAxisTextView = findViewById(R.id.tv_x_axis);
        TextView zAxisTextView = findViewById(R.id.tv_z_axis);
        TextView spindleSpeedTextView = findViewById(R.id.tv_spindle_speed);
        TextView toolsTextView = findViewById(R.id.tv_tools);
        ImageView imageView = findViewById(R.id.iv_photo);

        // TODO replace this MZAL
        manufacturerTextView.setText("MZAL");
        countryTextView.setText(mLathe.getProducingCountry());
        cncTextView.setText(mLathe.getSystemCNC());
        yearTextView.setText(String.valueOf(mLathe.getYear1()));
        locationTextView.setText(mLathe.getMachineLocation());
        maxCuttingDiameterTextView.setText(String.valueOf(mLathe.getDiameter_max()));
        cuttingDiameterTextView.setText(String.valueOf(mLathe.getDiameter()));
        barCapacityTextView.setText(String.valueOf(mLathe.getBardiameter()));
        xAxisTextView.setText(String.valueOf(mLathe.getX()));
        zAxisTextView.setText(String.valueOf(mLathe.getZ()));
        spindleSpeedTextView.setText(String.valueOf(mLathe.getSpindleSpeed()));
        toolsTextView.setText(String.format(getString(R.string.tools_all_live), mLathe.getToolsall(), mLathe.getToolslive()));
        ImageStorage.setImageFromUrlToImageView(imageView, Lathe.IMAGE_FOLDER, mLathe.getPhoto1());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        ItemStorage.addToCart(mLathe);
        Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
    }

    public static Intent getStartIntent(Context context, AdapterGenerator adapterGenerator) {
        Intent intent = new Intent(context, LatheActivity.class);
        intent.putExtra(EXTRA_ADAPTER_GENERATOR, adapterGenerator);
        return intent;
    }
}
