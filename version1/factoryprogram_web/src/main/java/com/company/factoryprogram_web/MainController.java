package com.company.factoryprogram_web;


import com.company.factoryprogram_web.data.DataManager;
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
    public String factory(Model model) {
        model.addAttribute("configurations", repo.getConfigurations());
        return "factory";
    }


    @GetMapping("/factory_temporary")
    public String factory() {
        return "factory_temporary";
    }

    @GetMapping("/storage")
    public String storage() {
        return "storage";
    }

    @GetMapping("/chosen_configuration/{id}")
    public String chosenConfiguration(@PathVariable int id, Model model) {

        model.addAttribute("configuration", repo.getConfiguration(id));
        var dataManager = new DataManager();
        model.addAttribute("requiredParts", dataManager.getRequiredParts(id));
        return "chosen_configuration";
    }

    @GetMapping("/confirmation/{id}")
    public String chosenConfigurationConfirmation(@PathVariable int id, Model model) {
        model.addAttribute("configuration", repo.getConfiguration(id));
        var dataManager = new DataManager();
        model.addAttribute("availableParts", dataManager.getRequiredAndAvailableParts(id));
        model.addAttribute("availability", dataManager.checkAvailability(id));
        return "confirmation";
    }


}
