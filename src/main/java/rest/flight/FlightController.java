package rest.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.passenger.Passenger;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flights")
@CrossOrigin
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public Iterable<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    public Flight addNewFlight(
            @RequestBody Flight flight,
            @RequestParam Long aircraftId,
            @RequestParam Long airlineId,
            @RequestParam Long departureAirportId,
            @RequestParam Long arrivalAirportId,
            @RequestParam Long departureGateId,
            @RequestParam Long arrivalGateId
    ) {
        return flightService.addNewFlight(
                flight,
                aircraftId,
                airlineId,
                departureAirportId,
                arrivalAirportId,
                departureGateId,
                arrivalGateId
        );
    }

    @PutMapping("/{id}")
    public Flight updateFlight(
            @PathVariable Long id,
            @RequestBody Flight flight,
            @RequestParam(required = false) Long aircraftId,
            @RequestParam(required = false) Long airlineId,
            @RequestParam(required = false) Long departureAirportId,
            @RequestParam(required = false) Long arrivalAirportId,
            @RequestParam(required = false) Long departureGateId,
            @RequestParam(required = false) Long arrivalGateId
    ) {
        return flightService.updateFlight(
                id,
                flight,
                aircraftId,
                airlineId,
                departureAirportId,
                arrivalAirportId,
                departureGateId,
                arrivalGateId
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        boolean deleted = flightService.deleteFlight(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();

    }


    @PostMapping("/{flightId}/passengers/{passengerId}")
    public ResponseEntity<?> addPassengerToFlight(
            @PathVariable Long flightId,
            @PathVariable Long passengerId) {

        Flight flight = flightService.addPassengerToFlight(flightId, passengerId);
        if (flight == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{flightId}/passengers/{passengerId}")
    public ResponseEntity<?> removePassengerFromFlight(
            @PathVariable Long flightId,
            @PathVariable Long passengerId) {

        Flight flight = flightService.removePassengerFromFlight(flightId, passengerId);
        if (flight == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{flightId}/passengers")
    public ResponseEntity<List<Passenger>> getFlightPassengers(@PathVariable Long flightId) {
        Flight flight = flightService.getFlightById(flightId);
        if (flight == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flight.getPassengers());
    }
}