package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {

    public void localeTest() {
        LocalizationService myLocService = new LocalizationServiceImpl();
        String expectedMessage = "Welcome";
        String currentMessage = myLocService.locale(Country.BRAZIL);
        Assertions.assertEquals(expectedMessage, currentMessage);
    }

}
