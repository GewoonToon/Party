package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.repositories.PartyRepository;
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

    private Logger logger = LoggerFactory.getLogger(AdminController.class);


    @GetMapping("/partyedit/{id}")
    public String partyedit(Model model, @PathVariable Optional<Integer> id){
        Party party = null;
        ArrayList<String> errors = new ArrayList<>();
        int partyIndex=1;
        if(id.isPresent()){
            partyIndex = id.get();
        }

        else{errors.add("Geef een nummer");}

        if (partyIndex<1 || partyIndex > partyRepository.count()){
            errors.add("Geef een nummer dat bestaat");
        }

        Optional<Party> optionalParty = partyRepository.findById(partyIndex);

        if (errors.isEmpty() && optionalParty.isPresent()){
            party = optionalParty.get();}


        model.addAttribute("index",partyIndex);
        model.addAttribute("errors", errors);
        model.addAttribute("party",party);
        return "admin/partyedit";
    }

    @PostMapping("/partyedit/{id}")
    public String partyEditPost(Model model, @PathVariable Optional<Integer> id,
                                @RequestParam String name,
                                @RequestParam int pricepre,
                                @RequestParam int price){
        logger.info("partyEditPost "+id);
        logger.info("party newName "+name);
        logger.info("party newPricePre "+pricepre);
        logger.info("party newPrice "+price);
        Party party = null;
        ArrayList<String> errors = new ArrayList<>();
        int partyIndex=1;
        if(id.isPresent()){
            partyIndex = id.get();
        }

        else{errors.add("Geef een nummer");}

        if (partyIndex<1 || partyIndex > partyRepository.count()){
            errors.add("Geef een nummer dat bestaat");
        }

        Optional<Party> optionalParty = partyRepository.findById(partyIndex);

        if (errors.isEmpty() && optionalParty.isPresent()){
            party = optionalParty.get();
            party.setName(name);
            party.setPrice_In_Eur(price);
            party.setPrice_Presale_In_Eur(pricepre);
            partyRepository.save(party);
        }

        model.addAttribute("index",partyIndex);
        model.addAttribute("errors", errors);
        model.addAttribute("party",party);
        return "admin/partyedit";

    }
}
