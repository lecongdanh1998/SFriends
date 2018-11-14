package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class RealestateContructor implements Serializable {
    String images;
    String catogary;
    String title;


    public RealestateContructor(String images, String catogary, String title) {
        this.images = images;
        this.catogary = catogary;
        this.title = title;
    }

    public RealestateContructor() {
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getCatogary() {
        return catogary;
    }

    public void setCatogary(String catogary) {
        this.catogary = catogary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
