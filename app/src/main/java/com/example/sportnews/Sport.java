package com.example.sportnews;

public class Sport {
    private String title;
    private String info;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    private String subtitle;
    private int imageResource;

    public Sport(String sportTitle, String sportInfo, String sub, int image) {
        title = sportTitle;
        subtitle = sub;
        info = sportInfo;
        imageResource = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
