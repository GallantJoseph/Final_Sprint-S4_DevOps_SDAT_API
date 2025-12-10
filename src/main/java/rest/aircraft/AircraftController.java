package rest.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.airport.Airport;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/aircraft")
@CrossOrigin
public class AircraftController {
    @Autowired
    private AircraftService aircraftService;

    @GetMapping
    public ResponseEntity<Iterable<Aircraft>> getAllAircraft(){
        return ResponseEntity.ok(aircraftService.getAllAircraft());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aircraft> getAircraftById(@PathVariable Long id){
        Aircraft aircraft = aircraftService.getAircraftById(id);
        if (aircraft == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aircraft);
    }

    @PostMapping
    public ResponseEntity<Aircraft> createAircraft(@RequestBody Aircraft aircraft,
                                                   @RequestParam("airline_id") Long airlineId){
        return ResponseEntity.ok(aircraftService.createAircraft(aircraft, airlineId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aircraft> updateAircraft(@PathVariable Long id, @RequestBody Aircraft aircraft,
                                                   @RequestParam("airline_id") Long airlineId){
        Aircraft updated = aircraftService.updateAircraft(id, aircraft, airlineId);
        if (updated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAircraftById(@PathVariable Long id){
        aircraftService.deleteAircraftById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/passenger/{id}")
    public ResponseEntity<Iterable<Aircraft>> getAllAircraftByPassengerId(@PathVariable Long id){
        return ResponseEntity.ok(aircraftService.getAllAircraftByPassengerId(id));
    }

    @GetMapping("/{id}/airports")
    public ResponseEntity<List<Airport>> getAirportsByAircraftId(@PathVariable Long id){
        List<Airport> airport = aircraftService.getAirportsByAircraftId(id);
        if (airport == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(airport);
    }

    @PostMapping("/{id}/airports/{airportId}")
    public ResponseEntity<Void> linkAirportByAircraft(@PathVariable Long id, @PathVariable Long airportId){
        aircraftService.addAirportToAircraft(id, airportId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/passengers/{passengerId}")
    public ResponseEntity<Void> linkPassengerToAircraft(@PathVariable Long id, @PathVariable Long passengerId){
        aircraftService.addPassengerToAircraft(id, passengerId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/passengers/{passengerId}")
    public ResponseEntity<Void> unlinkPassengerFromAircraft(@PathVariable Long id, @PathVariable Long passengerId){
        aircraftService.removePassengerFromAircraft(id, passengerId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/airports/{airportId}")
    public ResponseEntity<Void> unlinkAirportFromAircraft(@PathVariable Long id, @PathVariable Long airportId){
        aircraftService.removeAirportFromAircraft(id, airportId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/passengers/airports")
    public Set<Airport> getAirportsUsedByAllPassengers() {
        return aircraftService.getAirportsUsedByAllPassengers();
    }
}