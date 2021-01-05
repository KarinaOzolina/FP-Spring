package com.company.factoryprogram_web;


import com.company.factoryprogram_web.data.DataManager;
import com.company.factoryprogram_web.data.PartRepository;
import com.company.factoryprogram_web.data.RequiredQuantity;
import com.company.factoryprogram_web.data.Storage;
import com.company.factoryprogram_web.dto.AvailabilityDto;
import com.company.factoryprogram_web.dto.OrderUpdateDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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
        var storage = repo.getStorageData(id);
        model.addAttribute("storage", storage);
//
        return "confirmation";
    }


//    @PostMapping("/confirmation/{id}")
//    public String orderParts(@PathVariable int id, @ModelAttribute("updateDto") OrderUpdateDto dto, Model model) {
//        var dataManager = new DataManager();
//
//        var required = repo.getConfiguration(id);
//        var storage = repo.getStorageData(id);
//        dataManager.orderParts(storage);
//
////        storage.setAvailQty(dto.getAvailQty());
//
//        repo.update(storage);
//
//        model.addAttribute("configuration", repo.getConfiguration(id));
//
//        model.addAttribute("availableParts", dataManager.getRequiredAndAvailableParts(id));
//        model.addAttribute("storage", storage);
//
//
//        return "confirmation";
//    }



    @PostMapping("/confirmation/{id}")
    public ModelAndView updatePartAvailability(@PathVariable int id, @ModelAttribute("updateAvailability") OrderUpdateDto updateDto){



   // public ModelAndView updatePartAvailability(@PathVariable int id, @ModelAttribute("orderResult") OrderUpdateDto updateDto, Model model){
//        model.addAttribute("configuration", repo.getConfiguration(id));
//
//
//        var availQty = repo.orderParts(id);
//        model.addAttribute("orderResult", repo.update(availQty));

//
//        var updatedAvailQty;
//
//
//        var resultItem = repo.orderParts(id);
//        List<Object> listItems = new ArrayList<>();
//
//        int i = 1;
//        for (var newAvailQty :
//                resultItem) {
//            listItems.add(new OrderUpdateDto(newAvailQty, i));
//            ++i;
//        }
//        repo.update(listItems);
//
        return new ModelAndView("redirect:/confirmation/" + id);
    }






}
