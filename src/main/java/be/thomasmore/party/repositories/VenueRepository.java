package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface VenueRepository extends CrudRepository<Venue, Integer> {

    @Query("SELECT v FROM Venue v WHERE (:max is null or :max >= v.capacity) and (:min is null or :min <= v.capacity)")
    Iterable<Venue> finByCapacityBetween(@Param("min") Integer min, @Param("max") Integer max);

    Iterable<Venue> findByOutdoor(boolean outdoor);

    Iterable<Venue> findByIndoor(boolean indoor);

    @Query("SELECT v FROM Venue v WHERE v.capacity >= :min")
    Iterable<Venue> capacityGreaterThan(@Param("min") int min);

}
