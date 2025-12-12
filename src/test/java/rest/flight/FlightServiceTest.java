package rest.flight;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rest.aircraft.Aircraft;
import rest.aircraft.AircraftRepository;
import rest.airline.Airline;
import rest.airport.Airport;
import rest.city.City;
import rest.gate.Gate;
import rest.passenger.Passenger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AircraftRepository aircraftRepository;

    @InjectMocks
    private FlightService flightService;

    @Test
    void testGetFlightById() {
        City city = new City("St. John's", "NL", 150000);
        city.setId(1L);

        List<Passenger> passengers = new ArrayList<>();
        Passenger passenger = new Passenger("John", "Doe", "+1 (709) 867-5309");
        passenger.setId(1L);
        passenger.setCity(city);
        passengers.add(passenger);

        Airport airport = new Airport("St. John's International Airport", "YYT", city);
        airport.setId(1L);

        Gate gate = new Gate(1L, "1", "Boarding");
        gate.setAirport(airport);

        Airline airline = new Airline("Air Canada", "ACA", city);
        airline.setId(1L);

        Aircraft aircraft = new Aircraft("Boeing", 150, airline);
        aircraft.setId(1L);


        Flight flightUnderTest = new Flight(passengers, airport, airport, gate, aircraft, airline, "Departing", LocalDateTime.now(), LocalDateTime.now());
        flightUnderTest.setId(1L);
        flightUnderTest.setPassengers(passengers);
        flightUnderTest.setDepartureAirport(airport);
        flightUnderTest.setArrivalAirport(airport);
        flightUnderTest.setGate(gate);
        flightUnderTest.setAircraft(aircraft);
        flightUnderTest.setAirline(airline);

        Mockito.when(flightRepository.findById(1L)).thenReturn(Optional.of(flightUnderTest));

        Flight returnedFlight = flightService.getFlightById(1L);

        assertEquals(flightUnderTest, returnedFlight);
        Mockito.verify(flightRepository).findById(1L);
    }

    @Test
    void testAddNewFlight() {
        City city = new City("St. John's", "NL", 150000);
        city.setId(1L);

        List<Passenger> passengers = new ArrayList<>();
        Passenger passenger = new Passenger("John", "Doe", "+1 (709) 867-5309");
        passenger.setId(1L);
        passenger.setCity(city);
        passengers.add(passenger);

        Airport airport = new Airport("St. John's International Airport", "YYT", city);
        airport.setId(1L);

        Gate gate = new Gate(1L, "1", "Boarding");
        gate.setAirport(airport);

        Airline airline = new Airline("Air Canada", "ACA", city);
        airline.setId(1L);

        Aircraft aircraft = new Aircraft("Boeing", 150, airline);
        aircraft.setId(1L);

        Flight flightUnderTest = new Flight(passengers, airport, airport, gate, aircraft, airline, "Departing", LocalDateTime.now(), LocalDateTime.now());
        flightUnderTest.setId(1L);
        flightUnderTest.setPassengers(passengers);
        flightUnderTest.setDepartureAirport(airport);
        flightUnderTest.setArrivalAirport(airport);
        flightUnderTest.setGate(gate);
        flightUnderTest.setAircraft(aircraft);
        flightUnderTest.setAirline(airline);

        Mockito.when(aircraftRepository.findById(1L)).thenReturn(Optional.of(aircraft));
        Mockito.when(flightRepository.save(flightUnderTest)).thenReturn(flightUnderTest);

        Flight returnedFlight = flightService.addNewFlight(flightUnderTest, 1L);

        assertEquals(flightUnderTest, returnedFlight);

        Mockito.verify(aircraftRepository).findById(1L);
        Mockito.verify(flightRepository).save(flightUnderTest);
    }

    @Test
    void testUpdateFlight() {
        City city = new City("St. John's", "NL", 150000);
        city.setId(1L);

        List<Passenger> passengers = new ArrayList<>();
        Passenger passenger = new Passenger("John", "Doe", "+1 (709) 867-5309");
        passenger.setId(1L);
        passenger.setCity(city);
        passengers.add(passenger);

        Airport airport = new Airport("St. John's International Airport", "YYT", city);
        airport.setId(1L);

        Gate gate = new Gate(1L, "1", "Boarding");
        gate.setAirport(airport);

        Airline airline = new Airline("Air Canada", "ACA", city);
        airline.setId(1L);

        Aircraft aircraft = new Aircraft("Boeing", 150, airline);
        aircraft.setId(1L);

        Flight flightUnderTest = new Flight(passengers, airport, airport, gate, aircraft, airline, "Departing", LocalDateTime.now(), LocalDateTime.now());
        flightUnderTest.setId(1L);
        flightUnderTest.setPassengers(passengers);
        flightUnderTest.setDepartureAirport(airport);
        flightUnderTest.setArrivalAirport(airport);
        flightUnderTest.setGate(gate);
        flightUnderTest.setAircraft(aircraft);
        flightUnderTest.setAirline(airline);

        Mockito.when(aircraftRepository.findById(1L)).thenReturn(Optional.of(aircraft));
        Mockito.when(flightRepository.findById(1L)).thenReturn(Optional.of(flightUnderTest));
        Mockito.when(flightRepository.save(flightUnderTest)).thenReturn(flightUnderTest);

        Flight updatedFlight = new Flight(passengers, airport, airport, gate, aircraft, airline, "On Time", LocalDateTime.now(), LocalDateTime.now());
        flightUnderTest.setId(1L);
        flightUnderTest.setPassengers(passengers);
        flightUnderTest.setDepartureAirport(airport);
        flightUnderTest.setArrivalAirport(airport);
        flightUnderTest.setGate(gate);
        flightUnderTest.setAircraft(aircraft);
        flightUnderTest.setAirline(airline);

        Flight returnedFlight = flightService.updateFlight(1L, updatedFlight, 1L);

        assertEquals(updatedFlight.getStatus(), returnedFlight.getStatus());

        Mockito.verify(flightRepository).findById(1L);
        Mockito.verify(flightRepository).save(flightUnderTest);
    }
}
