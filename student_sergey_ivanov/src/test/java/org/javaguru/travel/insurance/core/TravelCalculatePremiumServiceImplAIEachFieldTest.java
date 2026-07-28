package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelCalculatePremiumServiceImplAIEachFieldTest {

    private final TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

    /**
     * Тест проверяет свойство personFirstName
     */
    @Test
    public void testShouldSetPersonFirstName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals("John", response.getPersonFirstName());
    }

    /**
     * Тест проверяет свойство personLastName
     */
    @Test
    public void testShouldSetPersonLastName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Doe");

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals("Doe", response.getPersonLastName());
    }

    /**
     * Тест проверяет свойство agreementDateFrom
     */
    @Test
    public void testShouldSetAgreementDateFrom() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        Date startDate = new Date();
        request.setAgreementDateFrom(startDate);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(startDate, response.getAgreementDateFrom());
    }

    /**
     * Тест проверяет свойство agreementDateTo
     */
    @Test
    public void testShouldSetAgreementDateTo() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        Date endDate = new Date();
        request.setAgreementDateTo(endDate);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(endDate, response.getAgreementDateTo());
    }
}
