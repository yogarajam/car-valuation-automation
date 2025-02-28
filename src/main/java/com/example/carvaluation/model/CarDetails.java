package com.example.carvaluation.model;

public class CarDetails {
    private String registration;
    private String make;
    private String model;
    private int year;
    private double value; // In pounds (£)

    public CarDetails(String registration, String make, String model, int year, double value) {
        this.registration = registration;
        this.make = make;
        this.model = model;
        this.year = year;
        this.value = value;
    }

    // Getters and setters
    public String getRegistration() { return registration; }
    public void setRegistration(String registration) { this.registration = registration; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getValue() { return value; }

    @Override
    public String toString() {
        return "CarDetails{" +
                "registration='" + registration + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", value=£" + value +
                '}';
    }
}