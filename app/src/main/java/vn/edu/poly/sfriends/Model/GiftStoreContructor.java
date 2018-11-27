package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class GiftStoreContructor implements Serializable {
    String images;
    String NameShop;
    String imagesBackground;
    String title;
    String time;

    public GiftStoreContructor(String images, String nameShop, String imagesBackground, String title, String time) {
        this.images = images;
        NameShop = nameShop;
        this.imagesBackground = imagesBackground;
        this.title = title;
        this.time = time;
    }

    public GiftStoreContructor() {
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getNameShop() {
        return NameShop;
    }

    public void setNameShop(String nameShop) {
        NameShop = nameShop;
    }

    public String getImagesBackground() {
        return imagesBackground;
    }

    public void setImagesBackground(String imagesBackground) {
        this.imagesBackground = imagesBackground;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
