package be.thomasmore.party.model;

import be.thomasmore.party.repositories.PartyRepository;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Venue {
    @Id
    private int id;
    private String venue_name, link_more_info, city;
    private boolean food_Provided, indoor, outdoor, free_Parking_available;
    private int distance_from_public_transport_in_km, capacity;


    public Venue(){

    }

    public Venue(String venuename, String linkmoreinfo, int capacity, boolean foodProvided, boolean indoor, boolean outdoor, boolean freeParking, String city, int distancePublicTransport) {
        this.venue_name = venuename;
        this.link_more_info = linkmoreinfo;
        this.capacity = capacity;
        this.food_Provided = foodProvided;
        this.indoor = indoor;
        this.outdoor = outdoor;
        this.free_Parking_available = freeParking;
        this.city = city;
        this.distance_from_public_transport_in_km = distancePublicTransport;

    }

    public int getCapacity() {
        return capacity;
    }

    public int getId() {
        return id;
    }

    public void setLinkmoreinfo(String linkmoreinfo) {
        this.link_more_info = linkmoreinfo;
    }

    public void setVenuename(String venuename) {
        this.venue_name = venuename;
    }

    public String getLinkmoreinfo() {
        return link_more_info;
    }

    public String getVenuename() {
        return venue_name;
    }

    public String isFreeParking() {
        String x = "no";
        if(free_Parking_available){x="yes";}
        return x;
    }

    public String isOutdoor() {
        String x= "no";
        if(outdoor){x="yes";}
        return x;
    }

    public String isIndoor() {
        String x= "no";
        if(indoor){x="yes";}
        return x;
    }

    public String isFoodProvided() {
        String x="no";
        if(food_Provided){x="yes";}
        return x;
    }

    public int getDistancePublicTransport() {
        return distance_from_public_transport_in_km;
    }

    public String getCity() {
        return city;
    }
}
