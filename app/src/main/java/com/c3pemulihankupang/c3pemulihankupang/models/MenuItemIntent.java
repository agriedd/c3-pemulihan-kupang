package com.c3pemulihankupang.c3pemulihankupang.models;

import android.content.Intent;

public class MenuItemIntent extends MenuItem {

    private Intent intent;

    public MenuItemIntent(int id, String title, String subtitle, String image_url, Intent intent) {
        super(id, title, subtitle, image_url);
        this.intent = intent;
    }

    public MenuItemIntent(int id, String title, String subtitle, int drawable_image, Intent intent) {
        super(id, title, subtitle, drawable_image);
        this.intent = intent;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
