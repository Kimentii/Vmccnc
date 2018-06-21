package com.kimentii.vmccnc.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kimentii.vmccnc.AdapterGenerator;
import com.kimentii.vmccnc.R;

public class TubeActivity extends AppCompatActivity {
    private static final String EXTRA_ADAPTER_GENERATOR = "com.kimentii.vmccnc.activities.TubeActivity.extra.ADAPTER_GENERATOR";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tube);
        // TODO realize
    }


    public static Intent getStartIntent(Context context, AdapterGenerator adapterGenerator) {
        Intent intent = new Intent(context, TubeActivity.class);
        intent.putExtra(EXTRA_ADAPTER_GENERATOR, adapterGenerator);
        return intent;
    }
}
