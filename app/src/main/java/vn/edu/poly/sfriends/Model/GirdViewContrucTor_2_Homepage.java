package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class GirdViewContrucTor_2_Homepage implements Serializable {
    String images;
    String title;

    public GirdViewContrucTor_2_Homepage(String images, String title) {
        this.images = images;
        this.title = title;
    }

    public GirdViewContrucTor_2_Homepage() {
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
