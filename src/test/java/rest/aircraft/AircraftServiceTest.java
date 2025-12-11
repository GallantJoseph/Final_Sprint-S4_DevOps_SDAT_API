package rest.aircraft;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rest.airline.Airline;
import rest.airline.AirlineRepository;
import rest.city.City;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AircraftServiceTest {
    @Mock
    private AircraftRepository aircraftRepository;

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private AircraftService aircraftService;

    @Test
    void testGetAircraftById() {
        City city = new City("St. John's", "NL", 150000);
        city.setId(1L);

        Airline airline = new Airline("St. John's International Airport", "SSJ", city);
        airline.setId(1L);

        Aircraft aircraftUnderTest = new Aircraft("Boeing 737", 150, airline);
        aircraftUnderTest.setAirline(airline);
        aircraftUnderTest.setId(1L);

        Mockito.when(aircraftRepository.findById(1L)).thenReturn(Optional.of(aircraftUnderTest));

        Aircraft returnedAircraft = aircraftService.getAircraftById(1L);

        assertEquals(aircraftUnderTest, returnedAircraft);
        Mockito.verify(aircraftRepository).findById(1L);
    }

    @Test
    void testCreateAircraft() {
        City city = new City("St. John's", "NL", 150000);
        city.setId(1L);

        Airline airline = new Airline("St. John's International Airport", "SSJ", city);
        airline.setId(1L);

        Aircraft aircraftUnderTest = new Aircraft("Boeing 737", 150, airline);
        aircraftUnderTest.setId(1L);
        aircraftUnderTest.setAirline(airline);

        Mockito.when(airlineRepository.findById(1L)).thenReturn(Optional.of(airline));
        Mockito.when(aircraftRepository.save(aircraftUnderTest)).thenReturn(aircraftUnderTest);

        Aircraft returnedAircraft = aircraftService.createAircraft(aircraftUnderTest, 1L);

        assertEquals(aircraftUnderTest, returnedAircraft);
    }

    @Test
    void testUpdateAircraft() {
        City city = new City("St. John's", "NL", 150000);
        city.setId(1L);

        Airline airline = new Airline("St. John's International Airport", "SSJ", city);
        airline.setId(1L);

        Aircraft aircraftUnderTest = new Aircraft("Boeing 737", 100, airline);
        aircraftUnderTest.setId(1L);
        aircraftUnderTest.setAirline(airline);

        Mockito.when(airlineRepository.findById(1L)).thenReturn(Optional.of(airline));
        Mockito.when(aircraftRepository.findById(1L)).thenReturn(Optional.of(aircraftUnderTest));
        Mockito.when(aircraftRepository.save(aircraftUnderTest)).thenReturn(aircraftUnderTest);

        Aircraft updatedAircraft = new Aircraft("Airbus A330", 100, airline);
        updatedAircraft.setId(1L);
        aircraftUnderTest.setAirline(airline);

        Aircraft returnedAircraft = aircraftService.updateAircraft(1L, updatedAircraft, 1L);

        assertEquals(updatedAircraft.getType(), returnedAircraft.getType());
    }
}