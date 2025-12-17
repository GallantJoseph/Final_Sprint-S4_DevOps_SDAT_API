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

    @PostMapping
    public ResponseEntity<Gate> createGate(@RequestBody Gate gate, @RequestParam("airport_id") Long airportId) {
        Gate newGate = gateService.createGate(gate, airportId);
        return  ResponseEntity.ok(newGate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gate> updateGateById(@PathVariable Long id, @RequestBody Gate gate, @RequestParam(value = "airport_id", required = false) Long airportId) {
        Gate updatedGate = gateService.updateGate(id, gate, airportId);

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