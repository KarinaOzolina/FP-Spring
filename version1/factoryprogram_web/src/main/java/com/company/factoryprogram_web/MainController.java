package com.company.factoryprogram_web;


import com.company.factoryprogram_web.data.PartRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    private PartRepository repo;

    public MainController() {
        repo = new PartRepository();
    }

    @GetMapping("")
    public String index() {

        return "index";
    }

    @GetMapping("/factory")
    public String factory() {

        return "factory";
    }

    @GetMapping("/storage")
    public String storage() {

        return "storage";
    }

    @GetMapping("/chosen_configuration")
    public String chosenConfiguration(Model model) {
        var items = repo.getConfigurations();

        model.addAttribute("title", "Configuration: C1");
        model.addAttribute("configurations", items);
        return "chosen_configuration";
    }

    @GetMapping("/confirmation")
    public String confirmation() {

        return "confirmation";
    }




}
