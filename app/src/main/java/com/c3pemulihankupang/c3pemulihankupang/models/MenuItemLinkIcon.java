package com.c3pemulihankupang.c3pemulihankupang.models;

public class MenuItemLinkIcon extends MenuItemLink{

    private Integer icon;

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public MenuItemLinkIcon(int id, String title, String subtitle, String image_url, String url) {
        super(id, title, subtitle, image_url, url);
    }

    public MenuItemLinkIcon(int id, String title, String subtitle, int drawable_image, String url) {
        super(id, title, subtitle, drawable_image, url);
    }

    public MenuItemLinkIcon(int id, String title, String subtitle, int drawable_image, String url, int icon) {
        super(id, title, subtitle, drawable_image, url);
        this.icon = icon;
    }
}
