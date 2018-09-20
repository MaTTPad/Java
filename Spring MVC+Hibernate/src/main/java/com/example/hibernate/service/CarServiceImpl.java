package com.example.hibernate.service;

import com.example.hibernate.dao.DAOcar;
import com.example.hibernate.entity.Car;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CarServiceImpl implements CarService {


    private DAOcar carDAO;

    public void setCarDAO(DAOcar carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    @Transactional
    public void addCar(Car p) {
        this.carDAO.addCar(p);
    }

    @Override
    @Transactional
    public void updateCar(Car p) {
        this.carDAO.updateCar(p);

    }

    @Override
    @Transactional
    public List<Car> listCars() {
        return this.carDAO.listCars();
    }

    @Override
    @Transactional
    public Car getCarById(int id) {
        return this.carDAO.getCarById(id);
    }

    @Override
    @Transactional
    public void removeCar(int id) {
        this.carDAO.removeCar(id);
    }
}
