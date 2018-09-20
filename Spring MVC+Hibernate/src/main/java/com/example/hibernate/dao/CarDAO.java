package com.example.hibernate.dao;

import com.example.hibernate.entity.Car;
import org.springframework.stereotype.Repository;

@Repository
public class CarDAO extends BasicDAO {

    public CarDAO() {
        setClazz(Car.class);
    }

    public Car getCarByCarID(int car_id) {
        return (Car) entityManager.createQuery("SElECT c FROM Car c WHERE c.car_id=:carID")
                .setParameter("carID", car_id)
                .getSingleResult();
    }

    public Car getCarByVIN(String VIN) {
        return (Car) entityManager.createQuery("SElECT c FROM Car c WHERE c.VIN=:carVIN")
                .setParameter("carVIN", VIN)
                .getSingleResult();
    }




}
