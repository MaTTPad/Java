package com.example.hibernate.CRUD;

import com.example.hibernate.entity.CarOwner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

public class CarOwnerCRUD {

    public static Integer addCarOwner(SessionFactory factory, String name){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer carOwnerID = null;

        try {
            tx = session.beginTransaction();
            CarOwner carOwner = new CarOwner(name);
            carOwnerID = (Integer) session.save(carOwner);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return carOwnerID;
    }

    public static void listCarOwners(SessionFactory factory ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List carOwners = session.createQuery("FROM CarOwner").list();
            for (Iterator iterator = carOwners.iterator(); iterator.hasNext();){
                CarOwner carOwner = (CarOwner) iterator.next();
                System.out.print("Car Owner: " + carOwner.getName());

            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static void updateCarOwner(SessionFactory factory, Integer ownerID, String newName){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            CarOwner car = (CarOwner) session.get(CarOwner.class, ownerID);
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

    public static void deleteCarOwner(SessionFactory factory, Integer carOwnerID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            CarOwner car = (CarOwner) session.get(CarOwner.class, carOwnerID);
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
