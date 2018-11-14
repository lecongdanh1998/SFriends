package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class ServicesContructor implements Serializable {
    String images,title;

    public ServicesContructor(String images, String title) {
        this.images = images;
        this.title = title;
    }

    public ServicesContructor() {
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
