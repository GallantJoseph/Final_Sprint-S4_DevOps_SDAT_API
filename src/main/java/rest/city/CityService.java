package rest.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public City getCityById(long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public Iterable<City> getAllCities(){
        return cityRepository.findAll();
    }

    public City updateCity(long id, City updatedCity) {
        Optional<City> cityToUpdateOptional = cityRepository.findById(id);

        if (cityToUpdateOptional.isPresent()) {
            City cityToUpdate = cityToUpdateOptional.get();

            cityToUpdate.setName(updatedCity.getName());
            cityToUpdate.setProvince(updatedCity.getProvince());
            cityToUpdate.setPopulation(updatedCity.getPopulation());

            return cityRepository.save(cityToUpdate);
        }

        return null;
    }

    public void deleteCity(long id) {
        cityRepository.deleteById(id);
    }
}
