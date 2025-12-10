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

    public Flight getFlightById(long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight addNewFlight(Flight flight, Long aircraftId) {
        if (aircraftId != null) {
            Optional<Aircraft> aircraft = aircraftRepository.findById(aircraftId);

            aircraft.ifPresent(flight::setAircraft);
        }

        return flightRepository.save(flight);
    }

    public Flight deleteFlight(long id) {
        Flight deleted = flightRepository.findById(id).orElse(null);
        flightRepository.deleteById(id);
        return deleted;
    }

    public Flight updateFlight(long id, Flight flight, Long aircraftId) {
        Flight flightToUpdate = flightRepository.findById(id).orElse(null);
        Optional<Aircraft> aircraft;

        if  (flightToUpdate == null) {
            return null;
        }

        if (aircraftId != null) {
            aircraft = aircraftRepository.findById(aircraftId);

            if (aircraft.isPresent()) {
                flightToUpdate.setAircraft(aircraft.get());
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

