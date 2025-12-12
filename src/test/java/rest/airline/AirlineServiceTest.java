package rest.airline;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rest.aircraft.Aircraft;
import rest.city.City;
import rest.city.CityRepository;
import rest.city.CityService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AirlineServiceTest {
    @Mock
    AirlineRepository airlineRepository;

    @InjectMocks
    AirlineService airlineService;

    @Mock
    CityRepository cityRepository;

    @InjectMocks
    CityService cityService;


    @Test
    void testCreateAirline() {
        City city = new City("Montreal", "QC", 2000000);
        city.setId(1L);

        Airline airlineUnderTest = new Airline("Air Canada", "ACA", city);
        airlineUnderTest.setId(1L);

        Mockito.when(cityRepository.findById(1L)).thenReturn(Optional.of(city));
        Mockito.when(airlineRepository.save(airlineUnderTest)).thenReturn(airlineUnderTest);

        Airline returnedAirline = airlineService.createAirline(airlineUnderTest, 1L);

        assertEquals(airlineUnderTest, returnedAirline);
    }

    @Test
    void testGetAirlineById() {
        City city = new City("Montreal", "QC", 2000000);
        city.setId(1L);

        Airline airlineUnderTest = new Airline("Air Canada", "ACA", city);
        airlineUnderTest.setId(1L);

        Mockito.when(airlineRepository.findById(1L)).thenReturn(Optional.of(airlineUnderTest));

        Airline returnedAirline = airlineService.getAirlineById(1L);

        assertEquals(airlineUnderTest, returnedAirline);
    }

    @Test
    void testUpdateAirline() {
        City city = new City("Montreal", "QC", 2000000);
        city.setId(1L);

        Airline airlineUnderTest = new Airline("Air Canada", "ACA", city);
        airlineUnderTest.setId(1L);

        Mockito.when(airlineRepository.findById(1L)).thenReturn(Optional.of(airlineUnderTest));
        Mockito.when(cityRepository.findById(2L)).thenReturn(Optional.of(city));

        City updatedCity = new City("Calgary", "AB", 1500000);
        city.setId(2L);

        Airline updatedAirline = new Airline("Westjet", "WJA", updatedCity);
        updatedAirline.setId(1L);

        Airline returnedAirline = airlineService.updateAirline(1L, updatedAirline, 2L);

        assertEquals(updatedAirline.getCode(), returnedAirline.getCode());
    }
}