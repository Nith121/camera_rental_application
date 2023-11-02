package com.java.phaseone;

import java.io.*;
 class Camera implements Serializable {
    private static final long serialVersionUID = 1L;
	private int cameraId;
	private String brand;
    private String model;
    private double price;
    private boolean available;
    
    Camera(int cameraId, String brand, String model, double price, boolean available) {
		super();
		this.cameraId = cameraId;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.available = available;
	}
    public int getId() {
        return cameraId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}



	
    
    