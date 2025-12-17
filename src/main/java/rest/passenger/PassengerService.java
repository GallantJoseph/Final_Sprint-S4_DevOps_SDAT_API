package rest.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.city.City;
import rest.city.CityRepository;
import rest.flight.Flight;
import rest.flight.FlightRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private FlightRepository flightRepository;  // ADD THIS

    public Passenger createPassenger(Passenger newPassenger, Long cityId, Long flightId) {
        if (cityId != null) {
            Optional<City> city = cityRepository.findById(cityId);
            city.ifPresent(newPassenger::setCity);
        }

        Passenger saved = passengerRepository.save(newPassenger);

        // NEW: If flightId provided, book the passenger on that flight
        if (flightId != null) {
            Flight flight = flightRepository.findById(flightId).orElse(null);
            if (flight != null) {
                if (flight.getPassengers() == null) {
                    flight.setPassengers(new ArrayList<>());
                }
                flight.getPassengers().add(saved);
                flightRepository.save(flight);
            }
        }

        return saved;
    }

    public Passenger getPassengerById(long id) {
        return passengerRepository.findById(id).orElse(null);
    }

    public Iterable<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger updatePassenger(long id, Passenger updatedPassenger, Long cityId) {
        Optional<Passenger> passengerOpt = passengerRepository.findById(id);

        if (passengerOpt.isPresent()) {
            Passenger passengerToUpdate = passengerOpt.get();

            passengerToUpdate.setFirstName(updatedPassenger.getFirstName());
            passengerToUpdate.setLastName(updatedPassenger.getLastName());
            passengerToUpdate.setPhone(updatedPassenger.getPhone());

            if (cityId != null) {
                Optional<City> cityOpt = cityRepository.findById(cityId);
                cityOpt.ifPresent(passengerToUpdate::setCity);
            }
            // If cityId null, keep existing city

            return passengerRepository.save(passengerToUpdate);
        }

        return null;
    }

    public void deletePassenger(long id) {
        passengerRepository.deleteById(id);
    }
}