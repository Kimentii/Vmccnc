package com.mzal.dto;

public class LivetoolPhoto {
    public static final String DATABASE_TABLE_NAME = "livetoolphoto";

    private int id;
    private String photo_name;
    private int livetool_id;

    public int getId() {
        return id;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public int getLivetool_id() {
        return livetool_id;
    }
}
