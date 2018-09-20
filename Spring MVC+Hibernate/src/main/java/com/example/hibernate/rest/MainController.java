package com.example.hibernate.rest;

import com.example.hibernate.dao.CarDAO;
import com.example.hibernate.dao.CarOwnerDAO;
import com.example.hibernate.dao.CarParkDAO;
import com.example.hibernate.entity.Car;
import com.example.hibernate.entity.CarOwner;
import com.example.hibernate.entity.CarPark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.Set;

@RestController
public class MainController {

    private final CarDAO carDAO;
    private final CarOwnerDAO carOwnerDAO;
    private final CarParkDAO carParkDAO;


    @Autowired
    public MainController(CarDAO carDAO, CarOwnerDAO carOwnerDAO, CarParkDAO carParkDAO) {
        this.carDAO=carDAO;
        this.carOwnerDAO=carOwnerDAO;
        this.carParkDAO=carParkDAO;
    }


    @RequestMapping(value = "/car/add", method = RequestMethod.POST)
    public void addCar(@RequestParam(value = "model") String model, @RequestParam(value = "brand") String brand, @RequestParam(value = "VIN") String VIN) {
        carDAO.create(new Car(model, brand, VIN));
    }

    @RequestMapping(value = "/car/{car_id}", method = RequestMethod.GET)
    public Car getCar(@PathVariable(value = "car_id") int car_id) {
        try {
            return carDAO.getCarByCarID(car_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @RequestMapping(value = "/car/{car_id}", method = RequestMethod.DELETE)
    public void removeCar(@PathVariable(value = "car_id") int car_id) {
        try {
            Car car = carDAO.getCarByCarID(car_id);
            carDAO.delete(car);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/car/update", method = RequestMethod.POST)
    public void updateCarVIN(@RequestParam(value = "car_id") int car_id,@RequestParam(value = "VIN") String VIN) {
            Car car = carDAO.getCarByCarID(car_id);
            car.setVIN(VIN);
            carDAO.update(car);
    }


///////////////////////////////////////////////////////

    @RequestMapping(value = "/carOwner/{carOwner_id}", method = RequestMethod.GET)
    public CarOwner getCarOwner(@PathVariable(value = "carOwner_id") int carOwner_id) {
        try {
            return carOwnerDAO.getCarOwnerByID(carOwner_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @RequestMapping(value = "/carOwner/{carOwner_id}/cars", method = RequestMethod.GET)
    public Set getOwnerCars(@PathVariable(value = "carOwner_id") int carOwner_id) {
        return carOwnerDAO.getOwnerCarsByUser(carOwner_id);
    }



    @RequestMapping(value = "/carOwner/add", method = RequestMethod.POST)
    public void addCarOwner(@RequestParam(value = "name") String name) {
        carOwnerDAO.create(new CarOwner(name));
    }


    @RequestMapping(value = "/carOwner/remove/{carOwner_id}", method = RequestMethod.DELETE)
    public void removeCarOwner(@PathVariable(value = "carOwner_id") int carOwner_id) {
        try {
            CarOwner carOwner = carOwnerDAO.getCarOwnerByID(carOwner_id);
            carOwnerDAO.delete(carOwner);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }


    ///////////////////////////////////////////////////////

    @RequestMapping(value = "/carPark/{carPark_id}", method = RequestMethod.GET)
    public CarPark getCarPark(@PathVariable(value = "carPark_id") int carPark_id) {
        try {
            return carParkDAO.getCarParkByID(carPark_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @RequestMapping(value = "/carPark/{carPark_id}/cars", method = RequestMethod.GET)
    public Set getParkCars(@PathVariable(value = "carPark_id") int carPark_id) {
        return carParkDAO.getParkingCars(carPark_id);
    }


    @RequestMapping(value = "/carPark/add", method = RequestMethod.POST)
    public void addCarPark(@RequestParam(value = "name") String name) {
        carParkDAO.create(new CarPark(name));
    }


    @RequestMapping(value = "/carPark/remove/{carPark_id}", method = RequestMethod.DELETE)
    public void removeCarPark(@PathVariable(value = "carPark_id") int carPark_id) {
        try {
            CarPark carPark = carParkDAO.getCarParkByID(carPark_id);
            carParkDAO.delete(carPark);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }


}
