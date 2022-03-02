package com.example.rentalspring.controller;

import com.example.rentalspring.domain.Cars;
import com.example.rentalspring.domain.Users;
import com.example.rentalspring.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class CarsController
{
    @Autowired
    private CarsService carsService;

    //POST
    @PostMapping(value = "/saveCar")
    public String saveCustomer(@ModelAttribute("car") Cars theCar) {
        carsService.saveCar(theCar);
        return "redirect:/listCar";
    }



    //GET
    @GetMapping("/listCar")
    public String listCustomers(Model theModel) {
        List <Cars> theCars = carsService.getCars();
        theModel.addAttribute("cars", theCars);
        theModel.addAttribute("titolo", "Lista auto presenti");
        theModel.addAttribute("ToSearch", "hidden");
        return "listCar";
    }

    @GetMapping("/newCar")
    public String showFormForAdd(Model theModel) {
        theModel.addAttribute("titolo", "Inserimento nuova auto");
        theModel.addAttribute("ToSearch", "hidden");
        theModel.addAttribute("button", "Inserisci Auto");
        theModel.addAttribute("formAction", "saveCar");
        return "manageCar";
    }

    @GetMapping("/viewCar")
    public String viewProfile(Model theModel,@RequestParam("carId") int theId) {
        Cars theCar = carsService.getCar(theId);
        theModel.addAttribute("car", theCar);
        theModel.addAttribute("titolo", "Modifica auto");
        theModel.addAttribute("formAction", "editCar");
        theModel.addAttribute("button", "Modifica Auto");
        theModel.addAttribute("ToSearch", "hidden");
        return "manageCar";
    }


    @GetMapping("/searchCar")
    public String availableCars(Model theModel, @RequestParam("dateFrom") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateFrom, @DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam("dateTo") Date dateTo) {
        List<Cars> theCars = carsService.getAvailableCars(dateFrom,dateTo);
        theModel.addAttribute("cars", theCars);
        theModel.addAttribute("titolo", "Stai eseguendo un noleggio auto");
        theModel.addAttribute("ToSearch", "hidden");
        theModel.addAttribute("button_verify", "Verifica Disponibilità");
        theModel.addAttribute("button_ok", "Conferma Noleggio");
        theModel.addAttribute("messageSelect", "Seleziona l'auto da noleggiare");

        theModel.addAttribute("dateFromSelect", dateFrom);
        theModel.addAttribute("dateToSelect", dateTo);
        return "manageReservation";
    }

    @GetMapping("/deleteCar")
    public String deleteCustomer(@RequestParam("carId") int theId) {
        carsService.deleteCar(theId);
        return "redirect:/listCar";
    }
}

