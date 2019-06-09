package com.votechandnichowk;

public class Station {
    private String id;
    private String ps_image;
    private String ps_name;
    private String ps_no;
    private String facilities;
    private String ps_queue;
    private String ps_gis;

    public Station(String id, String ps_image, String ps_name, String ps_no, String facilities, String ps_queue, String ps_gis) {
        this.id= id;
        this.ps_image= ps_image;
        this.ps_name = ps_name;
        this.ps_no = ps_no;
        this.facilities=facilities;
        this.ps_queue=ps_queue;
        this.ps_gis=ps_gis;
    }

    public String getId() {
        return id;
    }

    public String getPs_image() {
        return ps_image;
    }

    public String getPs_name() { return ps_name; }

    public String getPs_no() {
        return ps_no;
    }

    public String getFacilities() {
        return facilities;
    }

    public String getPs_queue() {
        return ps_queue;
    }

    public String getPs_gis() {
        return ps_gis;
    }
}
