package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private VenueRepository venueRepository;


    private final DayOfWeek[] weekend = {DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};

    @GetMapping({"/", "/home"})
    public String home(Model model){
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        return "about";
    }

    @GetMapping("/pay")
    public String pay(Model model){
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("newDate", LocalDate.now().plusDays(30));
        model.addAttribute("weekend", isWeekend(weekend));
        return "pay";
    }

    @GetMapping("/venuelist")
    public String venueList(Model model){
        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        return "venuelist";
    }

    public boolean isWeekend(DayOfWeek[] weekend){
        for (DayOfWeek day : weekend){
            if (LocalDate.now().getDayOfWeek().equals(day)){return true;}

        }
        return false;
    }

    @GetMapping({"/venuedetails", "/venuedetails/{id}"})
    public String venueDetailsById(Model model, @PathVariable Optional<Integer> id){
        Venue venue = null;
        ArrayList<String> errors = new ArrayList<>();
        int venueIndex=1;
        if(id.isPresent()){
            venueIndex = id.get();
        }
        else{errors.add("Geef een nummer");}
        if (venueIndex<1 || venueIndex > venueRepository.count()){
            errors.add("Geef een nummer dat bestaat");
        }

        if (errors.isEmpty() && venueRepository.findById(venueIndex).isPresent()){
            venue = venueRepository.findById(venueIndex).get();}
        model.addAttribute("index",venueIndex);
        model.addAttribute("errors", errors);
        model.addAttribute("venue",venue);
        return "venuedetails";
    }
}
