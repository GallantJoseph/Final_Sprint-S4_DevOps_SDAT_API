package rest.aircraft;

import jakarta.persistence.*;
import rest.airline.Airline;
import rest.airport.Airport;
import rest.passenger.Passenger;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Aircraft {
    @Id
    @SequenceGenerator(name = "aircraft_sequence", sequenceName = "aircraft_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aircraft_sequence")
    private Long id;
    private String type;
    private int numberOfPassengers;

    @ManyToMany
    private List<Passenger> passengers = new ArrayList<>();

    @ManyToMany
    private List<Airport> airports = new ArrayList<>();

    @ManyToOne
    private Airline airline;

    public Aircraft() {
    }
    
    public Aircraft(String type, String airlineName, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
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

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
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
