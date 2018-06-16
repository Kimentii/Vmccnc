package com.kimentii.vmccnc.dto;

import android.content.Context;

import com.kimentii.vmccnc.AdapterGenerator;
import com.kimentii.vmccnc.ItemAdapter;
import com.kimentii.vmccnc.adapters.LivetoolGridAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Livetool implements AdapterGenerator<Livetool> {
    public static final String DATABASE_TABLE_NAME = "livetool";
    public static final String IMAGE_FOLDER = DATABASE_TABLE_NAME;

    // those fields will not be initialized from gson
    private ArrayList<String> photoNames = new ArrayList<>();

    private int id;
    private String product_id;
    private String order1;
    private String code;
    private String producer_of_machine_cnc;
    private String model_of_machine_cnc;
    private String type;
    private String type_of_tool;
    private String producer;
    private String producing_country;
    private String landing_diameter;
    private String din;
    private String clamping_range;
    private String tool_holder;
    private String s;
    private String speed_max;
    private String a;
    private String b;
    private String c;
    private String e;
    private String d;
    private String f;
    private String g;
    private String m;
    private String m1;
    private String n1n2;
    private String torque_max;
    private String length_of_working_part;
    private String displacement;
    private String coolant_supply;
    private String weight;
    private int price;
    private String description_en;
    private String is_sold;

    @Override
    public ItemAdapter<Livetool> getListAdapter(Context context, List<Livetool> items) {
        return null;
    }

    @Override
    public ItemAdapter<Livetool> getGridAdapter(Context context, List<Livetool> items) {
        return new LivetoolGridAdapter(context, items);
    }

    public void addPhotoName(String photoName) {
        photoNames.add(photoName);
    }

    public ArrayList<String> getPhotoNames() {
        return photoNames;
    }

    public int getId() {
        return id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getOrder1() {
        return order1;
    }

    public String getCode() {
        return code;
    }

    public String getProducer_of_machine_cnc() {
        return producer_of_machine_cnc;
    }

    public String getModel_of_machine_cnc() {
        return model_of_machine_cnc;
    }

    public String getType() {
        return type;
    }

    public String getType_of_tool() {
        return type_of_tool;
    }

    public String getProducer() {
        return producer;
    }

    public String getProducing_country() {
        return producing_country;
    }

    public String getLanding_diameter() {
        return landing_diameter;
    }

    public String getDin() {
        return din;
    }

    public String getClamping_range() {
        return clamping_range;
    }

    public String getTool_holder() {
        return tool_holder;
    }

    public String getS() {
        return s;
    }

    public String getSpeed_max() {
        return speed_max;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getE() {
        return e;
    }

    public String getD() {
        return d;
    }

    public String getF() {
        return f;
    }

    public String getG() {
        return g;
    }

    public String getM() {
        return m;
    }

    public String getM1() {
        return m1;
    }

    public String getN1n2() {
        return n1n2;
    }

    public String getTorque_max() {
        return torque_max;
    }

    public String getLength_of_working_part() {
        return length_of_working_part;
    }

    public String getDisplacement() {
        return displacement;
    }

    public String getCoolant_supply() {
        return coolant_supply;
    }

    public String getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription_en() {
        return description_en;
    }

    public String getIs_sold() {
        return is_sold;
    }
}
