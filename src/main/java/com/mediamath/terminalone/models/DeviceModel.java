package com.mediamath.terminalone.models;

import java.util.Date;

public class DeviceModel {
    private int id;
    private int parent_id;
    private int bidder_frequency;
    private String path;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getBidder_frequency() {
        return bidder_frequency;
    }

    public void setBidder_frequency(int bidder_frequency) {
        this.bidder_frequency = bidder_frequency;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
