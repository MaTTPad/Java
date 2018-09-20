package com.example.hibernate.service;

import com.example.hibernate.entity.Car;

import java.util.List;

public interface CarService {
    public void addCar(Car p);
    public void updateCar(Car p);
    public List<Car> listCars();
    public Car getCarById(int id);
    public void removeCar(int id);

}
