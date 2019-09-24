package com.example.smartbuoy.DATA.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {

    @SerializedName("parking")
    @Expose
    private Boolean parking;
    @SerializedName("shower")
    @Expose
    private Boolean shower;
    @SerializedName("resto")
    @Expose
    private Boolean resto;
    @SerializedName("wc")
    @Expose
    private Boolean wc;
    @SerializedName("bar")
    @Expose
    private Boolean bar;
    @SerializedName("beachVolley")
    @Expose
    private Boolean beachVolley;
    @SerializedName("chienAdmis")
    @Expose
    private Boolean chienAdmis;
    @SerializedName("parasol")
    @Expose
    private Boolean parasol;

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean getShower() {
        return shower;
    }

    public void setShower(Boolean shower) {
        this.shower = shower;
    }

    public Boolean getResto() {
        return resto;
    }

    public void setResto(Boolean resto) {
        this.resto = resto;
    }

    public Boolean getWc() {
        return wc;
    }

    public void setWc(Boolean wc) {
        this.wc = wc;
    }

    public Boolean getBar() {
        return bar;
    }

    public void setBar(Boolean bar) {
        this.bar = bar;
    }

    public Boolean getBeachVolley() {
        return beachVolley;
    }

    public void setBeachVolley(Boolean beachVolley) {
        this.beachVolley = beachVolley;
    }

    public Boolean getChienAdmis() {
        return chienAdmis;
    }

    public void setChienAdmis(Boolean chienAdmis) {
        this.chienAdmis = chienAdmis;
    }

    public Boolean getParasol() {
        return parasol;
    }

    public void setParasol(Boolean parasol) {
        this.parasol = parasol;
    }

}