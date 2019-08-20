package com.example.smartbuoy.DATA.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Plan {

    @SerializedName("idPlan")
    @Expose
    private String idPlan;
    @SerializedName("idPlage")
    @Expose
    private String idPlage;
    @SerializedName("nomPlage")
    @Expose
    private String nomPlage;
    @SerializedName("villePlage")
    @Expose
    private String villePlage;
    @SerializedName("mainImage")
    @Expose
    private String mainImage;
    @SerializedName("date")
    @Expose
    private String date;

    public String getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(String idPlan) {
        this.idPlan = idPlan;
    }

    public String getIdPlage() {
        return idPlage;
    }

    public void setIdPlage(String idPlage) {
        this.idPlage = idPlage;
    }

    public String getNomPlage() {
        return nomPlage;
    }

    public void setNomPlage(String nomPlage) {
        this.nomPlage = nomPlage;
    }

    public String getVillePlage() {
        return villePlage;
    }

    public void setVillePlage(String villePlage) {
        this.villePlage = villePlage;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
