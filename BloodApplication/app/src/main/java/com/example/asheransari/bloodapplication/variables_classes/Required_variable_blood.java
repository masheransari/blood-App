package com.example.asheransari.bloodapplication.variables_classes;

/**
 * Created by asher.ansari on 3/4/2017.
 */

public class Required_variable_blood {
    private String blood;
    private String state;
    private String urgent;
    private String relationShip;
    private int unit;
    private String city;
    private String hospital;
    private String contact;
    private String additionalInfo;
    private String country;
    private String emial;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;

    }

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {

        return country;
    }

public Required_variable_blood(){}
    public Required_variable_blood(String name,String emial,String country, String blood, String state, String urgent, String relationShip, int unit, String city, String hospital, String contact, String additionalInfo) {
        this.name = name;
        this.emial = emial;
        this.country = country;
        this.blood = blood;
        this.state = state;
        this.urgent = urgent;
        this.relationShip = relationShip;
        this.unit = unit;
        this.city = city;
        this.hospital = hospital;
        this.contact = contact;
        this.additionalInfo = additionalInfo;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setUrgent(String urgent) {
        this.urgent = urgent;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getBlood() {
        return blood;
    }

    public String getState() {
        return state;
    }

    public String getUrgent() {
        return urgent;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public int getUnit() {
        return unit;
    }

    public String getCity() {
        return city;
    }

    public String getHospital() {
        return hospital;
    }

    public String getContact() {
        return contact;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}
