package rest.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.aircraft.Aircraft;
import rest.aircraft.AircraftRepository;

import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    public Iterable<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight addNewFlight(Flight flight, Long aircraftId) {
        if (aircraftId != null) {
            Optional<Aircraft> aircraft = aircraftRepository.findById(aircraftId);

            aircraft.ifPresent(flight::setAircraft);
        }

        return flightRepository.save(flight);
    }

    public Flight deleteFlight(Long id) {
        Flight deleted = flightRepository.findById(id).orElse(null);
        flightRepository.deleteById(id);
        return deleted;
    }

    public Flight updateFlight(Long id, Flight flight, Long aircraftId) {
        Flight flightToUpdate = flightRepository.findById(id).orElse(null);
        Aircraft aircraft;

        if  (flightToUpdate == null) {
            return null;
        }

        if (aircraftId != null) {
            aircraft = aircraftRepository.findById(aircraftId).orElse(null);

            if (aircraft != null) {
                flightToUpdate.setAircraft(aircraft);
            } else {
                flightToUpdate.setAircraft(flightToUpdate.getAircraft());
            }
        }

        if (flight.getDepartureAirport() != null) {
            flightToUpdate.setDepartureAirport(flight.getDepartureAirport());
        }
        if (flight.getArrivalAirport() != null) {
            flightToUpdate.setArrivalAirport(flight.getArrivalAirport());
        }
        if (flight.getGate() != null) {
            flightToUpdate.setGate(flight.getGate());
        }
        if (flight.getAircraft() != null) {
            flightToUpdate.setAircraft(flight.getAircraft());
        }
        if (flight.getStatus() != null) {
            flightToUpdate.setStatus(flight.getStatus());
        }

        return flightRepository.save(flightToUpdate);
    }
}

