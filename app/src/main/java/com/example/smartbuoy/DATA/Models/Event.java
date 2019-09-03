package com.example.smartbuoy.DATA.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Event {

    @SerializedName("participants")
    @Expose
    private List<Object> participants = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("titre")
    @Expose
    private String titre;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("plage")
    @Expose
    private String plage;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("simEvent")
    @Expose
    private Event simEvent;
    @SerializedName("joined")
    @Expose
    private Boolean joined;

    public Event(String titre, String desc, String date, String type, String image, String plage, String user, Event simEvent, Boolean joined) {
        this.titre = titre;
        this.desc = desc;
        this.date = date;
        this.type = type;
        this.image = image;
        this.plage = plage;
        this.user = user;
        this.simEvent = simEvent;
        this.joined = joined;
    }

    public Event(String titre, String desc, String date, String type, String image, String plage, String user) {
        this.titre = titre;
        this.desc = desc;
        this.date = date;
        this.type = type;
        this.image = image;
        this.plage = plage;
        this.user = user;
    }

    public Event(String titre, String desc, String date, String type, String image, String plage, String user, Event simEvent) {
        this.titre = titre;
        this.desc = desc;
        this.date = date;
        this.type = type;
        this.image = image;
        this.plage = plage;
        this.user = user;
        this.simEvent = simEvent;
    }

    public List<Object> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Object> participants) {
        this.participants = participants;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlage() {
        return plage;
    }

    public void setPlage(String plage) {
        this.plage = plage;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Event getSimEvent() {
        return simEvent;
    }

    public void setSimEvent(Event simEvent) {
        this.simEvent = simEvent;
    }

    public Boolean getJoined() {
        return joined;
    }

    public void setJoined(Boolean joined) {
        this.joined = joined;
    }

    @Override
    public String toString() {
        return "Event{" +
                "participants=" + participants +
                ", id='" + id + '\'' +
                ", titre='" + titre + '\'' +
                ", desc='" + desc + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", image='" + image + '\'' +
                ", plage='" + plage + '\'' +
                ", user='" + user + '\'' +
                ", v=" + v +
                ", simEvent=" + simEvent +
                ", joined=" + joined +
                '}';
    }
}