package com.example.rentalspring.controller;

import com.example.rentalspring.domain.Cars;
import com.example.rentalspring.domain.Reservations;
import com.example.rentalspring.domain.Users;
import com.example.rentalspring.service.CarsService;
import com.example.rentalspring.service.ReservationsService;
import com.example.rentalspring.service.UsersService;
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


    //instanzio i servizi di tutto le entità per utilizzare all'interno del controller
    private final UsersService usersService;
    private final CarsService carsService;

    public ReservationsController(ReservationsService reservationsService, UsersService usersService, CarsService carsService) {
        this.reservationsService = reservationsService;
        this.usersService = usersService;
        this.carsService = carsService;
    }


    //QUESTO METODO PERMETTE DI CONVERTIRE AUTOMATICAMENTE LE DATE NEL PATTERN RICHIESTO
    //DA STUDIARE
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));

    }


    //POST
    @PostMapping(value = "/saveReservation")
    public String saveReservation(@ModelAttribute("addReservation") Reservations theReservation,@RequestParam("reservationId") int reservationId, @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate,@RequestParam("carId") int carID,  @RequestParam("userId") int userID) {
        //creo gli oggetti ricavati grazie agl ID
        Users user = usersService.getCustomer(userID);
        Cars car = carsService.getCar(carID);

        //ricerca se prenotazione gia esistente tramite id
        Reservations reservations = reservationsService.getReservation(reservationId);


        //instanzio l'oggetto reservation
        Reservations createReservations = new Reservations(startDate,endDate,user,car,"IN ATTESA");

        if(reservations!=null) {
            //lo mando al metodo saveReservation che aggiorna o crea a seconda se trova l'id o no
            reservationsService.editReservation(reservationId,startDate,endDate,car);
        }else{
            reservationsService.saveReservation(createReservations);
        }
        return "redirect:/listReservations";
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
    public String showFormForEdit(Model theModel, @RequestParam("reservationId") int theId, @RequestParam("dateFrom") Date dateFrom, @RequestParam("dateTo") Date dateTo) {
        Reservations theReservation = new Reservations();
        theModel.addAttribute("addReservation", theReservation);

        theModel.addAttribute("titolo", "Stai modificando la tua prenotazione");
        theModel.addAttribute("ToSearch", "hidden");
        theModel.addAttribute("button_verify", "Verifica Disponibilità");
        theModel.addAttribute("button_ok_show", "hidden");


        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
        String dateFromConvert = pattern.format(dateFrom);
        String dateToConvert = pattern.format(dateTo);

        theModel.addAttribute("reservationId", theId);
        theModel.addAttribute("dateFromSelect", dateFromConvert);
        theModel.addAttribute("dateToSelect", dateToConvert);

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
        return "redirect:listReservations";
    }

    @GetMapping("/declineReservation")
    public String declineReservation(@RequestParam("reservationId") int theId) {
        reservationsService.declineReservation(theId);
        return "redirect:listReservations";
    }


    @GetMapping("/deleteReservation")
    public String deleteCustomer(@RequestParam("reservationId") int theId) {
        reservationsService.deleteReservation(theId);
        return "redirect:listReservations";
    }

}

