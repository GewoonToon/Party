package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class VenueController {

    @Autowired
    private VenueRepository venueRepository;
    /*---------------------------------------------------------------VENUEFUNCTIONS---------------------------------------------------------*/

    @GetMapping({"/venuelist", "/venuelist/{optfilter}"})
    public String venueList(Model model, @PathVariable Optional<String> optfilter){
        ArrayList<String> errors = new ArrayList<>();
        Iterable<Venue> venues = venueRepository.findAll();
        boolean filter = false;
        if(optfilter.isPresent() && optfilter.get().equals("filter")){
            filter = true;
        }
        else{errors.add("Geef een filter");}


        model.addAttribute("filter", filter);
        model.addAttribute("venues", venues);
        model.addAttribute("errors", errors);
        return "venuelist";
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
            errors.add("Geef een nummer tussen 1 en " + venueRepository.count());
        }

        Optional<Venue> optionalVenue = venueRepository.findById(venueIndex);

        if (errors.isEmpty() && optionalVenue.isPresent()){
            venue = optionalVenue.get();}


        model.addAttribute("index",venueIndex);
        model.addAttribute("count",venueRepository.count());
        model.addAttribute("errors", errors);
        model.addAttribute("venue",venue);
        return "venuedetails";
    }

    /*--------------------------------------------------------VENUEFILTERS----------------------------------------------------------------------------*/

   /* @GetMapping({"/venuelist/outdoor/{filter}", "/venuelist/outdoor"})
    public String venuelistOutdoor(Model model, @PathVariable Optional<String> filter){
        String filterstring = "all";
        ArrayList<String> errors = new ArrayList<>();
        Iterable<Venue> venues = null;

        if(filter.isPresent()){
            filterstring = filter.get();
        }

        if(filterstring.equals("yes")){
            venues = venueRepository.findByOutdoor(true);
        }

        else if(filterstring.equals("no")){
            venues = venueRepository.findByOutdoor(false);
        }

        else{ errors.add("De filter is ongeldig");
        }

        model.addAttribute("venues", venues);
        model.addAttribute("errors", errors);
        model.addAttribute("filtero", filterstring);

        return "venuelist";
    }


    @GetMapping({"/venuelist/indoor/{filter}", "/venuelist/indoor"})
    public String venuelistindoor(Model model, @PathVariable Optional<String> filter){
        String filterstring = "";
        ArrayList<String> errors = new ArrayList<>();
        Iterable<Venue> venues = null;

        if(filter.isPresent()){
            filterstring = filter.get();
        }

        if(filterstring.equals("yes")){
            venues = venueRepository.findByIndoor(true);
        }

        else if(filterstring.equals("no")){
            venues = venueRepository.findByIndoor(false);
        }

        else{ errors.add("De filter is ongeldig");
        }

        model.addAttribute("venues", venues);
        model.addAttribute("errors", errors);
        model.addAttribute("filteri", filterstring);

        return "venuelist";
    }

    @GetMapping({"/venuelist/size/{filter}", "/venuelist/size"})
    public String venuelistsize(Model model, @PathVariable Optional<String> filter){
        String filterstring = "";
        ArrayList<String> errors = new ArrayList<>();
        Iterable<Venue> venues = null;

        if(filter.isPresent()){
            filterstring = filter.get();
        }

        if(filterstring.equals("S")){
            venues = venueRepository.capacityBetween(0,200);
        }

        else if(filterstring.equals("M")){
            venues = venueRepository.capacityBetween(200,500);
        }

        else if(filterstring.equals("L")){
            venues = venueRepository.capacityGreaterThan(600);
        }

        else{ errors.add("De filter is ongeldig");
        }

        model.addAttribute("venues", venues);
        model.addAttribute("errors", errors);
        model.addAttribute("filters", filterstring);

        return "venuelist";
    }
*/
}
