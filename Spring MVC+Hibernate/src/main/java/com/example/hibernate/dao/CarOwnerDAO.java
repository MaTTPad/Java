package com.example.hibernate.dao;

import com.example.hibernate.entity.Car;
import com.example.hibernate.entity.CarOwner;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class CarOwnerDAO extends BasicDAO {

    public CarOwnerDAO() {
        setClazz(CarOwner.class);
    }

    public CarOwner getCarOwnerByID(int carowner_id) {
        return (CarOwner) entityManager.createQuery("SElECT c FROM CarOwner c WHERE c.id=:carownerID")
                .setParameter("carownerID", carowner_id)
                .getSingleResult();
    }

    public Set<Car> getOwnerCarsByUser(int carowner_id) {
        CarOwner carOwner= getCarOwnerByID(carowner_id);
        return carOwner.getCars();
    }

}
