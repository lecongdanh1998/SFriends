package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class PointContructor implements Serializable {
    String title;
    String time;
    String point;

    public PointContructor() {
    }

    public PointContructor(String title, String time, String point) {
        this.title = title;
        this.time = time;
        this.point = point;
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

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
