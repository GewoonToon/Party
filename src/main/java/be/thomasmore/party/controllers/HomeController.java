package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private int mySpecialNumber;

    @GetMapping({"/", "/home"})
    public String home(Model model){
        mySpecialNumber = (int) (Math.random()*11+0);
        model.addAttribute("mySpecialNumber", mySpecialNumber);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        mySpecialNumber = (int) (Math.random()*11+0);
        model.addAttribute("mySpecialNumber", mySpecialNumber);
        return "about";
    }
}
