package rest.gate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;

@Service
public class GateService {
    @Autowired
    private GateRepository gateRepository;

    public Gate createGate(Gate newGate) {
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

    public List<Gate> getDepartureGates(Long airportId) {
        return gateRepository.findByAirportId(airportId);
    }

    public List<Gate> getArrivalGates(Long airportId) {
        return gateRepository.findByAirportId(airportId);
    }

    public Gate updateGate(Long id, Gate gate) {
        Optional<Gate> gateFromDB = gateRepository.findById(id);

        if (gateFromDB.isPresent()) {
            Gate gateToUpdate = gateFromDB.get();

            gateToUpdate.setGateNumber(gate.getGateNumber());
            gateToUpdate.setStatus(gate.getStatus());
            gateToUpdate.setAirport(gate.getAirport()); // takes whatever client sent

            gateRepository.save(gateToUpdate);
        }

        return gateRepository.findById(id).orElse(null);
    }

    public void deleteGate(Long id) {
        gateRepository.deleteById(id);
    }
}