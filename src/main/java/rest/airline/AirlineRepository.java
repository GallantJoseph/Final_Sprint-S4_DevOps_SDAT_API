package rest.airline;

import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends org.springframework.data.repository.CrudRepository<Airline, Long>  {

}
