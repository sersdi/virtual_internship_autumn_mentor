package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceImplAIEachFieldTest {
    @Mock private DateTimeService dateTimeService;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void setUp() {
        request = createRequestWithAllFields();
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(0L);
    }
    @Test
    public void testShouldSetPersonFirstName() {
        var response = service.calculatePremium(request);
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }
    @Test
    public void testShouldSetPersonLastName() {
        var response = service.calculatePremium(request);
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    public void testShouldSetAgreementDateFrom() {
        var response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }
    @Test
    public void testShouldSetAgreementDateTo() {
        var response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    private TravelCalculatePremiumRequest createRequestWithAllFields() {
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");
        request.setPersonLastName("Peterson");
        request.setAgreementDateFrom(new Date(2024,12,10));
        request.setAgreementDateTo(new Date(2024,12,10));
        return request;
    }
}
