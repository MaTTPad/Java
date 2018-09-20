package com.example.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "car")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="car_id")
    private int car_id;

    @Column(name="model")
    private String model;

    @Column(name="brand")
    private String brand;

    @Column(name="VIN")
    private String VIN;

    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    private CarOwner carOwner;

    @ManyToOne
    @JoinColumn(name="park_id", nullable=false)
    private CarPark carPark;

    public Car(String model, String brand, String VIN) {
        this.model = model;
        this.brand = brand;
        this.VIN = VIN;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public Car() {
    }

    public Car( String brand, String model, String VIN, CarOwner carOwner, CarPark carPark) {
        this.model = model;
        this.brand = brand;
        this.VIN = VIN;
        this.carOwner = carOwner;
        this.carPark = carPark;
    }


}
