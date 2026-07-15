package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
    @Test
    public void shouldPopulatePersonFirstName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    public void shouldPopulatePersonLastName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Peterson");
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    public void shouldPopulateAgreementDateFrom() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(new Date());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    public void shouldPopulateAgreementDateTo() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(new Date());
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

}