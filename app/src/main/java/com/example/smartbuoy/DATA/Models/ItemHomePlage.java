package com.example.smartbuoy.DATA.Models;

import com.google.gson.annotations.SerializedName;

public class ItemHomePlage {

    @SerializedName("_id")
    private String id;

    @SerializedName("nom")
    private String name;

    @SerializedName("ville")
    private String ville;

    @SerializedName("mainImage")
    private String image;

    @SerializedName("rate")
    private String rating;

    public ItemHomePlage(String id, String name, String ville, String image, String rating) {
        this.id = id;
        this.name = name;
        this.ville = ville;
        this.image = image;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ItemHomePlage{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ville='" + ville + '\'' +
                ", image='" + image + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
