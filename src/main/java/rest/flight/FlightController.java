package rest.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/flights")
@CrossOrigin
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public Iterable<Flight> getAllFlights() {return flightService.getAllFlights();}

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable("id") long id) {return flightService.getFlightById(id);}

    @PostMapping
    public Flight addNewFlight(@RequestBody Flight flight,
                               @RequestParam("aircraft_id") Long aircraftId) {
        return flightService.addNewFlight(flight, aircraftId);
    }

    @DeleteMapping("/{id}")
    public Flight deleteFlight(@PathVariable("id") long id) {return flightService.deleteFlight(id);}

    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable("id") long id, @RequestBody Flight flight,
                               @RequestParam("aircraft_id") Long aircraftId) {
        return flightService.updateFlight(id, flight, aircraftId);
    }
}

