package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface ArtistRepository extends CrudRepository<Artist, Integer> {

    @Query("SELECT a from Artist a where :keyword is null or upper(a.artist_Name) LIKE upper(CONCAT('%',:keyword,'%'))")
    Iterable<Artist> findByKeyword(@Param("keyword")String keyword);
}
