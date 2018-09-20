package com.example.hibernate.Relacja;

import java.util.HashSet;
import java.util.Set;

import com.example.hibernate.CRUD.CarCRUD;
import com.example.hibernate.entity.Car;
import com.example.hibernate.entity.CarOwner;
import com.example.hibernate.entity.CarPark;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class HibernateMain {

    public static void main(String[] args) {


        CarOwner carOwner = new CarOwner();
        carOwner.setName("Mateusz Padula");

        CarPark carPark = new CarPark();
        carPark.setName("ParBabilonem");

        Car vwgolf=new Car("VW","Golf","WVWZZZ1JZXD279969",carOwner,carPark);
        Car vwpassat=new Car("VW","Passat","WVWZZZ1JZLO279931",carOwner,carPark);
        Car audia4=new Car("Audi","A4","WAUZZZ8DZWA152831",carOwner,carPark);

        Set<Car> carSet = new HashSet<Car>();
        carSet.add(vwgolf);
        carSet.add(vwpassat);
        carSet.add(audia4);

        carOwner.setCars(carSet);
        carPark.setCars(carSet);

            SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = com.example.hibernate.Relacja.HibernateUtil.getSessionFactory();
            session = sessionFactory.getCurrentSession();
            System.out.println("Session created");
            tx = session.beginTransaction();


            session.save(carOwner);
            session.save(carPark);
            session.save(vwgolf);
            session.save(vwpassat);
            session.save(audia4);
            CarCRUD.listCars(sessionFactory);


            tx.commit();
            System.out.println("Owner ID="+carOwner.getId());

        }
        catch(Exception e){
            System.out.println("Exception occured. "+e.getMessage());
            e.printStackTrace();
        }
        finally{
            if(!sessionFactory.isClosed()){
                System.out.println("Closing SessionFactory");
                sessionFactory.close();
            }
        }
    }

}