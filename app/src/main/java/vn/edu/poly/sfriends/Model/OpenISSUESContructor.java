package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class OpenISSUESContructor implements Serializable {
    String status,number,time,title,subtile,name,reply;

    public OpenISSUESContructor(String status, String number, String time, String title, String subtile, String name, String reply) {
        this.status = status;
        this.number = number;
        this.time = time;
        this.title = title;
        this.subtile = subtile;
        this.name = name;
        this.reply = reply;
    }

    public OpenISSUESContructor() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTile(String tile) {
        this.title = tile;
    }

    public String getSubtile() {
        return subtile;
    }

    public void setSubtile(String subtile) {
        this.subtile = subtile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
