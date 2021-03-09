package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface VenueRepository extends CrudRepository<Venue, Integer> {

    @Query("SELECT v FROM Venue v WHERE (:max is null or :max >= v.capacity) and (:min is null or :min <= v.capacity)")
    Iterable<Venue> finByCapacityBetween(@Param("min") Integer min, @Param("max") Integer max);

    @Query("SELECT v FROM Venue v WHERE :outdoor is null or :outdoor=true")
    Iterable<Venue> findByOutdoor(@Param("outdoor")boolean outdoor);

    @Query("SELECT v FROM Venue v WHERE v.capacity >= :min")
    Iterable<Venue> capacityGreaterThan(@Param("min") int min);

    @Query("SELECT v FROM Venue v where :max is null or :max>= v.distance_from_public_transport_in_km")
    Iterable<Venue> findByMaxDistance(@Param("max") Integer max);

    @Query("SELECT v FROM Venue v where :indoor is null or :indoor =true")
    Iterable<Venue> findByIndoor(@Param("indoor")boolean indoor);

    @Query("SELECT v FROM Venue v WHERE " +
            "((:max is null or :max >= v.capacity) and (:min is null or :min <= v.capacity)) and " +
            "(:maxd is null or :maxd>= v.distance_from_public_transport_in_km) and " +
            "(:indoor is null or :indoor =(v.indoor)) and " +
            "(:outdoor is null or :outdoor =(v.outdoor))")
    Iterable<Venue> finByCriteria(@Param("min") Integer min, @Param("max") Integer max, @Param("maxd") Integer maxd,
                                  @Param("indoor")Boolean filterIndoor, @Param("outdoor") Boolean filterOutdoor);

}
