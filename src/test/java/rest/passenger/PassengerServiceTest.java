package rest.passenger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rest.city.City;
import rest.city.CityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PassengerServiceTest {

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private PassengerService passengerService;

    @Test
    void testCreatePassenger() {
        City city = new City("St. John's", "NL", 150000);
        city.setId(1L);

        Passenger passengerUnderTest = new Passenger("John", "Doe", "709-555-1234");
        passengerUnderTest.setId(1L);
        passengerUnderTest.setCity(city);

        Mockito.when(cityRepository.findById(1L)).thenReturn(Optional.of(city));
        Mockito.when(passengerRepository.save(passengerUnderTest)).thenReturn(passengerUnderTest);

        Passenger returnedPassenger = passengerService.createPassenger(passengerUnderTest, 1L);

        Assertions.assertEquals(passengerUnderTest, returnedPassenger);
    }

    @Test
    void testGetPassengerById() {
        City city = new City("St. John's", "NL", 150000);
        city.setId(1L);

        Passenger passengerUnderTest = new Passenger("John", "Doe", "709-555-1234");
        passengerUnderTest.setId(1L);
        passengerUnderTest.setCity(city);

        Mockito.when(passengerRepository.findById(1L)).thenReturn(Optional.of(passengerUnderTest));

        Passenger returnedPassenger = passengerService.getPassengerById(1L);

        Assertions.assertEquals(passengerUnderTest, returnedPassenger);
    }

    @Test
    void getAllPassengers() {
        City city = new City("St. John's", "NL", 150000);
        city.setId(1L);

        Passenger passengerUnderTest1 = new Passenger("John", "Doe", "709-555-1234");
        passengerUnderTest1.setId(1L);
        passengerUnderTest1.setCity(city);

        Passenger passengerUnderTest2 = new Passenger("Jane", "Smith", "709-555-4321");
        passengerUnderTest1.setId(2L);
        passengerUnderTest1.setCity(city);

        List<Passenger> passengerList = new ArrayList<>();
        passengerList.add(passengerUnderTest1);
        passengerList.add(passengerUnderTest2);

        Mockito.when(passengerRepository.findAll()).thenReturn(passengerList);

        Iterable<Passenger> returnedPassengerIterable = passengerService.getAllPassengers();

        Assertions.assertEquals(passengerList, returnedPassengerIterable);
    }
}