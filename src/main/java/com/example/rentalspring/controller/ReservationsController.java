package com.example.rentalspring.controller;

import com.example.rentalspring.domain.Cars;
import com.example.rentalspring.domain.Reservations;
import com.example.rentalspring.service.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class ReservationsController {
    @Autowired
    private ReservationsService reservationsService;


    //QUESTO METODO PERMETTE DI CONVERTIRE AUTOMATICAMENTE LE DATE NEL PATTERN RICHIESTO
    //DA STUDIARE
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));

    }

    //POST
    @PostMapping(value = "/saveReservation")
    public String saveReservation(@ModelAttribute("reservation") Reservations theReservation) {
        reservationsService.saveReservation(theReservation);
        return "redirect:/listCar";
    }

    //GET
    @GetMapping("/listReservations")
    public String listReservations(Model theModel) {
        List<Reservations> theReservations = reservationsService.getReservations();
        theModel.addAttribute("reservations", theReservations);
        theModel.addAttribute("titolo", "Storico Prenotazioni");
        theModel.addAttribute("ToSearch", "hidden");
        return "customerHome";
    }

    @GetMapping("/newReservation")
    public String showFormForAdd(Model theModel) {
        Reservations theReservation = new Reservations();
        theModel.addAttribute("reservation", theReservation);
        theModel.addAttribute("titolo", "Stai eseguendo un noleggio auto");
        theModel.addAttribute("ToSearch", "hidden");
        theModel.addAttribute("button_verify", "Verifica Disponibilità");
        theModel.addAttribute("button_ok_show", "hidden");

        return "manageReservation";
    }

    @GetMapping("/viewReservation")
    public String viewProfile(Model theModel, @RequestParam("carId") int theId) {
        Reservations theReservation = reservationsService.getReservation(theId);
        theModel.addAttribute("car", theReservation);
        theModel.addAttribute("titolo", "Modifica auto");
        theModel.addAttribute("formAction", "editCar");
        theModel.addAttribute("button", "Modifica Auto");
        theModel.addAttribute("ToSearch", "hidden");
        return "manageCar";
    }

    @GetMapping("/deleteReservation")
    public String deleteCustomer(@RequestParam("reservationId") int theId) {
        reservationsService.deleteReservation(theId);
        return "redirect:/listCar";
    }
}

