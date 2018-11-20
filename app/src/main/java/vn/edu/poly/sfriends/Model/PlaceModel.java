package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class PlaceModel implements Serializable {
    String id;
    String name;
    String address;
    String typeCountry;
    String reviews;
    String timeOpen;

    public PlaceModel(String id, String name, String address, String typeCountry, String reviews,
                      String timeOpen) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.typeCountry = typeCountry;
        this.reviews = reviews;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTypeCountry() {
        return typeCountry;
    }

    public void setTypeCountry(String typeCountry) {
        this.typeCountry = typeCountry;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(String timeOpen) {
        this.timeOpen = timeOpen;
    }
}
