package com.example.demo.model;

public class CreateMedicalServiceDTO {
    private String name;
    private float price;
    private int duration;

    public CreateMedicalServiceDTO() {
    }

    public CreateMedicalServiceDTO(String name, float price, int duration) {
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

