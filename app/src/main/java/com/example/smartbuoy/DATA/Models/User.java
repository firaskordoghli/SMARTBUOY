package com.example.smartbuoy.DATA.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("interest")
    @Expose
    private List<String> interest = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("goings")
    @Expose
    private List<Object> goings = null;
    @SerializedName("follows")
    @Expose
    private List<Object> follows = null;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public User() {
    }

    public User(List<String> interest, String username, String password, String email) {
        this.interest = interest;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getInterest() {
        return interest;
    }

    public void setInterest(List<String> interest) {
        this.interest = interest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Object> getGoings() {
        return goings;
    }

    public void setGoings(List<Object> goings) {
        this.goings = goings;
    }

    public List<Object> getFollows() {
        return follows;
    }

    public void setFollows(List<Object> follows) {
        this.follows = follows;
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

    @Override
    public String toString() {
        return "User{" +
                "image='" + image + '\'' +
                ", role='" + role + '\'' +
                ", interest='" + interest + '\'' +
                ", id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", goings=" + goings +
                ", follows=" + follows +
                ", date='" + date + '\'' +
                ", v=" + v +
                '}';
    }
}
