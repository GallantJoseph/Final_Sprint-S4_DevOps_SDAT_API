package rest.airline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import rest.city.City;
import rest.city.CityRepository;

import java.util.Optional;

@Service
public class AirlineService {
    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private CityRepository cityRepository;

    public Airline createAirline(Airline newAirline, long cityId){
        Optional<City> city = cityRepository.findById(cityId);

        city.ifPresent(newAirline::setCity);
        return airlineRepository.save(newAirline);
    }

    public Iterable<Airline> getAllAirlines(){
        return airlineRepository.findAll();
    }

    public Airline getAirlineById(long id) {
        return airlineRepository.findById(id).orElse(null);
    }

    public Airline updateAirline(long id, Airline airline, long cityId) {
        Optional<Airline> airlineFromDB = airlineRepository.findById(id);
        Optional<City> city = cityRepository.findById(cityId);

        if (airlineFromDB.isPresent()) {
            Airline airlineToUpdate = airlineFromDB.get();

            airlineToUpdate.setName(airline.getName());
            airlineToUpdate.setCode(airline.getCode());

            if (city.isPresent())
                airlineToUpdate.setCity(city.get());
             else
                airlineToUpdate.setCity(airline.getCity());

            airlineRepository.save(airlineToUpdate);
        }

        return airlineRepository.findById(id).orElse(null);
    }

    public void deleteAirlineById(Long id){
        airlineRepository.deleteById(id);
    }
}
