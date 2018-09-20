package com.example.hibernate.entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "car_owner")
public class CarOwner implements Serializable{
    @Column(name="name")
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="owner_id")
    private int id;
    //////////

    @OneToMany(mappedBy="carOwner")
    private Set<Car> cars;

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    /////////
    public CarOwner(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    /////////////////////


    public CarOwner() {
    }
}
