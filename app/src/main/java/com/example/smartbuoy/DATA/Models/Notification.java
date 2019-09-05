package com.example.smartbuoy.DATA.Models;

public class Notification {

    private String image;
    private String description;

    public Notification() {
    }

    public Notification(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
