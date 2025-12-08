package rest.passenger;

import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends org.springframework.data.repository.CrudRepository<Passenger, Long>  {

}
