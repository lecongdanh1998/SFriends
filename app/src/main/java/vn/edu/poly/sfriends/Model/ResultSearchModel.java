package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class ResultSearchModel implements Serializable {
    private String id;
    private String name;
    private String reviewCount;
    private String typeCountry;
    private String address;
    private String timeOpen;

    public ResultSearchModel(String id, String name, String reviewCount, String typeCountry,
                             String address, String timeOpen) {
        this.id = id;
        this.name = name;
        this.reviewCount = reviewCount;
        this.typeCountry = typeCountry;
        this.address = address;
        this.timeOpen = timeOpen;
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

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getTypeCountry() {
        return typeCountry;
    }

    public void setTypeCountry(String typeCountry) {
        this.typeCountry = typeCountry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(String timeOpen) {
        this.timeOpen = timeOpen;
    }
}
