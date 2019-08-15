package com.example.smartbuoy.DATA.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meteo {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("temp")
    @Expose
    private Integer temp;
    @SerializedName("humi")
    @Expose
    private Integer humi;
    @SerializedName("press")
    @Expose
    private Double press;
    @SerializedName("uv")
    @Expose
    private Double uv;
    @SerializedName("diVent")
    @Expose
    private String diVent;
    @SerializedName("viVent")
    @Expose
    private Double viVent;
    @SerializedName("ph")
    @Expose
    private Double ph;
    @SerializedName("tempEau")
    @Expose
    private Integer tempEau;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getHumi() {
        return humi;
    }

    public void setHumi(Integer humi) {
        this.humi = humi;
    }

    public Double getPress() {
        return press;
    }

    public void setPress(Double press) {
        this.press = press;
    }

    public Double getUv() {
        return uv;
    }

    public void setUv(Double uv) {
        this.uv = uv;
    }

    public String getDiVent() {
        return diVent;
    }

    public void setDiVent(String diVent) {
        this.diVent = diVent;
    }

    public Double getViVent() {
        return viVent;
    }

    public void setViVent(Double viVent) {
        this.viVent = viVent;
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Integer getTempEau() {
        return tempEau;
    }

    public void setTempEau(Integer tempEau) {
        this.tempEau = tempEau;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}