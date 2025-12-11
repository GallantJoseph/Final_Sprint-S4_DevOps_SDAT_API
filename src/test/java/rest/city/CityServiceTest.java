package rest.city;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {
    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @Test
    void testCreateCity() {
        City cityUnderTest = new City("St. John's", "NL", 150000);
        cityUnderTest.setId(1L);

        Mockito.when(cityRepository.save(cityUnderTest)).thenReturn(cityUnderTest);

        City returnedCity = cityService.createCity(cityUnderTest);

        Assertions.assertEquals(cityUnderTest, returnedCity);
    }

    @Test
    void testGetCityById() {
        City cityUnderTest = new City("St. John's", "NL", 150000);
        cityUnderTest.setId(1L);

        Mockito.when(cityRepository.findById(1L)).thenReturn(Optional.of(cityUnderTest));

        City returnedCity = cityService.getCityById(1L);

        Assertions.assertEquals(cityUnderTest, returnedCity);
    }

    @Test
    void testUpdateCity() {
        City cityUnderTest = new City("St. John's", "NL", 150000);
        cityUnderTest.setId(1L);

        City updatedCity = new City("Moncton", "NB", 80000);
        updatedCity.setId(1L);

        Mockito.when(cityRepository.findById(1L)).thenReturn(Optional.of(cityUnderTest));
        Mockito.when(cityService.updateCity(1L, updatedCity)).thenReturn(updatedCity);

        City returnedCity = cityService.updateCity(1L, updatedCity);

        Assertions.assertNotEquals(cityUnderTest, returnedCity);
    }
}