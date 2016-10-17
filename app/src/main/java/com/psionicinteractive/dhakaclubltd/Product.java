package com.psionicinteractive.dhakaclubltd;

/**
 * Created by iShaheed on 8/28/2016.
 */
public class Product {
    private String image;
    private String name;
    private String email;
    private String phoneNumber;

    public Product(String image, String name, String email, String phone) {
        this.image = image;
        this.name = name;
        this.email = email;
        this.phoneNumber= phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
