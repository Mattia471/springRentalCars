package com.example.rentalspring.controller;

import com.example.rentalspring.domain.Users;
import com.example.rentalspring.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UsersController {
    @Autowired
    private UsersService usersService;

    //POST
    @PostMapping(value = "/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Users theCustomer) {
        usersService.saveCustomer(theCustomer);
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
        theModel.addAttribute("customer", theCustomer);
        theModel.addAttribute("titolo", "Profilo utente");
        theModel.addAttribute("button", "Modifica Dati");
        theModel.addAttribute("ToSearch", "hidden");
        return "viewProfile";
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

