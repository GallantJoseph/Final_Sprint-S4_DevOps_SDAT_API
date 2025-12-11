package rest.aircraft;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.airline.Airline;
import rest.airline.AirlineRepository;
import rest.airport.Airport;
import rest.airport.AirportRepository;
import rest.passenger.Passenger;
import rest.passenger.PassengerRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AircraftService {
    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private PassengerRepository passengerRepository;

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

    public Airport getCurrentLocation(Long aircraftId) {
        return aircraftRepository.findById(aircraftId)
                .map(Aircraft::getCurrentLocation)
                .orElse(null);
    }

    public List<Aircraft> getAircraftAtAirport(Long airportId) {
        return aircraftRepository.findByCurrentLocationId(airportId);
    }

    public List<Aircraft> getAircraftByAirline(Long airlineId) {
        return aircraftRepository.findByAirlineId(airlineId);
    }

}
