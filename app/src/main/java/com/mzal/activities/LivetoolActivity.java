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
import com.mzal.dto.Livetool;

public class LivetoolActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String EXTRA_ADAPTER_GENERATOR = "com.kimentii.vmccnc.activities.LivetoolActivity.extra.ADAPTER_GENERATOR";

    private Livetool mLivetool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livetool);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        AdapterGenerator<Livetool> adapterGenerator = (AdapterGenerator<Livetool>) getIntent()
                .getExtras().getSerializable(EXTRA_ADAPTER_GENERATOR);
        mLivetool = (Livetool) adapterGenerator;

        Button addToCartButton = findViewById(R.id.button_add_to_cart);
        addToCartButton.setOnClickListener(this);

        ImageView photoImageView = findViewById(R.id.iv_photo);
        TextView order1TextView = findViewById(R.id.tv_order1);
        TextView codeTextView = findViewById(R.id.tv_code);
        TextView useForTextView = findViewById(R.id.tv_use_for);
        TextView producerTextView = findViewById(R.id.tv_producer);
        TextView dinTextView = findViewById(R.id.tv_din);
        TextView clampingRangeTextView = findViewById(R.id.tv_clamping_range);
        TextView toolHolderTextView = findViewById(R.id.tv_tool_holder);
        TextView sTextView = findViewById(R.id.tv_s);
        TextView speedMaxTextView = findViewById(R.id.tv_speed_max);
        TextView aTextView = findViewById(R.id.tv_a);
        TextView bTextView = findViewById(R.id.tv_b);
        TextView m1TextView = findViewById(R.id.tv_m1);
        TextView coolantSupply = findViewById(R.id.tv_coolant_supply);

        ImageStorage.setImageFromUrlToImageView(photoImageView, Livetool.IMAGE_FOLDER, mLivetool.getPhotoNames().get(0));
        order1TextView.setText(mLivetool.getOrder1());
        codeTextView.setText(mLivetool.getCode());
        useForTextView.setText(mLivetool.getModel_of_machine_cnc());
        producerTextView.setText(mLivetool.getProducer());
        dinTextView.setText(mLivetool.getDin());
        clampingRangeTextView.setText(mLivetool.getClamping_range());
        toolHolderTextView.setText(mLivetool.getTool_holder());
        sTextView.setText(String.valueOf(mLivetool.getS()));
        speedMaxTextView.setText(mLivetool.getSpeed_max());
        aTextView.setText(mLivetool.getA());
        bTextView.setText(mLivetool.getB());
        m1TextView.setText(mLivetool.getM1());
        coolantSupply.setText(mLivetool.getCoolant_supply());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        ItemStorage.addToCart(mLivetool);
        Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
    }

    public static Intent getStartIntent(Context context, AdapterGenerator adapterGenerator) {
        Intent intent = new Intent(context, LivetoolActivity.class);
        intent.putExtra(EXTRA_ADAPTER_GENERATOR, adapterGenerator);
        return intent;
    }
}
