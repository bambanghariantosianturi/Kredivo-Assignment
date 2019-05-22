package com.androidexercise.admin.kredivoassigment.model;

public class Banner {

    private int imageBanner;

    public Banner(int imageBanner) {
        this.imageBanner = imageBanner;
    }

    public int getImageBanner() {
        return imageBanner;
    }

    public void setImageBanner(int imageBanner) {
        this.imageBanner = imageBanner;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "imageBanner=" + imageBanner +
                '}';
    }
}
