package rest.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/airports")
@CrossOrigin
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public Iterable<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/{id}")
    public Airport getAirportById(@PathVariable("id") long id) {
        return airportService.getAirportById(id);
    }

    @GetMapping("/city/{id}")
    public Iterable<Airport> getAllAirportsByCityId(@PathVariable("id") long id) {
        return airportService.getAllAirportsByCityId(id);
    }

    @PostMapping
    public Airport addNewAirport(@RequestBody Airport airport, @RequestParam("city_id") long city_id) { return airportService.addNewAirport(airport, city_id); }

    @DeleteMapping("/{id}")
    public Airport deleteAirport(@PathVariable("id") long id) {
        return airportService.removeAirport(id);
    }

    @PutMapping("/{id}")
    public Airport updateAirport(@PathVariable("id") long id, @RequestBody Airport airport, @RequestParam("city_id") Long city_id) {
        return airportService.updateAirport(id, airport, city_id);
    }
}
