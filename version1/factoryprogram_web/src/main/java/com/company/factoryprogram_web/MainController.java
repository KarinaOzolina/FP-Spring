package com.company.factoryprogram_web;


import com.company.factoryprogram_web.data.PartRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/chosen_configuration")
    public String chosenConfiguration(){

        return "chosen_configuration";
    }

    @GetMapping("/confirmation")
    public String confirmation(){

        return "confirmation";
    }

}
