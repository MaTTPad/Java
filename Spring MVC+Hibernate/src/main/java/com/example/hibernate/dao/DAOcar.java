package com.example.hibernate.dao;


import java.util.List;

import com.example.hibernate.entity.Car;

public interface DAOcar {

    public void addCar(Car p);
    public void updateCar(Car p);
    public List<Car> listCars();
    public Car getCarById(int id);
    public void removeCar(int id);
}

