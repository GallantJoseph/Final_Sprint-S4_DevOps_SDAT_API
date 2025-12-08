package rest.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
@CrossOrigin
public class CityController {
    
    @Autowired
    private CityService cityService;

    @PostMapping
    public City createCity(@RequestBody City city) {
        return cityService.createCity(city);
    }

    @GetMapping("/{id}")
    public City getCityById(@PathVariable long id) {
        return cityService.getCityById(id);
    }

    @GetMapping
    public Iterable<City> getAllCities(){
        return cityService.getAllCities();
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable long id, @RequestBody City city) {
        return ResponseEntity.ok(cityService.updateCity(id, city));
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable long id) {
        cityService.deleteCity(id);
    }
}
