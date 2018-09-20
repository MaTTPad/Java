package com.example.hibernate.dao;

import com.example.hibernate.entity.Car;
import com.example.hibernate.entity.CarPark;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class CarParkDAO extends BasicDAO {
    public CarParkDAO() {
        setClazz(Car.class);
    }

    public CarPark getCarParkByID(int carpark_id) {
        return (CarPark) entityManager.createQuery("SElECT c FROM CarPark c WHERE c.id=:carparkID")
                .setParameter("carparkID", carpark_id)
                .getSingleResult();
    }

    public Set<Car> getParkingCars(int carowner_id) {
        CarPark carPark= getCarParkByID(carowner_id);
        return carPark.getCars();
    }

}
