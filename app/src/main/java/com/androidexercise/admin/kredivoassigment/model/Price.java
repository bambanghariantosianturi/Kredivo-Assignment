package com.androidexercise.admin.kredivoassigment.model;

public class Price {

    private Long price;

    public Price(Long price) {
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "price='" + price + '\'' +
                '}';
    }
}
