package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class UserIssuedByContructor implements Serializable {
    String title;

    public UserIssuedByContructor(String title) {
        this.title = title;
    }

    public UserIssuedByContructor() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
