package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class GroupsContructor implements Serializable {
    String images,title,number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public GroupsContructor(String images, String title, String number) {
        this.images = images;
        this.title = title;
        this.number = number;
    }

    public GroupsContructor() {
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
