package rest.gate;

import jakarta.persistence.*;
import rest.airport.Airport;

@Entity
public class Gate {
    @Id
    @SequenceGenerator(name = "gate_sequence", sequenceName = "gate_sequence", allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.IDENTITY)

    private Long id;
    private String gateNumber;
    private String status; // like if its Available, Boarding or Closed.


    @ManyToOne
    private Airport airport;

    // Constructors

    public Gate(){}

    public Gate(Long id, String gateNumber, String status) {
        this.id = id;
        this.gateNumber = gateNumber;
        this.status = status;
    }

// Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }
}