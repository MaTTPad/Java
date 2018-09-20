package com.example.hibernate.CRUD;

import com.example.hibernate.entity.CarPark;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

public class CarParkCRUD {

    public static Integer addCarPark(SessionFactory factory, String name){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer carParkID = null;

        try {
            tx = session.beginTransaction();
            CarPark carPark = new CarPark(name);
            carParkID = (Integer) session.save(carPark);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return carParkID;
    }

    public static void listCarParks(SessionFactory factory ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List carOwners = session.createQuery("FROM CarOwner").list();
            for (Iterator iterator = carOwners.iterator(); iterator.hasNext();){
                CarPark carPark = (CarPark) iterator.next();
                System.out.print("CarPark name: " + carPark.getName());

            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static void updateCarPark(SessionFactory factory, Integer carParkID, String newName){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            CarPark car = (CarPark) session.get(CarPark.class, carParkID);
            car.setName(newName);
            session.update(car);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void deleteCarPark(SessionFactory factory, Integer carParkID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            CarPark car = (CarPark) session.get(CarPark.class, carParkID);
            session.delete(car);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
