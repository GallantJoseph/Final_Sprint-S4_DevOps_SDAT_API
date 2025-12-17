package rest.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.aircraft.Aircraft;
import rest.aircraft.AircraftRepository;
import rest.airline.Airline;
import rest.airline.AirlineRepository;
import rest.airport.Airport;
import rest.airport.AirportRepository;
import rest.gate.Gate;
import rest.gate.GateRepository;
import rest.passenger.Passenger;
import rest.passenger.PassengerRepository;

import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private GateRepository gateRepository;

    // ADD THIS
    @Autowired
    private PassengerRepository passengerRepository;

    public Iterable<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight addNewFlight(
            Flight flight,
            Long aircraftId,
            Long airlineId,
            Long departureAirportId,
            Long arrivalAirportId,
            Long departureGateId,
            Long arrivalGateId
    ) {
        aircraftRepository.findById(aircraftId).ifPresent(flight::setAircraft);
        airlineRepository.findById(airlineId).ifPresent(flight::setAirline);
        airportRepository.findById(departureAirportId).ifPresent(flight::setDepartureAirport);
        airportRepository.findById(arrivalAirportId).ifPresent(flight::setArrivalAirport);
        gateRepository.findById(departureGateId).ifPresent(flight::setDepartureGate);
        gateRepository.findById(arrivalGateId).ifPresent(flight::setArrivalGate);

        return flightRepository.save(flight);
    }

    public Flight updateFlight(
            Long id,
            Flight flight,
            Long aircraftId,
            Long airlineId,
            Long departureAirportId,
            Long arrivalAirportId,
            Long departureGateId,
            Long arrivalGateId
    ) {
        Flight flightToUpdate = flightRepository.findById(id).orElse(null);
        if (flightToUpdate == null) return null;

        if (aircraftId != null) aircraftRepository.findById(aircraftId).ifPresent(flightToUpdate::setAircraft);
        if (airlineId != null) airlineRepository.findById(airlineId).ifPresent(flightToUpdate::setAirline);
        if (departureAirportId != null) airportRepository.findById(departureAirportId).ifPresent(flightToUpdate::setDepartureAirport);
        if (arrivalAirportId != null) airportRepository.findById(arrivalAirportId).ifPresent(flightToUpdate::setArrivalAirport);
        if (departureGateId != null) gateRepository.findById(departureGateId).ifPresent(flightToUpdate::setDepartureGate);
        if (arrivalGateId != null) gateRepository.findById(arrivalGateId).ifPresent(flightToUpdate::setArrivalGate);

        if (flight.getStatus() != null) flightToUpdate.setStatus(flight.getStatus());
        if (flight.getDepartureTime() != null) flightToUpdate.setDepartureTime(flight.getDepartureTime());
        if (flight.getArrivalTime() != null) flightToUpdate.setArrivalTime(flight.getArrivalTime());

        return flightRepository.save(flightToUpdate);
    }

    // In FlightService.java
    public boolean deleteFlight(Long id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // NEW: Add passenger to flight
    public Flight addPassengerToFlight(Long flightId, Long passengerId) {
        Flight flight = flightRepository.findById(flightId).orElse(null);
        if (flight == null) return null;

        Passenger passenger = passengerRepository.findById(passengerId).orElse(null);
        if (passenger == null) return null;

        // Avoid duplicates
        if (!flight.getPassengers().contains(passenger)) {
            flight.getPassengers().add(passenger);
        }

        return flightRepository.save(flight);
    }

    // NEW: Remove passenger from flight
    public Flight removePassengerFromFlight(Long flightId, Long passengerId) {
        Flight flight = flightRepository.findById(flightId).orElse(null);
        if (flight == null) return null;

        Passenger passenger = passengerRepository.findById(passengerId).orElse(null);
        if (passenger == null) return null;

        flight.getPassengers().remove(passenger);

        return flightRepository.save(flight);
    }
}