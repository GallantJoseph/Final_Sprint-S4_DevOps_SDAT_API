package rest.flight;

import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends org.springframework.data.repository.CrudRepository<Flight, Long> {

}
