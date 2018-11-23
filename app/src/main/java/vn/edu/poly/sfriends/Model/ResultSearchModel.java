package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class ResultSearchModel implements Serializable {
    private String id;
    private String title;
    private String slug;
    private String description;
    private String img;
    private String phone;
    private String email;
    private String website;
    private String facebook;
    private String geolocation;
    private String address;
    private String add_ward;
    private String add_city;
    private String link;
    private String type_id;
    private String view;

    public ResultSearchModel(String id, String title, String slug, String description, String img,
                             String phone, String email, String website, String facebook,
                             String geolocation, String address, String add_ward, String add_city,
                             String link, String type_id, String view) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.img = img;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.facebook = facebook;
        this.geolocation = geolocation;
        this.address = address;
        this.add_ward = add_ward;
        this.add_city = add_city;
        this.link = link;
        this.type_id = type_id;
        this.view = view;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdd_ward() {
        return add_ward;
    }

    public void setAdd_ward(String add_ward) {
        this.add_ward = add_ward;
    }

    public String getAdd_city() {
        return add_city;
    }

    public void setAdd_city(String add_city) {
        this.add_city = add_city;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
