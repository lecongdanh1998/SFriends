package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class AlbumsContructor implements Serializable {
    String images,title;

    public AlbumsContructor(String images, String title) {
        this.images = images;
        this.title = title;
    }

    public AlbumsContructor() {
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
