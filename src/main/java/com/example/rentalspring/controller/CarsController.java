package com.example.rentalspring.controller;

import com.example.rentalspring.domain.Cars;
import com.example.rentalspring.domain.Users;
import com.example.rentalspring.service.CarsService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class CarsController {
    @Autowired
    private CarsService carsService;

    //QUESTO METODO PERMETTE DI CONVERTIRE AUTOMATICAMENTE LE DATE NEL PATTERN RICHIESTO
    //DA STUDIARE
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));

    }

    //POST
    @PostMapping(value = "/saveCar")
    public String saveCar(@ModelAttribute("car") Cars theCar) {
        carsService.saveCar(theCar);
        return "redirect:/listCar";
    }

    //GET
    @GetMapping("/listCar")
    public String listCar(Model theModel) {
        List<Cars> theCars = carsService.getCars();
        theModel.addAttribute("cars", theCars);
        theModel.addAttribute("titolo", "Lista auto presenti");
        theModel.addAttribute("ToSearch", "hidden");
        return "listCar";
    }

    @GetMapping("/newCar")
    public String showFormForAdd(Model theModel) {
        Cars theCar = new Cars();
        theModel.addAttribute("car", theCar);
        theModel.addAttribute("titolo", "Inserimento nuova auto");
        theModel.addAttribute("ToSearch", "hidden");
        theModel.addAttribute("button", "Inserisci Auto");
        return "manageCar";
    }

    @GetMapping("/viewCar")
    public String viewProfile(Model theModel, @RequestParam("carId") int theId) {
        Cars theCar = carsService.getCar(theId);
        theModel.addAttribute("car", theCar);
        theModel.addAttribute("titolo", "Modifica auto");
        theModel.addAttribute("button", "Modifica Auto");
        theModel.addAttribute("ToSearch", "hidden");
        return "manageCar";
    }


    @GetMapping("/searchCar")
    public String availableCars(Model theModel, @RequestParam("dateFrom") Date dateFrom, @RequestParam("dateTo") Date dateTo) {
        List<Cars> theCars = carsService.getAvailableCars(dateFrom, dateTo);
        theModel.addAttribute("cars", theCars);
        theModel.addAttribute("titolo", "Stai eseguendo un noleggio auto");
        theModel.addAttribute("ToSearch", "hidden");
        theModel.addAttribute("button_verify", "Verifica Disponibilità");
        theModel.addAttribute("button_ok_text", "Conferma Noleggio");
        theModel.addAttribute("messageSelect", "Seleziona l'auto da noleggiare");

        SimpleDateFormat dateS = new SimpleDateFormat("yyyy-MM-dd");
        String a = dateS.format(dateFrom);

        theModel.addAttribute("dateFromSelect", a);
        theModel.addAttribute("dateToSelect", dateTo);
        return "manageReservation";
    }

    @GetMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") int theId) {
        carsService.deleteCar(theId);
        return "redirect:/listCar";
    }
}

