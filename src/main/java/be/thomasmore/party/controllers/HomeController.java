package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.ArtistRepository;
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
    @Autowired
    private ArtistRepository artistRepository;



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

    @GetMapping("/venuelist/outdoor/yes")
    public String venuelistOutdoorYes(Model model){
        Iterable<Venue> venues = venueRepository.findByOutdoor(true);
        model.addAttribute("venues", venues);
        return "venuelist";
    }

    @GetMapping("/venuelist/outdoor/no")
    public String venuelistOutdoorNo(Model model){
        Iterable<Venue> venues = venueRepository.findByOutdoor(false);
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
        model.addAttribute("count",venueRepository.count());
        model.addAttribute("errors", errors);
        model.addAttribute("venue",venue);
        return "venuedetails";
    }

    @GetMapping("/artistlist")
    public String artistlist(Model model){
        Iterable<Artist> artists = artistRepository.findAll();
        model.addAttribute("artists", artists);
        return "artistlist";
    }




    @GetMapping({"/artistdetails", "/artistdetails/{id}"})
    public String artistDetailsById(Model model, @PathVariable Optional<Integer> id){
        Artist artist = null;
        ArrayList<String> errors = new ArrayList<>();
        int artistIndex=1;
        if(id.isPresent()){
            artistIndex = id.get();
        }
        else{errors.add("Geef een nummer");}
        if (artistIndex<1 || artistIndex > artistRepository.count()){
            errors.add("Geef een nummer dat bestaat");
        }

        if (errors.isEmpty() && artistRepository.findById(artistIndex).isPresent()){
            artist = artistRepository.findById(artistIndex).get();}
        model.addAttribute("index",artistIndex);
        model.addAttribute("count",artistRepository.count());
        model.addAttribute("errors", errors);
        model.addAttribute("artist",artist);
        return "artistdetails";
    }

}
