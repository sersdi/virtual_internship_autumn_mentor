package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class    TravelCalculatePremiumServiceImplAIOneTest {

    private final TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

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
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        String firstName = "John";
        String lastName = "Peterson";
        Date dateFrom = new Date(1000000000000L);
        Date dateTo = new Date(1000000086400000L);

        request.setPersonFirstName(firstName);
        request.setPersonLastName(lastName);
        request.setAgreementDateFrom(dateFrom);
        request.setAgreementDateTo(dateTo);

        // Act - вызов метода
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        // Assert - проверка всех четырех свойств
        assertEquals(firstName, response.getPersonFirstName(), "Имя должно быть скопировано");
        assertEquals(lastName, response.getPersonLastName(), "Фамилия должна быть скопирована");
        assertEquals(dateFrom, response.getAgreementDateFrom(), "Дата начала должна быть скопирована");
        assertEquals(dateTo, response.getAgreementDateTo(), "Дата окончания должна быть скопирована");
    }
}
