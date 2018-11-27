package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class UuDaiContructor implements Serializable {
    String images;

    public UuDaiContructor() {
    }

    public UuDaiContructor(String images) {
        this.images = images;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
