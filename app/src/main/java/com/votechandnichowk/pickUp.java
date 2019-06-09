package com.votechandnichowk;

public class pickUp  {

    private String id;
    private String pickup_name;
    private String pickup_time;
    private String pickup_coordinates;

    public pickUp(String id, String pickup_name, String pickup_time, String pickup_coordinates) {
        this.id= id;
        this.pickup_name= pickup_name;
        this.pickup_time = pickup_time;
        this.pickup_coordinates = pickup_coordinates;
    }

    public String getId() {
        return id;
    }

    public String getPickup_name() {
        return pickup_name;
    }

    public String getPickup_time() { return pickup_time; }

    public String getPickup_coordinates() {
        return pickup_coordinates;
    }
}
