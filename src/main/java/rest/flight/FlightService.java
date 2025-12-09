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
}

