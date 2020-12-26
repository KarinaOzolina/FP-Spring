package com.company.factoryprogram_web;


import com.company.factoryprogram_web.data.PartRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private PartRepository repo;

    public MainController(){
        repo = new PartRepository();
    }


    @GetMapping("")
    public String index(Model model){

        model.addAttribute("parts", repo.getParts());

        return "index";
    }
}
