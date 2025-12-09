package rest.airline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/airlines")
@CrossOrigin
public class AirlineController {
    @Autowired
    private AirlineService airlineService;

    @GetMapping
    public ResponseEntity<Iterable<Airline>> getAllAirlines(){
        return ResponseEntity.ok(airlineService.getAllAirlines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airline> getAirlineById(@PathVariable Long id){
        Airline airline = airlineService.getAirlineById(id);

        if (airline == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(airline);
    }

    @PostMapping
    public ResponseEntity<Airline> createAirline(@RequestBody Airline airline, @RequestParam("city_id") long cityId){
        return ResponseEntity.ok(airlineService.createAirline(airline, cityId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airline> updateAirline(@PathVariable Long id, @RequestBody Airline airline, @RequestParam("city_id") long cityId){
        Airline updated = airlineService.updateAirline(id, airline, cityId);

        if (updated == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirlineById(@PathVariable Long id){
        airlineService.deleteAirlineById(id);

        return ResponseEntity.noContent().build();
    }
}
