package rest.flight;

import jakarta.persistence.*;
import rest.aircraft.Aircraft;
import rest.airline.Airline;
import rest.airport.Airport;
import rest.gate.Gate;

@Entity
public class Flight {
    @Id
    @SequenceGenerator(name = "flight_sequence", sequenceName = "flight_sequence", allocationSize = 1)
    @GeneratedValue(generator = "flight_sequence")
    private Long id;

    @ManyToOne
    private Airport departureAirport;

    @ManyToOne
    private Airport arrivalAirport;

    @ManyToOne
    private Gate gate;

    @ManyToOne
    private Airline airline;

    @ManyToOne
    private Aircraft aircraft;

    private String status;

    @SuppressWarnings("unused")
    public Flight() {
    }

    @SuppressWarnings("unused")
    public Flight(Airport departureAirport, Airport arrivalAirport, Gate gate, Aircraft aircraft, String status){
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.gate = gate;
        this.aircraft = aircraft;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
