package com.kimentii.vmccnc;

import java.io.Serializable;

public class Machine implements Serializable {
    String name;

    public Machine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
