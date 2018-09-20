package com.example.hibernate;

import com.example.hibernate.entity.Car;
import com.example.hibernate.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class CarController {

    private CarService carService;

    @Autowired(required=true)
    @Qualifier(value="carService")
    public void setCarService(CarService cs){
        this.carService = cs;
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String listCars(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("listCars", this.carService.listCars());
        return "car";
    }

    //For add and update car both
    @RequestMapping(value= "/car/add", method = RequestMethod.POST)
    public String addCar(@ModelAttribute("car") Car p){

        if(p.getCar_id() == 0){
            //new person, add it
            this.carService.addCar(p);
        }else{
            //existing person, call update
            this.carService.updateCar(p);
        }

        return "redirect:/cars";

    }

    @RequestMapping("/remove/{id}")
    public String removeCar(@PathVariable("id") int id){

        this.carService.removeCar(id);
        return "redirect:/cars";
    }

    @RequestMapping("/edit/{id}")
    public String editCar(@PathVariable("id") int id, Model model){
        model.addAttribute("car", this.carService.getCarById(id));
        model.addAttribute("listCars", this.carService.listCars());
        return "car";
    }

}
