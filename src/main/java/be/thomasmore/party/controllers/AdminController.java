package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.PartyRepository;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    PartyRepository partyRepository;

    @Autowired
    VenueRepository venueRepository;

    private Logger logger = LoggerFactory.getLogger(AdminController.class);


    @GetMapping("/partyedit/{id}")
    public String partyedit(Model model, @PathVariable Optional<Integer> id){
        ArrayList<String> errors = new ArrayList<>();
        int partyIndex=1;
        if(id.isPresent()){
            partyIndex = id.get();
        }

        else{errors.add("Geef een nummer");}

        if (partyIndex<1 || partyIndex > partyRepository.count()){
            errors.add("Geef een nummer dat bestaat");
        }

        model.addAttribute("venues", venueRepository.findAll());
        model.addAttribute("index",partyIndex);
        model.addAttribute("errors", errors);
        return "admin/partyedit";
    }

    @PostMapping("/partyedit/{id}")
    public String partyEditPost(Model model, @PathVariable Optional<Integer> id,
                                @ModelAttribute Party party,
                                @RequestParam Integer venueId){
        logger.info("partyEditPost "+id);

        ArrayList<String> errors = new ArrayList<>();
        int partyIndex=1;

        if(id.isPresent()){
            partyIndex = id.get();
        }
        else{errors.add("Geef een nummer");}

        if (partyIndex<1 || partyIndex > partyRepository.count()){
            errors.add("Geef een nummer dat bestaat");
        }
        if(venueId!=null && venueId != party.getVenue().getId()){
            party.setVenue(new Venue(venueId));
        }

        partyRepository.save(party);

        model.addAttribute("index",partyIndex);
        model.addAttribute("errors", errors);
        return "redirect:/partydetails/"+partyIndex;
    }

    @ModelAttribute("party")
    public Party findParty(@PathVariable (required = false) Integer id){
        if(id==null){return null;}
        logger.info("findparty "+id);
        Optional<Party> optionalParty = partyRepository.findById(id);
        if(optionalParty.isPresent()){
            return optionalParty.get();
        }
        return null;

    }

    @GetMapping("/partynew")
    public String partyNew(Model model,
                           @RequestParam (required = false) String name,
                           @RequestParam (required = false) String extra_info,
                           @RequestParam (required = false) Integer price_Presale_In_Eur,
                           @RequestParam (required = false) Integer price_In_Eur){
        ArrayList<String> errors = new ArrayList<>();
        Party newParty = new Party(name, extra_info, price_In_Eur, price_Presale_In_Eur);
        model.addAttribute("errors", errors);
        model.addAttribute("party", newParty);
        /*model.addAttribute("name", name);
        model.addAttribute("extra_info", extra_info);
        model.addAttribute("price_Presale_In_Eur", price_Presale_In_Eur);
        model.addAttribute("price_In_Eur", price_In_Eur);*/

        return "admin/partynew";
    }

    @PostMapping("/partynew")
    public String partyNewPost(Model model,
                               @RequestParam (required = false) String name,
                               @RequestParam (required = false) String extra_info,
                               @RequestParam (required = false) Integer price_Presale_In_Eur,
                               @RequestParam (required = false) Integer price_In_Eur){
        ArrayList<String> errors = new ArrayList<>();
        Party newParty = new Party(name, extra_info, price_In_Eur, price_Presale_In_Eur);
        partyRepository.save(newParty);
        model.addAttribute("errors", errors);
        model.addAttribute("party", newParty);

        return "partylist";
    }


}
