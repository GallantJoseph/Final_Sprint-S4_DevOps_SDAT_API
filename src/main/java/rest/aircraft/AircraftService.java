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

    public Iterable<Aircraft> getAllAircraftByPassengerId (Long passengerId) {
        return aircraftRepository.findAllByPassengers_Id(passengerId);
    }

    public Set<Airport> getAirportsUsedByAllPassengers() {
        Set<Airport> result = new HashSet<>();

        Iterable<Passenger> passengers = passengerRepository.findAll();

        for (Passenger passenger : passengers) {
            Iterable<Aircraft> aircraftList = aircraftRepository.findAllByPassengers_Id(passenger.getId());

            for (Aircraft aircraft : aircraftList) {
                List<Airport> airports = aircraft.getAirports();

                if (airports != null) {
                    for (Airport airport : airports) {
                        result.add(airport);
                    }
                }
            }
        }
        return result;
    }

    public Iterable<Aircraft> getAllAircraft() {
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

    public List<Airport> getAirportsByAircraftId(Long id){
        Optional<Aircraft> aircraftOptional = aircraftRepository.findById(id);
        if (aircraftOptional.isPresent()){
            return aircraftOptional.get().getAirports();
        } else {
            return null;
        }
    }

    public void addAirportToAircraft(Long aircraftId, Long airportId){
        Optional<Aircraft> aircraftOptional = aircraftRepository.findById(aircraftId);
        Optional<Airport> airportOptional = airportRepository.findById(airportId);

        if (aircraftOptional.isPresent() && airportOptional.isPresent()) {
            Aircraft aircraft = aircraftOptional.get();
            Airport airport = airportOptional.get();
            aircraft.getAirports().add(airport);
            aircraftRepository.save(aircraft);
        }
    }

    public void addPassengerToAircraft(Long aircraftId, Long passengerId){
        Optional<Aircraft> aircraftOptional = aircraftRepository.findById(aircraftId);
        Optional<Passenger> passengerOptional = passengerRepository.findById(passengerId);

        if (aircraftOptional.isPresent() && passengerOptional.isPresent()) {
            Aircraft aircraft = aircraftOptional.get();
            Passenger passenger = passengerOptional.get();
            aircraft.getPassengers().add(passenger);
            aircraftRepository.save(aircraft);
        }
    }

    public void removePassengerFromAircraft(Long aircraftId, Long passengerId) {
        Aircraft aircraft = aircraftRepository.findById(aircraftId)
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));

        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        aircraft.getPassengers().remove(passenger);

        aircraftRepository.save(aircraft);
    }

    @Transactional
    public void removeAirportFromAircraft(Long aircraftId, Long airportId) {
        Aircraft aircraft = aircraftRepository.findById(aircraftId)
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));

        Airport airport = airportRepository.findById(airportId)
                .orElseThrow(() -> new RuntimeException("Airport not found"));

        aircraft.getAirports().remove(airport);

        aircraftRepository.save(aircraft);
    }
}
