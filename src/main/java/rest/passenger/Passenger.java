package rest.passenger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import rest.city.City;
import rest.flight.Flight;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Passenger {
    @Id
    @SequenceGenerator(
            name = "passenger_sequence",
            sequenceName = "passenger_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "passenger_sequence"
    )
    private long id;

    private String firstName;
    private String lastName;
    private String phone;

    @ManyToOne
    private City city;

    // Bidirectional Many-to-Many with Flight
    // "mappedBy = passengers" means Flight is the owning side
    @ManyToMany(mappedBy = "passengers")
    @JsonIgnoreProperties("passengers")  // Prevents infinite JSON loop
    private List<Flight> flights = new ArrayList<>();

    // Constructors
    public Passenger() {
    }

    public Passenger(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}