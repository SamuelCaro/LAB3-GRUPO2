package com.example.laboratorio3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {

    //COMPLETAR

    @GetMapping(value = {"", "/"})
    public String indice() {
        return "Search/indice";
    }


    @GetMapping(value = {"/empMaSal"})
    public String mayorSalario() {


        return "Search/empleadoMayorSalario";
    }
}