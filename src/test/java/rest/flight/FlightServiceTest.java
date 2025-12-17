package rest.flight;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rest.aircraft.*;
import rest.airline.*;
import rest.airport.*;
import rest.city.City;
import rest.gate.*;
import rest.passenger.Passenger;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @Mock private FlightRepository flightRepository;
    @Mock private AircraftRepository aircraftRepository;
    @Mock private AirlineRepository airlineRepository;
    @Mock private AirportRepository airportRepository;
    @Mock private GateRepository gateRepository;

    @InjectMocks
    private FlightService flightService;

    private Flight buildFlight() {
        City city = new City("St. John's", "NL", 150000);
        city.setId(1L);

        Passenger passenger = new Passenger("John", "Doe", "7091234567");
        passenger.setId(1L);
        passenger.setCity(city);

        Airport airport = new Airport("YYT", "YYT", city);
        airport.setId(1L);

        Gate depGate = new Gate(1L, "A1", "Boarding");
        depGate.setAirport(airport);

        Gate arrGate = new Gate(2L, "B2", "Available");
        arrGate.setAirport(airport);

        Airline airline = new Airline("Air Canada", "ACA", city);
        airline.setId(1L);

        Aircraft aircraft = new Aircraft("Boeing", 150, airline);
        aircraft.setId(1L);

        Flight flight = new Flight(
                List.of(passenger),
                airport,
                airport,
                depGate,
                arrGate,
                aircraft,
                airline,
                "Departing",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        flight.setId(1L);

        return flight;
    }

    @Test
    void testGetFlightById() {
        Flight flight = buildFlight();
        Mockito.when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        Flight returned = flightService.getFlightById(1L);

        assertEquals(flight, returned);
        Mockito.verify(flightRepository).findById(1L);
    }

    @Test
    void testAddNewFlight() {
        Flight flight = buildFlight();

        Mockito.when(aircraftRepository.findById(1L)).thenReturn(Optional.of(flight.getAircraft()));
        Mockito.when(airlineRepository.findById(1L)).thenReturn(Optional.of(flight.getAirline()));
        Mockito.when(airportRepository.findById(1L)).thenReturn(Optional.of(flight.getDepartureAirport()));
        Mockito.when(gateRepository.findById(1L)).thenReturn(Optional.of(flight.getDepartureGate()));
        Mockito.when(gateRepository.findById(2L)).thenReturn(Optional.of(flight.getArrivalGate()));
        Mockito.when(flightRepository.save(flight)).thenReturn(flight);

        Flight returned = flightService.addNewFlight(
                flight,
                1L, // aircraft
                1L, // airline
                1L, // departure airport
                1L, // arrival airport
                1L, // departure gate
                2L  // arrival gate
        );

        assertEquals(flight, returned);
        Mockito.verify(flightRepository).save(flight);
    }

    @Test
    void testUpdateFlight() {
        Flight existing = buildFlight();

        Flight updated = buildFlight();
        updated.setStatus("On Time");

        Mockito.when(flightRepository.findById(1L)).thenReturn(Optional.of(existing));
        Mockito.when(flightRepository.save(existing)).thenReturn(existing);

        Flight returned = flightService.updateFlight(
                1L,
                updated,
                null,
                null,
                null,
                null,
                null,
                null
        );

        assertEquals("On Time", returned.getStatus());
        Mockito.verify(flightRepository).save(existing);
    }
}
