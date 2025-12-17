package rest.aircraft;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.airline.Airline;
import rest.airline.AirlineRepository;

import java.util.List;
import java.util.Optional;
;

@Service
public class AircraftService {
    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    public List<Aircraft> getAllAircraft() {
        return  aircraftRepository.findAll();
    }

    public Aircraft getAircraftById(Long id){
        return aircraftRepository.findById(id).orElse(null);
    }

    public Aircraft createAircraft(Aircraft newAircraft, Long airlineId){
        if (airlineId != null) {
            Optional<Airline> airlineOptional = airlineRepository.findById(airlineId);

            airlineOptional.ifPresent(newAircraft::setAirline);
        }

        return aircraftRepository.save(newAircraft);
    }

    public Aircraft updateAircraft(Long id, Aircraft updatedAircraft, Long airlineId){
        Optional<Aircraft> aircraftOptional = aircraftRepository.findById(id);
        Optional<Airline> airlineOptional;

        if (aircraftOptional.isEmpty()){
            return null;
        }

        Aircraft aircraftToUpdate = aircraftOptional.get();

        aircraftToUpdate.setType(updatedAircraft.getType());

        if (airlineId != null) {
            airlineOptional = airlineRepository.findById(airlineId);
            
            airlineOptional.ifPresent(aircraftToUpdate::setAirline);
        } else {
            aircraftToUpdate.setAirline(updatedAircraft.getAirline());
        }

        aircraftToUpdate.setNumberOfPassengers(updatedAircraft.getNumberOfPassengers());
        return aircraftRepository.save(aircraftToUpdate);
    }

    public void deleteAircraftById(Long id){
        aircraftRepository.deleteById(id);
    }

    public List<Aircraft> getAircraftByAirline(Long airlineId) {
        return aircraftRepository.findByAirlineId(airlineId);
    }

}
