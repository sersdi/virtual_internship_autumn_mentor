package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelCalculatePremiumServiceImplTest {

    private DateTimeService dateTimeService;
    private TravelCalculatePremiumServiceImpl service;

    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void setUp() {
        request = createRequestWithAllFields();
        dateTimeService = mock(DateTimeService.class);
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(0L);
        service = new TravelCalculatePremiumServiceImpl(dateTimeService);
    }

    @Test
    public void shouldPopulatePersonFirstName() {
        var response = service.calculatePremium(request);
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    public void shouldPopulatePersonLastName() {
        var response = service.calculatePremium(request);
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    public void shouldPopulateAgreementDateFrom() {
        var response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    public void shouldPopulateAgreementDateTo() {
        var response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    @Test
    public void shouldPopulateAgreementPrice() {
        var response = service.calculatePremium(request);
        assertNotNull(response.getAgreementPrice());
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