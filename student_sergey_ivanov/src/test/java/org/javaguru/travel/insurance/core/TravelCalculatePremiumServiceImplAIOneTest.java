package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelCalculatePremiumServiceImplAIOneTest {

    private DateTimeService dateTimeService = new DateTimeService();
    private TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

    @BeforeEach
    public void setUp() {
        dateTimeService = new DateTimeService();
        service = new TravelCalculatePremiumServiceImpl(dateTimeService);
    }
    /**
     * Один комплексный тест, который проверяет все свойства класса одновременно:
     * - personFirstName
     * - personLastName
     * - agreementDateFrom
     * - agreementDateTo
     */
    @Test
    public void testCalculatePremiumShouldSetAllProperties() {
        // Arrange - подготовка данных
        var request = createRequestWithAllFields();
        // Act - вызов метода
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        // Assert - проверка всех четырех свойств
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName(), "Имя должно быть скопировано");
        assertEquals(request.getPersonLastName(), response.getPersonLastName(), "Фамилия должна быть скопирована");
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom(), "Дата начала должна быть скопирована");
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo(), "Дата окончания должна быть скопирована");
    }

    private TravelCalculatePremiumRequest createRequestWithAllFields() {
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");
        request.setPersonLastName("Peterson");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
        return request;
    }
}
