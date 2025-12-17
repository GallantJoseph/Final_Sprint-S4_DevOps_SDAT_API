package rest.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.airport.Airport;

import java.util.List;

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

    // All aircraft belonging to an airline
    @GetMapping("/airline/{airlineId}")
    public ResponseEntity<List<Aircraft>> getAircraftByAirline(@PathVariable Long airlineId) {
        return ResponseEntity.ok(aircraftService.getAircraftByAirline(airlineId));
    }
}