package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class LoginContrucstor implements Serializable {
    String image;

    public LoginContrucstor(String image) {
        this.image = image;
    }

    public LoginContrucstor() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
