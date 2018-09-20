package com.example.hibernate.dao;

import com.example.hibernate.entity.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DAOcarImpl implements DAOcar {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }


    @Override
    public void addCar(Car p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
    }

    @Override
    public void updateCar(Car p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Car> listCars() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Car> carsList = session.createQuery("from Car").list();
        return carsList;
    }

    @Override
    public Car getCarById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Car p = (Car) session.load(Car.class, new Integer(id));
        return p;
    }

    @Override
    public void removeCar(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Car p = (Car) session.load(Car.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
    }

}
