package com.kimentii.vmccnc.dto;

import android.content.Context;

import com.kimentii.vmccnc.AdapterGenerator;
import com.kimentii.vmccnc.ItemAdapter;
import com.kimentii.vmccnc.adapters.LatheGridAdapter;
import com.kimentii.vmccnc.adapters.LatheListAdapter;

import java.io.Serializable;
import java.util.List;

public class Lathe implements AdapterGenerator<Lathe> {
    public static final String DATABASE_TABLE_NAME = "lathe";
    public static final String IMAGE_FOLDER = DATABASE_TABLE_NAME;

    private int id;
    private String productId;
    private String type;
    private String typeurl;
    private String model;
    private String modelurl;
    private String producer;
    private String producingCountry;
    private String systemCNC;
    private String fullSystemCNC;
    private int year1;
    private String condition1;
    private String machineLocation;
    private int axisCount;
    private int diameter_max;
    private int diameter;
    private int bardiameter;
    private int length_max;
    private int x;
    private int y;
    private int z;
    private int spindleSpeed;
    private String spindlepower;
    private int spindlediameter;
    private String subspindle;
    private int subspindleSpeed;
    private String vdi;
    private String toolsall;
    private String toolslive;
    private String toolsnotlive;
    private String tailstock;
    private String accuracy;
    private String accuracyAxisC;
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
    public ItemAdapter<Lathe> getListAdapter(Context context, List<Lathe> items) {
        return new LatheListAdapter(context, items);
    }

    @Override
    public ItemAdapter<Lathe> getGridAdapter(Context context, List<Lathe> items) {
        return new LatheGridAdapter(context, items);
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

    public int getAxisCount() {
        return axisCount;
    }

    public int getDiameter_max() {
        return diameter_max;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getBardiameter() {
        return bardiameter;
    }

    public int getLength_max() {
        return length_max;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getSpindleSpeed() {
        return spindleSpeed;
    }

    public String getSpindlepower() {
        return spindlepower;
    }

    public int getSpindlediameter() {
        return spindlediameter;
    }

    public String getSubspindle() {
        return subspindle;
    }

    public int getSubspindleSpeed() {
        return subspindleSpeed;
    }

    public String getVdi() {
        return vdi;
    }

    public String getToolsall() {
        return toolsall;
    }

    public String getToolslive() {
        return toolslive;
    }

    public String getToolsnotlive() {
        return toolsnotlive;
    }

    public String getTailstock() {
        return tailstock;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public String getAccuracyAxisC() {
        return accuracyAxisC;
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
}
