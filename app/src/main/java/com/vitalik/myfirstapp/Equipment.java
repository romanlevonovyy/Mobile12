package com.vitalik.myfirstapp;

public class Equipment {
    private final String temperaturel;
    private final String madename;
    private final String vologist;
    private final String location;
    private final String description;
    private final String datastart;
    private final String napryamok;
    private final String atmotusk;
    private final String speedviter;
    private final String dataservice;



    public Equipment(final String temperaturel, final String madename,
                     final String vologist, final String description,
                     final String location, final String dataservice,
                     final String datastart, final String napryamok,
                     final String speedviter, final String atmotusk) {
        this.temperaturel = temperaturel;
        this.madename = madename;
        this.vologist = vologist;
        this.description = description;
        this.dataservice = dataservice;
        this.datastart = datastart;
        this.speedviter = speedviter;
        this.atmotusk = atmotusk;
        this.napryamok = napryamok;
        this.location = location;

    }

    public String getTemperaturel() {
        return temperaturel;
    }

    public String getMadename() {
        return madename;
    }

    public String getVologist() {
        return vologist;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getDatastart() {
        return datastart;
    }

    public String getNapryamok() {
        return napryamok;
    }

    public String getAtmotusk() {
        return atmotusk;
    }

    public String getSpeedviter() {
        return speedviter;
    }

    public String getDataservice() {
        return dataservice;
    }
}