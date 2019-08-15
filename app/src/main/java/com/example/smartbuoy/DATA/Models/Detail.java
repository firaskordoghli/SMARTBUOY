package com.example.smartbuoy.DATA.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {

    @SerializedName("parking")
    @Expose
    private Boolean parking;
    @SerializedName("douche")
    @Expose
    private Boolean douche;
    @SerializedName("resto")
    @Expose
    private Boolean resto;
    @SerializedName("wc")
    @Expose
    private Boolean wc;
    @SerializedName("bar")
    @Expose
    private Boolean bar;
    @SerializedName("cafe")
    @Expose
    private Boolean cafe;
    @SerializedName("beachTennis")
    @Expose
    private Boolean beachTennis;
    @SerializedName("beachVolley")
    @Expose
    private Boolean beachVolley;
    @SerializedName("chienAdmis")
    @Expose
    private Boolean chienAdmis;

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean getDouche() {
        return douche;
    }

    public void setDouche(Boolean douche) {
        this.douche = douche;
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

    public Boolean getCafe() {
        return cafe;
    }

    public void setCafe(Boolean cafe) {
        this.cafe = cafe;
    }

    public Boolean getBeachTennis() {
        return beachTennis;
    }

    public void setBeachTennis(Boolean beachTennis) {
        this.beachTennis = beachTennis;
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

}
