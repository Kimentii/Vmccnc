package com.kimentii.vmccnc.dto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kimentii.vmccnc.AdapterGenerator;
import com.kimentii.vmccnc.ImageStorage;
import com.kimentii.vmccnc.ItemAdapter;
import com.kimentii.vmccnc.R;
import com.kimentii.vmccnc.adapters.TubeGridAdapter;
import com.kimentii.vmccnc.adapters.TubeListAdapter;

import java.io.Serializable;
import java.util.List;

public class Tube implements AdapterGenerator<Tube> {
    public static final String DATABASE_TABLE_NAME = "tube";
    public static final String IMAGE_FOLDER = DATABASE_TABLE_NAME;

    private int id;
    private String productId;
    private String type;
    private String typeurl;
    private String model;
    private String modelurl;
    private String detail;
    private String producer;
    private String producingCountry;
    private String systemCNC;
    private String fullSystemCNC;
    private int year1;
    private String condition1;
    private String machineLocation;
    private int diameter_max;
    private int diameter_min;
    private int nomberOfSpindles;
    private String dimensions_of_machine;
    private String weight_of_machine;
    private String spindletime;
    private String machineRuntime;
    private int price;
    private String photo1;
    private String photo2;
    private String photo3;
    private String photo4;
    private String photo5;
    private String descriptionen;
    private String descriptionru;
    private String video1;
    private String video2;

    @Override
    public ItemAdapter<Tube> getListAdapter(Context context, List<Tube> items) {
        return new TubeListAdapter(context, items);
    }

    @Override
    public ItemAdapter<Tube> getGridAdapter(Context context, List<Tube> items) {
        return new TubeGridAdapter(context, items);
    }

    @Override
    public View getCartView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cart, null, false);
        ImageView photo = view.findViewById(R.id.iv_photo);
        TextView id = view.findViewById(R.id.tv_id);
        TextView name = view.findViewById(R.id.tv_name);

        ImageStorage.setImageFromUrlToImageView(photo, Tube.IMAGE_FOLDER, getPhoto1());
        id.setText(getDetail());
        name.setText(getModel());
        return view;
    }

    public int getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getType() {
        return type;
    }

    public String getTypeurl() {
        return typeurl;
    }

    public String getModel() {
        return model;
    }

    public String getModelurl() {
        return modelurl;
    }

    public String getDetail() {
        return detail;
    }

    public String getProducer() {
        return producer;
    }

    public String getProducingCountry() {
        return producingCountry;
    }

    public String getSystemCNC() {
        return systemCNC;
    }

    public String getFullSystemCNC() {
        return fullSystemCNC;
    }

    public int getYear1() {
        return year1;
    }

    public String getCondition1() {
        return condition1;
    }

    public String getMachineLocation() {
        return machineLocation;
    }

    public int getDiameter_max() {
        return diameter_max;
    }

    public int getDiameter_min() {
        return diameter_min;
    }

    public int getNomberOfSpindles() {
        return nomberOfSpindles;
    }

    public String getDimensions_of_machine() {
        return dimensions_of_machine;
    }

    public String getWeight_of_machine() {
        return weight_of_machine;
    }

    public String getSpindletime() {
        return spindletime;
    }

    public String getMachineRuntime() {
        return machineRuntime;
    }

    public int getPrice() {
        return price;
    }

    public String getPhoto1() {
        return photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public String getPhoto4() {
        return photo4;
    }

    public String getPhoto5() {
        return photo5;
    }

    public String getDescriptionen() {
        return descriptionen;
    }

    public String getDescriptionru() {
        return descriptionru;
    }

    public String getVideo1() {
        return video1;
    }

    public String getVideo2() {
        return video2;
    }
}
