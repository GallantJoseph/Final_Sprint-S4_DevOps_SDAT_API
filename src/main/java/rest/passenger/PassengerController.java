package rest.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passengers")
@CrossOrigin
public class PassengerController {
    
    @Autowired
    private PassengerService passengerService;

    @PostMapping
    public ResponseEntity<Passenger> createPassenger(
            @RequestBody Passenger passenger,
            @RequestParam("city_id") Long cityId,
            @RequestParam(value = "flight_id", required = false) Long flightId) {  // NEW

        Passenger newPassenger = passengerService.createPassenger(passenger, cityId, flightId);
        return ResponseEntity.ok(newPassenger);
    }

    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable long id) {
        return passengerService.getPassengerById(id);
    }

    @GetMapping
    public Iterable<Passenger> getAllPassengers(){
        return passengerService.getAllPassengers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable long id, @RequestBody Passenger passenger, @RequestParam("city_id") Long cityId) {
        return ResponseEntity.ok(passengerService.updatePassenger(id, passenger, cityId));
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable long id) {
        passengerService.deletePassenger(id);
    }
}


