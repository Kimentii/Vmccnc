package com.kimentii.vmccnc.dto;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kimentii.vmccnc.AdapterGenerator;
import com.kimentii.vmccnc.ImageStorage;
import com.kimentii.vmccnc.ItemAdapter;
import com.kimentii.vmccnc.ItemHolder;
import com.kimentii.vmccnc.R;
import com.kimentii.vmccnc.adapters.AutomaticLineGridAdapter;
import com.kimentii.vmccnc.adapters.AutomaticLineListAdapter;

import java.io.Serializable;
import java.util.List;

public class AutomaticLine implements AdapterGenerator<AutomaticLine> {
    public static final String DATABASE_TABLE_NAME = "automated_line";
    public static final String IMAGE_FOLDER = "automated_lines";

    private String id;
    private String type_en;
    private String type_ru;
    private String model_en;
    private String model_ru;
    private String url;
    private String manufacturer_en;
    private String manufacturer_ru;
    private String country_en;
    private String country_ru;
    private String CNC_en;
    private String CNC_ru;
    private String CNC_full_en;
    private String CNC_full_ru;
    private String machine_condition_en;
    private String machine_condition_ru;
    private String machine_location_en;
    private String machine_location_ru;
    private int year_of_manufacture;
    private String workpiece_en;
    private String workpiece_ru;
    private String workpiece_weight;
    private String workpiece_photo1;
    private String workpiece_photo2;
    private String workpiece_description_en;
    private String workpiece_description_ru;
    private int line_width;
    private int line_length;
    private int line_hight;
    private int line_weight;
    private int num_of_working_staff;
    private double productivity;
    private int price;
    private String photo1;
    private String photo2;
    private String photo3;
    private String description_en;
    private String description_ru;

    @Override
    public ItemAdapter<AutomaticLine> getListAdapter(Context context, List<AutomaticLine> items) {
        return new AutomaticLineListAdapter(context, items);
    }

    @Override
    public ItemAdapter<AutomaticLine> getGridAdapter(Context context, List<AutomaticLine> items) {
        return new AutomaticLineGridAdapter(context, items);
    }

    @Override
    public View getCartView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cart, null, false);
        ImageView photo = view.findViewById(R.id.iv_photo);
        TextView id = view.findViewById(R.id.tv_id);
        TextView name = view.findViewById(R.id.tv_name);

        ImageStorage.setImageFromUrlToImageView(photo, AutomaticLine.IMAGE_FOLDER, getPhoto1());
        id.setText(getId());
        name.setText(getCNC_en());
        return view;
    }

    public String getId() {
        return id;
    }

    public String getType_en() {
        return type_en;
    }

    public String getType_ru() {
        return type_ru;
    }

    public String getModel_en() {
        return model_en;
    }

    public String getModel_ru() {
        return model_ru;
    }

    public String getUrl() {
        return url;
    }

    public String getManufacturer_en() {
        return manufacturer_en;
    }

    public String getManufacturer_ru() {
        return manufacturer_ru;
    }

    public String getCountry_en() {
        return country_en;
    }

    public String getCountry_ru() {
        return country_ru;
    }

    public String getCNC_en() {
        return CNC_en;
    }

    public String getCNC_ru() {
        return CNC_ru;
    }

    public String getCNC_full_en() {
        return CNC_full_en;
    }

    public String getCNC_full_ru() {
        return CNC_full_ru;
    }

    public String getMachine_condition_en() {
        return machine_condition_en;
    }

    public String getMachine_condition_ru() {
        return machine_condition_ru;
    }

    public String getMachine_location_en() {
        return machine_location_en;
    }

    public String getMachine_location_ru() {
        return machine_location_ru;
    }

    public int getYear_of_manufacture() {
        return year_of_manufacture;
    }

    public String getWorkpiece_en() {
        return workpiece_en;
    }

    public String getWorkpiece_ru() {
        return workpiece_ru;
    }

    public String getWorkpiece_weight() {
        return workpiece_weight;
    }

    public String getWorkpiece_photo1() {
        return workpiece_photo1;
    }

    public String getWorkpiece_photo2() {
        return workpiece_photo2;
    }

    public String getWorkpiece_description_en() {
        return workpiece_description_en;
    }

    public String getWorkpiece_description_ru() {
        return workpiece_description_ru;
    }

    public int getLine_width() {
        return line_width;
    }

    public int getLine_length() {
        return line_length;
    }

    public int getLine_hight() {
        return line_hight;
    }

    public int getLine_weight() {
        return line_weight;
    }

    public int getNum_of_working_staff() {
        return num_of_working_staff;
    }

    public double getProductivity() {
        return productivity;
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

    public String getDescription_en() {
        return description_en;
    }

    public String getDescription_ru() {
        return description_ru;
    }
}
