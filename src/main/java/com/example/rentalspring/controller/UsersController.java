package com.example.rentalspring.controller;

import com.example.rentalspring.domain.Users;
import com.example.rentalspring.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;


    //POST
    @PostMapping(value = "/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Users theCustomer) {
        Users user = usersService.getCustomer(theCustomer.getId());

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        if(user!=null) {
            //controllo se password inserita è uguale a quella precedente
            if (!encoder.matches(theCustomer.getPassword(), user.getPassword()) && !theCustomer.getPassword().equals(user.getPassword())) {
                theCustomer.setPassword(encoder.encode(theCustomer.getPassword())); //password criptata
                usersService.saveCustomer(theCustomer);
            } else {
                usersService.saveCustomer(theCustomer);
            }
        }else{
            theCustomer.setPassword(encoder.encode(theCustomer.getPassword())); //password criptata
            usersService.saveCustomer(theCustomer);
        }

        return "redirect:/listCustomer";
    }


    //GET
    @GetMapping("/listCustomer")
    public String listCustomers(Model theModel) {
        List<Users> theCustomers = usersService.getCustomers();
        theModel.addAttribute("customers", theCustomers);
        theModel.addAttribute("titolo", "Lista customer presenti");
        theModel.addAttribute("ToSearch", "Cerca customer nella lista");
        return "adminHome";
    }

    @GetMapping("/newCustomer")
    public String showFormForAdd(Model theModel) {
        Users theCustomer = new Users();
        theModel.addAttribute("customer", theCustomer);
        theModel.addAttribute("titolo", "Creazione nuovo customer");
        theModel.addAttribute("button", "Inserisci Utente");
        theModel.addAttribute("ToSearch", "hidden");
        return "manageCustomer";
    }


    @GetMapping("/viewProfile")
    public String viewProfile(Model theModel, @RequestParam("customerId") int theId) {
        Users theCustomer = usersService.getCustomer(theId);
        theModel.addAttribute("customerId", theId);
        theModel.addAttribute("customer", theCustomer);
        theModel.addAttribute("titolo", "Profilo utente");
        theModel.addAttribute("button", "Modifica Dati");
        theModel.addAttribute("ToSearch", "hidden");
        return "manageCustomer";
    }

    @GetMapping("/myProfile")
    public String myProfile(Model theModel) {
        //recupera l'id dalla sessione del ContextHolder
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Users userId = usersService.getEmailBySurname(principal.getUsername());

        Users theCustomer = usersService.getCustomer(userId.getId());
        theModel.addAttribute("customerId", userId.getId());
        theModel.addAttribute("customer", theCustomer);
        theModel.addAttribute("titolo", "Dettagli Profilo");
        theModel.addAttribute("button", "Modifica Dati");
        theModel.addAttribute("ToSearch", "hidden");
        return "manageCustomer";
    }

    @GetMapping("/searchUsers")
    public String searchUsers(Model theModel, @RequestParam("filter") String filter) {
        List<Users> theCustomer = usersService.getByString(filter);
        theModel.addAttribute("customers", theCustomer);
        theModel.addAttribute("titolo", "Lista customer presenti");
        theModel.addAttribute("ToSearch", "Cerca customer nella lista");
        return "adminHome";
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("customerId") int theId) {
        usersService.deleteCustomer(theId);
        return "redirect:/listCustomer";
    }
}

