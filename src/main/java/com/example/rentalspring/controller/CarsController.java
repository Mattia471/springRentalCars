package com.example.rentalspring.controller;

import com.example.rentalspring.domain.Cars;
import com.example.rentalspring.domain.Reservations;
import com.example.rentalspring.service.CarsService;
import freemarker.template.SimpleDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CarsController {

    @Autowired
    private CarsService carsService;


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
    public String availableCars(Model theModel,@RequestParam("reservationId") int reservationId, @RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo) throws ParseException {

        //formatto le stringhe in date
        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateTemp = pattern.parse(dateFrom);
        Date endDateTemp = pattern.parse(dateTo);

        List<Cars> theCars = carsService.getAvailableCars(startDateTemp, endDateTemp);
        theModel.addAttribute("cars", theCars);


        //controllo se date inserite solo valide
        if(startDateTemp.after(endDateTemp) || endDateTemp.before(startDateTemp)){

            theModel.addAttribute("messageSelect", "Attenzione! Date inserite non corrette, reinseriscile");
            theModel.addAttribute("button_ok_show", "hidden");
            theModel.addAttribute("table_show", "hidden");
            theModel.addAttribute("button_verify", "Verifica Disponibilità");
            theModel.addAttribute("ToSearch", "hidden");

        }else {
            Reservations theReservation = new Reservations();
            theModel.addAttribute("addReservation", theReservation);

            theModel.addAttribute("messageSelect", "Seleziona l'auto da noleggiare");
            theModel.addAttribute("button_ok_text", "Conferma Noleggio");
            theModel.addAttribute("reservationId", reservationId);
            theModel.addAttribute("titolo", "Stai eseguendo un noleggio auto");
            theModel.addAttribute("button_verify", "Verifica Disponibilità");
            theModel.addAttribute("ToSearch", "hidden");


            theModel.addAttribute("dateFromSelect", pattern.format(startDateTemp));
            theModel.addAttribute("dateToSelect", pattern.format(endDateTemp));
        }


        return "manageReservation";
    }

    @GetMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") int theId) {
        carsService.deleteCar(theId);
        return "redirect:/listCar";
    }
}

