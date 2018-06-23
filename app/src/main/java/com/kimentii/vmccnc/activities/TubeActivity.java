package com.kimentii.vmccnc.activities;

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

import com.kimentii.vmccnc.AdapterGenerator;
import com.kimentii.vmccnc.ImageStorage;
import com.kimentii.vmccnc.ItemStorage;
import com.kimentii.vmccnc.R;
import com.kimentii.vmccnc.dto.Tube;

public class TubeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String EXTRA_ADAPTER_GENERATOR = "com.kimentii.vmccnc.activities.TubeActivity.extra.ADAPTER_GENERATOR";

    private Tube mTube;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tube);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        AdapterGenerator<Tube> adapterGenerator = (AdapterGenerator<Tube>) getIntent()
                .getExtras().getSerializable(EXTRA_ADAPTER_GENERATOR);
        mTube = (Tube) adapterGenerator;

        Button addToCartButton = findViewById(R.id.button_add_to_cart);
        addToCartButton.setOnClickListener(this);

        ImageView photoImageView = findViewById(R.id.iv_photo);
        TextView manufacturerTextView = findViewById(R.id.tv_manufacturer);
        TextView countryTextView = findViewById(R.id.tv_country);
        TextView cncTextView = findViewById(R.id.tv_cnc);
        TextView yearTextView = findViewById(R.id.tv_year);
        TextView locationTextView = findViewById(R.id.tv_location);

        ImageStorage.setImageFromUrlToImageView(photoImageView, Tube.IMAGE_FOLDER, mTube.getPhoto1());
        // TODO fix this MZAL
        manufacturerTextView.setText("MZAL");
        countryTextView.setText(mTube.getProducingCountry());
        cncTextView.setText(mTube.getSystemCNC());
        yearTextView.setText(String.valueOf(mTube.getYear1()));
        locationTextView.setText(mTube.getMachineLocation());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        ItemStorage.addToCart(mTube);
        Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
    }

    public static Intent getStartIntent(Context context, AdapterGenerator adapterGenerator) {
        Intent intent = new Intent(context, TubeActivity.class);
        intent.putExtra(EXTRA_ADAPTER_GENERATOR, adapterGenerator);
        return intent;
    }
}
