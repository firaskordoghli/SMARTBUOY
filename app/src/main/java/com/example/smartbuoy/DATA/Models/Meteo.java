package com.example.smartbuoy.DATA.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meteo {

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
    @SerializedName("flag")
    @Expose
    private Integer flag;
    @SerializedName("cloudy")
    @Expose
    private Integer cloudy;
    @SerializedName("crowded")
    @Expose
    private Integer crowded;

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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getCloudy() {
        return cloudy;
    }

    public void setCloudy(Integer cloudy) {
        this.cloudy = cloudy;
    }

    public Integer getCrowded() {
        return crowded;
    }

    public void setCrowded(Integer crowded) {
        this.crowded = crowded;
    }

    @Override
    public String toString() {
        return "Meteo{" +
                "temp=" + temp +
                ", humi=" + humi +
                ", press=" + press +
                ", uv=" + uv +
                ", diVent='" + diVent + '\'' +
                ", viVent=" + viVent +
                ", ph=" + ph +
                ", tempEau=" + tempEau +
                ", date='" + date + '\'' +
                ", flag=" + flag +
                ", cloudy=" + cloudy +
                ", crowded=" + crowded +
                '}';
    }
}