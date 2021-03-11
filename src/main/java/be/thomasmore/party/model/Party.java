package be.thomasmore.party.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Party {
    @Id
    private int id;
    private String name, extra_info;
    private Integer price_Presale_In_Eur, price_In_Eur;
    @Temporal(TemporalType.DATE)
    Date date;
    @Temporal(TemporalType.TIME)
    Date doors;
    @ManyToOne
    private Venue venue;

    public Party(){}

    public Party(int id, String name, String extra_info, Integer price_Presale_In_Eur, Integer price_In_Eur, Date date, Date doors) {
        this.id = id;
        this.name = name;
        this.extra_info = extra_info;
        this.price_Presale_In_Eur = price_Presale_In_Eur;
        this.price_In_Eur = price_In_Eur;
        this.date = date;
        this.doors = doors;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExtra_info() {
        return extra_info;
    }

    public Integer getPrice_Presale_In_Eur() {
        return price_Presale_In_Eur;
    }

    public Integer getPrice_In_Eur() {
        return price_In_Eur;
    }

    public Date getDate() {
        return date;
    }

    public Date getDoors() {
        return doors;
    }

    public Venue getVenue() {
        return venue;
    }
}
