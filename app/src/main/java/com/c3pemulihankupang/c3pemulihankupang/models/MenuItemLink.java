package com.c3pemulihankupang.c3pemulihankupang.models;

public class MenuItemLink extends MenuItem {

    private String url;

    public MenuItemLink(int id, String title, String subtitle, String image_url, String url) {
        super(id, title, subtitle, image_url);
        this.url = url;
    }

    public MenuItemLink(int id, String title, String subtitle, int drawable_image, String url) {
        super(id, title, subtitle, drawable_image);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
