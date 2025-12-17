package rest.flight;

import jakarta.persistence.*;
import rest.aircraft.Aircraft;
import rest.airport.Airport;
import rest.gate.Gate;
import rest.passenger.Passenger;
import rest.airline.Airline;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flight {
    @Id
    @SequenceGenerator(name = "flight_sequence", sequenceName = "flight_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_sequence")
    private Long id;


    // If we wanna manage passengers on this specific flight
    @ManyToMany
    @JoinTable(
            name = "flight_passenger",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id")
    )
    private List<Passenger> passengers = new ArrayList<>();

    @ManyToOne
    private Airport departureAirport;

    @ManyToOne
    private Airport arrivalAirport;

    @ManyToOne
    private Gate departureGate;

    @ManyToOne
    private Gate arrivalGate;


    @ManyToOne
    private Aircraft aircraft;

    @ManyToOne
    private Airline airline;

    private String status;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public Flight() {
    }

    public Flight(List<Passenger> passengers, Airport departureAirport, Airport arrivalAirport, Gate departureGate, Gate arrivalGate, Aircraft aircraft, Airline airline, String status, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.passengers = passengers;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureGate = departureGate;
        this.arrivalGate = arrivalGate;
        this.aircraft = aircraft;
        this.airline = airline;
        this.status = status;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
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

    public Gate getDepartureGate() {
        return departureGate;
    }

    public void setDepartureGate(Gate departureGate) {
        this.departureGate = departureGate;
    }

    public Gate getArrivalGate() {
        return arrivalGate;
    }

    public void setArrivalGate(Gate arrivalGate) {
        this.arrivalGate = arrivalGate;
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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
