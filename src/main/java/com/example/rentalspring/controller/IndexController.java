package com.example.rentalspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController
{
    @RequestMapping(value="index")
    public String getWelcome(Model model)
    {
        model.addAttribute("intestazione", "Benvenuti nella web app Rental Cars");
        model.addAttribute("saluti", "Seleziona le auto da noleggiare");

        return "welcome";
    }

    @RequestMapping
    public String getWelcome2(Model model)
    {
        model.addAttribute("intestazione", "Benvenuti nella web app Rental Cars");
        model.addAttribute("saluti", "Seleziona le auto da noleggiare");

        return "index";
    }

}
