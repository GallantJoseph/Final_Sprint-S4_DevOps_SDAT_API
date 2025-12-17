package rest.airport;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import rest.city.City;
import rest.city.CityRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class AirportServiceTest {
    @Mock
    private AirportRepository airportRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private AirportService airportService;

    @Test
    void testGetAirportById() {
        City city = new City("St. John's", "NL", 150000);
        city.setId(1L);

        Airport airportUnderTest = new Airport("St. John's International Airport", "YYT", city);
        airportUnderTest.setId(1L);
        airportUnderTest.setCity(city);

        Mockito.when(airportRepository.findById(1L)).thenReturn(Optional.of(airportUnderTest));

        Airport returnedAirport = airportService.getAirportById(1L);

        assertEquals(airportUnderTest, returnedAirport);
        Mockito.verify(airportRepository).findById(1L);
    }

    @Test
    void testAddNewAirport() {
        City city  = new City("St. John's", "NL", 150000);
        city.setId(1L);

        Airport airportUnderTest = new Airport("St. John's International Airport", "YYT", city);
        airportUnderTest.setId(1L);
        airportUnderTest.setCity(city);

        Mockito.when(cityRepository.findById(1L)).thenReturn(Optional.of(city));
        Mockito.when(airportRepository.save(airportUnderTest)).thenReturn(airportUnderTest);

        Airport returnedAirport = airportService.addNewAirport(airportUnderTest, 1L);

        assertEquals(airportUnderTest, returnedAirport);

        Mockito.verify(cityRepository).findById(1L);
        Mockito.verify(airportRepository).save(airportUnderTest);
    }

    @Test
    void testUpdateAirport() {
        City city = new City("St. John's", "NL", 150000);
        city.setId(1L);

        Airport resultAirport = new Airport("St. John's International Airport", "YYT", city);
        resultAirport.setId(1L);
        resultAirport.setCity(city);

        Airport airportUnderTest = new Airport("St. John's International Airport", "YYU", city);
        airportUnderTest.setId(1L);
        airportUnderTest.setCity(city);

        Mockito.when(airportRepository.findById(1L)).thenReturn(Optional.of(airportUnderTest));
        Mockito.when(airportRepository.save(airportUnderTest)).thenReturn(airportUnderTest);

        Airport airportUpdate = new Airport(null, "YYT", null);
        airportUpdate.setId(1L);

        Airport returnedAirport = airportService.updateAirport(1L, airportUpdate, null);

        assertEquals(resultAirport.getName(), returnedAirport.getName());
        assertEquals(resultAirport.getCode(), returnedAirport.getCode());
        assertEquals(resultAirport.getCity(), returnedAirport.getCity());

        assertNotNull(returnedAirport.getName());
        assertNotNull(returnedAirport.getCode());
        assertNotNull(returnedAirport.getCity());

        Mockito.verify(airportRepository, times(2)).findById(1L);
        Mockito.verify(airportRepository).save(airportUnderTest);
    }
}
