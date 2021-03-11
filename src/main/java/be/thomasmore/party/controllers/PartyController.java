package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Party;
import be.thomasmore.party.repositories.PartyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PartyController {

    @Autowired
    private PartyRepository partyRepository;

    private Logger logger = LoggerFactory.getLogger(PartyController.class);

    /*-----------------------Mappings---------------------------------------*/

    @GetMapping({"/partylist"})
    public String partyList(Model model){


        model.addAttribute("parties", partyRepository.findAll());
        return "partylist";
    }


    @GetMapping({"/partydetails", "/partydetails/{id}"})
    public String artistDetailsById(Model model, @PathVariable Optional<Integer> id){
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
        model.addAttribute("count",partyRepository.count());
        model.addAttribute("errors", errors);
        model.addAttribute("party",party);
        return "partydetails";
    }
}
