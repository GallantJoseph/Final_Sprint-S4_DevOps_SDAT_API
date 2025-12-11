package rest.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.city.City;
import rest.city.CityService;

import java.util.Optional;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private CityService cityService;

    public Passenger createPassenger(Passenger passenger, Long cityId) {
            City city;

            if (cityId != null) {
                city = cityService.getCityById(cityId);

                if (city != null) {
                    passenger.setCity(city);
                }
            }
            return passengerRepository.save(passenger);
    }

    public Passenger getPassengerById(long id) {
        return passengerRepository.findById(id).orElse(null);
    }

    public Iterable<Passenger> getAllPassengers(){
        return passengerRepository.findAll();
    }

    public Passenger updatePassenger(long id, Passenger updatedPassenger, Long cityId) {
        Optional<Passenger> passengerToUpdateOptional = passengerRepository.findById(id);

        if (passengerToUpdateOptional.isPresent()) {
            City city = null;
            Passenger passengerToUpdate = passengerToUpdateOptional.get();

            if (cityId != null) {
                city = cityService.getCityById(cityId);
            }

            if (city == null) {
                city = passengerToUpdate.getCity();
            }

            passengerToUpdate.setFirstName(updatedPassenger.getFirstName());
            passengerToUpdate.setLastName(updatedPassenger.getLastName());
            passengerToUpdate.setCity(city);

            return passengerRepository.save(passengerToUpdate);
        }

        return null;
    }

    public void deletePassenger(long id) {
        passengerRepository.deleteById(id);
    }
}
