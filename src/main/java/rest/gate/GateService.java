package rest.gate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.airport.Airport;
import rest.airport.AirportRepository;

import java.util.List;

@Service
public class GateService {
    @Autowired
    private GateRepository gateRepository;

    @Autowired
    private  AirportRepository airportRepository;


    public Gate createGate(Gate newGate, Long airportId) {
        if (airportId == null) {
            throw new IllegalArgumentException("airport_id is required");
        }
        Airport airport = airportRepository.findById(airportId)
                .orElseThrow(() -> new IllegalArgumentException("Airport not found: " + airportId));

        newGate.setAirport(airport);
        return gateRepository.save(newGate);
    }

    public List<Gate> getAllGates() {
        return gateRepository.findAll();
    }

    public Gate getGateById(Long id) {
        return gateRepository.findById(id).orElse(null);
    }

    public List<Gate> getGatesByAirportId(Long airportId) {
        return gateRepository.findByAirportId(airportId);
    }

    public Gate updateGate(Long id, Gate gate, Long airportId) {
        return gateRepository.findById(id).map(gateToUpdate -> {

            gateToUpdate.setGateNumber(gate.getGateNumber());
            gateToUpdate.setStatus(gate.getStatus());

            if (airportId != null) {
                Airport airport = airportRepository.findById(airportId)
                        .orElseThrow(() -> new IllegalArgumentException("Airport not found: " + airportId));
                gateToUpdate.setAirport(airport);
            }

            return gateRepository.save(gateToUpdate);

        }).orElse(null);
    }

    public void deleteGate(Long id) {
        gateRepository.deleteById(id);
    }
}