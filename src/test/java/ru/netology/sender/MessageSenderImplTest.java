package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderImplTest {

    @ParameterizedTest
    @MethodSource("setParameters")
    public void sendTest (String givenIP, String actualGreeting, Country givenCountry ) {

        LocalizationService localizationServiceMock = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceMock.locale(givenCountry))
                .thenReturn(actualGreeting);

        GeoServiceImpl geoServiceMock = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceMock.byIp(givenIP))
                .thenReturn(new Location(Mockito.anyString(), givenCountry, null, 0));

        Map<String, String> headers = new HashMap<String, String>();
        MessageSender testMessageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, givenIP);
        String expected = actualGreeting;
        String actual = testMessageSender.send(headers);

        Assertions.assertEquals(expected, actual);

    }

    public static Stream<Arguments> setParameters () {
        return Stream.of(Arguments.of("172", "Добро пожаловать", Country.RUSSIA),
                Arguments.of("96", "Welcome", Country.USA),
                Arguments.of("96", "Welcome", Country.GERMANY),
                Arguments.of("96", "Welcome", Country.BRAZIL));
    }

}
