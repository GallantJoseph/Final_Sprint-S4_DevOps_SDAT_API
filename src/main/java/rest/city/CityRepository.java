package rest.city;

import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends org.springframework.data.repository.CrudRepository<City, Long>  {

}
