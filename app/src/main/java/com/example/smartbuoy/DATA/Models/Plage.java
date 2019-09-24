package com.example.smartbuoy.DATA.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Plage {

    @SerializedName("detail")
    @Expose
    private Detail detail;
    @SerializedName("images")
    @Expose
    private List<Object> images = null;
    @SerializedName("events")
    @Expose
    private List<Object> events = null;
    @SerializedName("buoys")
    @Expose
    private List<String> buoys = null;
    @SerializedName("acti")
    @Expose
    private List<Object> acti = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("ville")
    @Expose
    private String ville;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("etat")
    @Expose
    private String etat;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("capacite")
    @Expose
    private Integer capacite;
    @SerializedName("mainImage")
    @Expose
    private String mainImage;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("meteo")
    @Expose
    private Meteo meteo;
    @SerializedName("favoris")
    @Expose
    private Boolean favoris;
    @SerializedName("prev")
    @Expose
    private List<Meteo> prev = null;
    @SerializedName("rate")
    @Expose
    private float rate;

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }

    public List<Object> getEvents() {
        return events;
    }

    public void setEvents(List<Object> events) {
        this.events = events;
    }

    public List<String> getBuoys() {
        return buoys;
    }

    public void setBuoys(List<String> buoys) {
        this.buoys = buoys;
    }

    public List<Object> getActi() {
        return acti;
    }

    public void setActi(List<Object> acti) {
        this.acti = acti;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Meteo getMeteo() {
        return meteo;
    }

    public void setMeteo(Meteo meteo) {
        this.meteo = meteo;
    }

    public Boolean getFavoris() {
        return favoris;
    }

    public void setFavoris(Boolean favoris) {
        this.favoris = favoris;
    }

    public List<Meteo> getPrev() {
        return prev;
    }

    public void setPrev(List<Meteo> prev) {
        this.prev = prev;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}