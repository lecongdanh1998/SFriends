package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class ClassufiedContructor implements Serializable {
    String images;
    String title;
    String SubTile;
    String time;
    String Rs;

    public ClassufiedContructor() {
    }

    public ClassufiedContructor(String images, String title, String subTile, String time, String rs) {
        this.images = images;
        this.title = title;
        SubTile = subTile;
        this.time = time;
        Rs = rs;
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

    public String getSubTile() {
        return SubTile;
    }

    public void setSubTile(String subTile) {
        SubTile = subTile;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRs() {
        return Rs;
    }

    public void setRs(String rs) {
        Rs = rs;
    }
}
