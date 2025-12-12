package rest.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.city.City;
import rest.city.CityRepository;

import java.util.Optional;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CityRepository cityRepository;

    public Iterable<Airport> getAllAirportsByCityId(Long cityId){
        return airportRepository.findAllAirportsByCityId(cityId);
    }

    public Iterable<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    public Airport addNewAirport(Airport airport, Long city_id) {
        cityRepository.findById(city_id).ifPresent(airport::setCity);
        return airportRepository.save(airport);
    }

    public Airport removeAirport(Long id) {
        Airport deleted = airportRepository.findById(id).orElse(null);
        if (deleted != null) {
            airportRepository.delete(deleted);
        }
        return deleted;
    }

    public Airport updateAirport(Long id, Airport airport) {
        Airport airportToUpdate = airportRepository.findById(id).orElse(null);
        if (airportToUpdate != null) {
            if (airport.getName() != null) {
                airportToUpdate.setName(airport.getName());
            }
            if (airport.getCity() != null) {
                airportToUpdate.setCity(airport.getCity());
            }
            if (airport.getCode() != null) {
                airportToUpdate.setCode(airport.getCode());
            }

            airportRepository.save(airportToUpdate);
        }
        return airportRepository.findById(id).orElse(null);
    }
}
