package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelCalculatePremiumServiceImplAIEachFieldTest {

    private DateTimeService dateTimeService = new DateTimeService();
    private TravelCalculatePremiumServiceImpl service;

    @BeforeEach
    public void setUp() {
        dateTimeService = new DateTimeService();
        service = new TravelCalculatePremiumServiceImpl(dateTimeService);
    }
    /**
     * Тест проверяет свойство personFirstName
     */
    @Test
    public void testShouldSetPersonFirstName() {
        var request = createRequestWithAllFields();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("John", response.getPersonFirstName());
    }

    /**
     * Тест проверяет свойство personLastName
     */
    @Test
    public void testShouldSetPersonLastName() {
        var request = createRequestWithAllFields();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("Peterson", response.getPersonLastName());
    }

    /**
     * Тест проверяет свойство agreementDateFrom
     */
    @Test
    public void testShouldSetAgreementDateFrom() {
        var request = createRequestWithAllFields();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    /**
     * Тест проверяет свойство agreementDateTo
     */
    @Test
    public void testShouldSetAgreementDateTo() {
        var request = createRequestWithAllFields();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
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
