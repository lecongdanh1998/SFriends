package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class MapDirectionModel implements Serializable {
    private int icon;
    private String direction;

    public MapDirectionModel(int icon, String direction) {
        this.icon = icon;
        this.direction = direction;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
