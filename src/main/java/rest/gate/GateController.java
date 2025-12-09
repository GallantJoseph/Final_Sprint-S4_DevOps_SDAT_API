package rest.gate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/gates")
@CrossOrigin
public class GateController {
    @Autowired
    private GateService gateService;

    @GetMapping
    public ResponseEntity<List<Gate>> getAllGates() {
        List<Gate> gates = gateService.getAllGates();
        return ResponseEntity.ok(gates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gate> getGateById(@PathVariable Long id) {
        Gate gate = gateService.getGateById(id);

        if (gate == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gate);
    }

    @GetMapping("/airport/{id}")
    public ResponseEntity<List<Gate>> getAllGatesByAirportId(@PathVariable Long id) {
        List<Gate> gates = gateService.getGatesByAirportId(id);
        return ResponseEntity.ok(gates);
    }

    @GetMapping("/airport/{id}/departures")
    public ResponseEntity<List<Gate>> getDepartureGatesByAirportId(@PathVariable Long id) {
        List<Gate> departureGates = gateService.getDepartureGates(id);
        return ResponseEntity.ok(departureGates);

    }

    @GetMapping("/airport/{id}/arrivals")
    public ResponseEntity<List<Gate>> getArrivalGatesByAirportId(@PathVariable Long id) {
        List<Gate> arrivalGates = gateService.getArrivalGates(id);
        return ResponseEntity.ok(arrivalGates);
    }

    @PostMapping
    public ResponseEntity<Gate> createGate(@RequestBody Gate gate) {
        Gate newGate = gateService.createGate(gate);
        return  ResponseEntity.ok(newGate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gate> updateGateById(@PathVariable Long id, @RequestBody Gate gate) {
        Gate updatedGate = gateService.updateGate(id, gate);

        if (updatedGate == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedGate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteGateById(@PathVariable Long id) {
        gateService.deleteGate(id);

        return  ResponseEntity.noContent().build();
    }
}