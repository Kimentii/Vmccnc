package com.kimentii.vmccnc;

import java.io.Serializable;
import java.util.Random;

public class Machine implements Serializable {
    private static final String[] NAMES = {"DMU 40", "DMU 50 Ecoline", "DMF 220", "DMU 60", "GROB G 350"};
    private static final String[] TYPES = {"Vertical machining center", "Vertical machining center",
            "Vertical machining center", "Horizontal machining center", "CNC lathes"};
    private static final String[] LOCATION = {"Germany", "Germany", "Germany", "Germany", "Germany"};
    private static final String[] IDS = {"ID170420A", "IDGROB_G_350", "VF7-50-IDUSA001", "VF7-50-IDUSA001", "VF7-50-IDUSA001"};
    private static final int[] YEARS = {2000, 2001, 2002, 2003, 2004};
    String name;
    String type;
    String location;
    String id;
    int imageId;
    int year;


    public Machine() {
        Random random = new Random();
        int num = Math.abs(random.nextInt() % 5);
        name = NAMES[num];
        type = TYPES[num];
        location = LOCATION[num];
        id = IDS[num];
        year = YEARS[num];
        imageId = R.drawable.machine1;
        switch (num) {
            case 0:
                imageId = R.drawable.machine1;
                break;
            case 1:
                imageId = R.drawable.machine2;
                break;
            case 2:
                imageId = R.drawable.machine3;
                break;
            case 3:
                imageId = R.drawable.machine4;
                break;
            case 4:
                imageId = R.drawable.machine5;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
