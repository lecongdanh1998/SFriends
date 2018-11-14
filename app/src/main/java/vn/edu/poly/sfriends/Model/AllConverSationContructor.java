package vn.edu.poly.sfriends.Model;

import java.io.Serializable;

public class AllConverSationContructor implements Serializable {
    String images,name,stt,time,title;
    String voteSenior,voteMiniter;
    String Subtitle,Replies,Like;
    String notices1,notices2,timenotices1,timenotices2;
    public AllConverSationContructor() {
    }

    public String getNotices1() {
        return notices1;
    }

    public void setNotices1(String notices1) {
        this.notices1 = notices1;
    }

    public String getNotices2() {
        return notices2;
    }

    public void setNotices2(String notices2) {
        this.notices2 = notices2;
    }

    public String getTimenotices1() {
        return timenotices1;
    }

    public void setTimenotices1(String timenotices1) {
        this.timenotices1 = timenotices1;
    }

    public String getTimenotices2() {
        return timenotices2;
    }

    public void setTimenotices2(String timenotices2) {
        this.timenotices2 = timenotices2;
    }

    public AllConverSationContructor(String images, String name, String stt, String time, String title, String voteSenior, String voteMiniter, String subtitle, String replies, String like, String notices1, String notices2, String timenotices1, String timenotices2) {
        this.images = images;
        this.name = name;
        this.stt = stt;
        this.time = time;
        this.title = title;
        this.voteSenior = voteSenior;
        this.voteMiniter = voteMiniter;
        Subtitle = subtitle;
        Replies = replies;
        Like = like;
        this.notices1 = notices1;
        this.notices2 = notices2;
        this.timenotices1 = timenotices1;
        this.timenotices2 = timenotices2;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVoteSenior() {
        return voteSenior;
    }

    public void setVoteSenior(String voteSenior) {
        this.voteSenior = voteSenior;
    }

    public String getVoteMiniter() {
        return voteMiniter;
    }

    public void setVoteMiniter(String voteMiniter) {
        this.voteMiniter = voteMiniter;
    }

    public String getSubtitle() {
        return Subtitle;
    }

    public void setSubtitle(String subtitle) {
        Subtitle = subtitle;
    }

    public String getReplies() {
        return Replies;
    }

    public void setReplies(String replies) {
        Replies = replies;
    }

    public String getLike() {
        return Like;
    }

    public void setLike(String like) {
        Like = like;
    }
}
