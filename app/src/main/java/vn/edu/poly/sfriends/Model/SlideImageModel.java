package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class SlideImageModel implements Serializable {
    String image;

    public SlideImageModel(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
