package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceImplTest {

    @Test
    public void byIpTest () {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expected = new Location("Moscow", Country.RUSSIA, null, 0);
        Location actual = geoService.byIp("172.0.");
        Assertions.assertEquals(expected.getCountry(), actual.getCountry());
    }

    @Test
    public void byCoordinatesTest () {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Executable ex = () -> geoService.byCoordinates(Mockito.anyDouble(), Mockito.anyDouble());
        Assertions.assertThrowsExactly(RuntimeException.class, (Executable) ex);
    }

}
