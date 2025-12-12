package rest.gate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rest.airport.Airport;
import rest.airport.AirportRepository;
import rest.city.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class GateServiceTest {

    @Mock
    GateRepository gateRepository;

    @Mock
    AirportRepository airportRepository;

    @InjectMocks
    GateService gateService;


    @Test
    void createGate() {
        City city = new City("St. John's", "NL", 150000);
        city.setId(1L);

        Airport airport = new Airport("St. John's International Airport", "YYT", city);
        airport.setId(1L);

        Gate gateUnderTest = new Gate(1L, "1", "On-Time");
        gateUnderTest.setAirport(airport);

        Mockito.when(airportRepository.findById(1L)).thenReturn(Optional.of(airport));
        Mockito.when(gateRepository.save(gateUnderTest)).thenReturn(gateUnderTest);

        Gate returnedGate = gateService.createGate(gateUnderTest, 1L);

        Assertions.assertEquals(gateUnderTest, returnedGate);
        Mockito.verify(airportRepository).findById(1L);
        Mockito.verify(gateRepository).save(gateUnderTest);
    }

    @Test
    void getGateById() {
        City city = new City("St. John's", "NL", 150000);
        city.setId(1L);

        Airport airport = new Airport("St. John's International Airport", "YYT", city);
        airport.setId(1L);

        Gate gateUnderTest = new Gate(1L, "1", "On-Time");
        gateUnderTest.setAirport(airport);

        Mockito.when(gateRepository.findById(1L)).thenReturn(Optional.of(gateUnderTest));

        Gate returnedGate = gateService.getGateById(1L);

        Assertions.assertEquals(gateUnderTest, returnedGate);
        Mockito.verify(gateRepository).findById(1L);
    }

    @Test
    void getGatesByAirportId() {
        City city = new City("St. John's", "NL", 150000);
        city.setId(1L);

        Airport airport = new Airport("St. John's International Airport", "YYT", city);
        airport.setId(1L);

        Gate gateUnderTest1 = new Gate(1L, "1", "On-Time");
        gateUnderTest1.setAirport(airport);

        Gate gateUnderTest2 = new Gate(2L, "2", "Delayed");
        gateUnderTest2.setAirport(airport);

        List<Gate> gateListUnderTest = new ArrayList<>();
        gateListUnderTest.add(gateUnderTest1);
        gateListUnderTest.add(gateUnderTest2);

        Mockito.when(gateRepository.findByAirportId(1L)).thenReturn(gateListUnderTest);

        List<Gate> returnedGateList = gateService.getGatesByAirportId(1L);

        Assertions.assertEquals(gateListUnderTest, returnedGateList);

        Mockito.verify(gateRepository).findByAirportId(1L);
    }
}