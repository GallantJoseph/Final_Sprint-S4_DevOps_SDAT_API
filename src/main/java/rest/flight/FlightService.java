package rest.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public Iterable<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight addNewFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight deleteFlight(long id) {
        Flight deleted = flightRepository.findById(id).orElse(null);
        flightRepository.deleteById(id);
        return deleted;
    }

    public Flight updateFlight(long id, Flight flight) {
        Flight toUpdate = flightRepository.findById(id).orElse(null);
        if  (toUpdate == null) {
            return null;
        }

        if (flight.getDepartureAirport() != null) {
            toUpdate.setDepartureAirport(flight.getDepartureAirport());
        }
        if (flight.getArrivalAirport() != null) {
            toUpdate.setArrivalAirport(flight.getArrivalAirport());
        }
        if (flight.getGate() != null) {
            toUpdate.setGate(flight.getGate());
        }
        if (flight.getAircraft() != null) {
            toUpdate.setAircraft(flight.getAircraft());
        }
        if (flight.getStatus() != null) {
            toUpdate.setStatus(flight.getStatus());
        }

        return flightRepository.save(toUpdate);
    }
}

