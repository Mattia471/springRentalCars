package com.example.rentalspring.controller;

import com.example.rentalspring.domain.Cars;
import com.example.rentalspring.domain.Reservations;
import com.example.rentalspring.domain.Users;
import com.example.rentalspring.service.CarsService;
import com.example.rentalspring.service.ReservationsService;
import com.example.rentalspring.service.UsersService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class ReservationsController {

    //instanzio i servizi di tutto le entità per utilizzare all'interno del controller
    private final ReservationsService reservationsService;
    private final UsersService usersService;
    private final CarsService carsService;

    public ReservationsController(ReservationsService reservationsService, UsersService usersService, CarsService carsService) {
        this.reservationsService = reservationsService;
        this.usersService = usersService;
        this.carsService = carsService;
    }

    //POST
    @PostMapping(value = "/saveReservation")
    public String saveReservation(@ModelAttribute("addReservation") Reservations theReservation,@RequestParam("reservationId") int reservationId, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,@RequestParam("carId") int carID,  @RequestParam("userId") int userID) throws ParseException {

        //recupera l'id dalla sessione del ContextHolder
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Users user = usersService.getEmailBySurname(principal.getUsername());

        //creo gli oggetti ricavati grazie agl ID
        Cars car = carsService.getCar(carID);

        //ricerca se prenotazione gia esistente tramite id
        Reservations reservations = reservationsService.getReservation(reservationId);

        //formatto le stringhe in date
        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateTemp = pattern.parse(startDate);
        Date endDateTemp = pattern.parse(endDate);

        //instanzio l'oggetto reservation
        Reservations createReservations = new Reservations(startDateTemp,endDateTemp,user,car,"IN ATTESA");

        if(reservations!=null) {
            //lo mando al metodo saveReservation che aggiorna o crea a seconda se trova l'id o no
            reservationsService.editReservation(reservationId,startDateTemp,endDateTemp,user,car);
        }else{
            reservationsService.saveReservation(createReservations);
        }
        return "redirect:/listReservations";
    }

    //GET
    @GetMapping("/listReservations")
    public String listReservations(Model theModel) throws ParseException {
        List<Reservations> theReservations = reservationsService.getReservations();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = format.parse(format.format(new Date()));
        theModel.addAttribute("now", now);
        theModel.addAttribute("reservations", theReservations);
        theModel.addAttribute("titolo", "Storico Prenotazioni");
        theModel.addAttribute("ToSearch", "hidden");
        return "customerHome";
    }

    @GetMapping("/listReservationsUser")
    public String listReservationsUser(Model theModel,@RequestParam("userId") int id) {
        List<Reservations> theReservations = reservationsService.getReservationsUsers(id);

        theModel.addAttribute("reservations", theReservations);
        theModel.addAttribute("titolo", "Storico Prenotazioni");
        theModel.addAttribute("ToSearch", "hidden");
        theModel.addAttribute("noShow", "hidden");

        return "customerHome";
    }

    @GetMapping("/newReservation")
    public String showFormForAdd(Model theModel) {
        Reservations theReservation = new Reservations();
        theModel.addAttribute("addReservation", theReservation);

        theModel.addAttribute("titolo", "Stai eseguendo un noleggio auto");
        theModel.addAttribute("ToSearch", "hidden");
        theModel.addAttribute("button_verify", "Verifica Disponibilità");
        theModel.addAttribute("button_ok_show", "hidden");

        return "manageReservation";
    }

    @GetMapping("/editReservation")
    public String showFormForEdit(Model theModel, @RequestParam("reservationId") int theId, @RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo) throws ParseException {
        Reservations theReservation = new Reservations();
        theModel.addAttribute("addReservation", theReservation);

        theModel.addAttribute("titolo", "Stai modificando la tua prenotazione");
        theModel.addAttribute("ToSearch", "hidden");
        theModel.addAttribute("button_verify", "Verifica Disponibilità");
        theModel.addAttribute("button_ok_show", "hidden");


        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFromConvert = pattern.parse(dateFrom);
        Date dateToConvert = pattern.parse(dateTo);

        theModel.addAttribute("reservationId", theId);
        theModel.addAttribute("dateFromSelect", pattern.format(dateFromConvert));
        theModel.addAttribute("dateToSelect", pattern.format(dateToConvert));

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


    @GetMapping("/approveReservation")
    public String approveReservation(@RequestParam("reservationId") int theId) {
        reservationsService.approveReservation(theId);
        return "redirect:listCustomer";
    }

    @GetMapping("/declineReservation")
    public String declineReservation(@RequestParam("reservationId") int theId) {
        reservationsService.declineReservation(theId);
        return "redirect:listCustomer";
    }


    @GetMapping("/deleteReservation")
    public String deleteCustomer(@RequestParam("reservationId") int theId) {
        reservationsService.deleteReservation(theId);
        return "redirect:listReservations";
    }

}

