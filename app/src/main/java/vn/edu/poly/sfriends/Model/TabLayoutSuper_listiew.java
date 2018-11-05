package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class TabLayoutSuper_listiew implements Serializable {
    String images;
    String NameShop;
    String imagesBackground;
    String title;
    String time;
    String day;
    String cmt;
    String like;
    String star;
    String khuyenmai;

    public TabLayoutSuper_listiew(String images, String nameShop, String imagesBackground, String title, String time, String day, String cmt, String like, String star, String khuyenmai) {
        this.images = images;
        NameShop = nameShop;
        this.imagesBackground = imagesBackground;
        this.title = title;
        this.time = time;
        this.day = day;
        this.cmt = cmt;
        this.like = like;
        this.star = star;
        this.khuyenmai = khuyenmai;
    }

    public TabLayoutSuper_listiew() {

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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getKhuyenmai() {
        return khuyenmai;
    }

    public void setKhuyenmai(String khuyenmai) {
        this.khuyenmai = khuyenmai;
    }
}
