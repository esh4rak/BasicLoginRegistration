package com.eshrak.basicloginregistration.models;


import java.io.Serializable;

public class ShowItem implements Serializable {

    public String id;
    public String showName;
    public String showImage;
    public String language;
    public String type;
    public String premiered;
    public String rating;
    public String summary;

    public ShowItem(String id, String showName, String showImage, String language, String type, String premiered, String rating, String summary) {
        this.id = id;
        this.showName = showName;
        this.showImage = showImage;
        this.language = language;
        this.type = type;
        this.premiered = premiered;
        this.rating = rating;
        this.summary = summary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowImage() {
        return showImage;
    }

    public void setShowImage(String showImage) {
        this.showImage = showImage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPremiered() {
        return premiered;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
