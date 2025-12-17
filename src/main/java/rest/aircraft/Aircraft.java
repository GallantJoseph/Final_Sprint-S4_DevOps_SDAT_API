package rest.aircraft;

import jakarta.persistence.*;
import rest.airline.Airline;
import rest.airport.Airport;


@Entity
public class Aircraft {
    @Id
    @SequenceGenerator(name = "aircraft_sequence", sequenceName = "aircraft_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aircraft_sequence")
    private Long id;
    private String type;
    private int numberOfPassengers;

    @ManyToOne
    private Airline airline;

    public Aircraft() {
    }

    public Aircraft(String type, int numberOfPassengers, Airline airline) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
        this.airline = airline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    @Override
    public String toString() {
        return "Aircraft( id = " + id + ", type = " + type + " )";
    }

}
