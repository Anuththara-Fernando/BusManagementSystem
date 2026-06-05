package com.smartbus.app;

public class Bus {

    String busName;
    String city;
    String destination;
    String status;
    String eta;

    public Bus() {
    }
    public Bus(String busName,
               String city,
               String destination,
               String status,
               String eta) {

        this.busName = busName;
        this.city = city;
        this.destination = destination;
        this.status = status;
        this.eta = eta;
    }

    public String getBusName() {
        return busName;
    }

    public String getCity() {
        return city;
    }

    public String getDestination() {
        return destination;
    }

    public String getStatus() {
        return status;
    }

    public String getEta() {
        return eta;
    }
}