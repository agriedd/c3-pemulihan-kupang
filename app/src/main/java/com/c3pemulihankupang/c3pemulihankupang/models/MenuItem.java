package com.c3pemulihankupang.c3pemulihankupang.models;

import android.content.Intent;

public abstract class MenuItem {
    private Integer id;
    private String title ;
    private String subtitle;
    private String image_url;
    private Integer drawable_image;
    private Integer color;
    private Integer style;

    public MenuItem(int id, String title, String subtitle, String image_url) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.image_url = image_url;
    }

    public MenuItem(int id, String title, String subtitle, int drawable_image) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.drawable_image = drawable_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Integer getDrawable_image() {
        return drawable_image;
    }

    public void setDrawable_image(Integer drawable_image) {
        this.drawable_image = drawable_image;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getColor() {
        return color;
    }

    public MenuItem setColor(Integer color) {
        this.color = color;
        return this;
    }

    public Integer getStyle() {
        return style;
    }

    public MenuItem setStyle(Integer style) {
        this.style = style;
        return this;
    }
}
