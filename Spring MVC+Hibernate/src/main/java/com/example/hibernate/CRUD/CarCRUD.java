package com.example.hibernate.CRUD;

import com.example.hibernate.entity.Car;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

public class CarCRUD{

        public static Integer addCar(SessionFactory factory, String brand, String model, String VIN){
            Session session = factory.openSession();
            Transaction tx = null;
            Integer carID = null;

            try {
                tx = session.beginTransaction();
                Car car = new Car(brand,model,VIN);
                carID = (Integer) session.save(car);
                tx.commit();
            } catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
            return carID;
        }

        public static void listCars(SessionFactory factory ){
            Session session = factory.openSession();
            Transaction tx = null;

            try {
                System.out.println("ELOELOELOELEO");
                tx = session.beginTransaction();
                List cars = session.createQuery("FROM Car").list();
                for (Iterator iterator = cars.iterator(); iterator.hasNext();){
                    Car car = (Car) iterator.next();
                    System.out.print("Brand: " + car.getBrand());
                    System.out.print(" Model: " + car.getModel());
                    System.out.println(" VIN: " + car.getVIN());
                }
                tx.commit();
            } catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }


        public static void updateCar(SessionFactory factory, Integer carID, String VIN ){
            Session session = factory.openSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                Car car = (Car)session.get(Car.class, carID);
                car.setVIN( VIN );
                session.update(car);
                tx.commit();
            } catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }

        public static void deleteCar(SessionFactory factory, Integer carID){
            Session session = factory.openSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                Car car = (Car)session.get(Car.class, carID);
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
