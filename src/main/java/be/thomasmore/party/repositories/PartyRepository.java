package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface PartyRepository extends CrudRepository<Party, Integer> {

    @Query("SELECT p from Party p where :venue is null or p.venue = :venue")
    Iterable<Party> findByVenue(@Param("venue") Venue venue);

}
