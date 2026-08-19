package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock private TravelCalculatePremiumRequestValidator requestValidator;
    @Mock private TravelPremiumUnderwriting travelPremiumUnderwriting;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @Test
    public void shouldPopulatePersonFirstName() {
        var request = createRequestWithAllFields();
        when(travelPremiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(0L));
        when(requestValidator.validate(request)).thenReturn(List.of());
        var response = service.calculatePremium(request);
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    public void shouldPopulatePersonLastName() {
        var request = createRequestWithAllFields();
        when(travelPremiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(0L));
        when(requestValidator.validate(request)).thenReturn(List.of());
        var response = service.calculatePremium(request);
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    public void shouldPopulateAgreementDateFrom() {
        var request = createRequestWithAllFields();
        when(travelPremiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(0L));;
        when(requestValidator.validate(request)).thenReturn(List.of());
        var response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    public void shouldPopulateAgreementDateTo() {
        var request = createRequestWithAllFields();
        when(travelPremiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(0L));
        when(requestValidator.validate(request)).thenReturn(List.of());
        var response = service.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    @Test
    public void shouldPopulateAgreementPrice() {
        var request = createRequestWithAllFields();
        when(travelPremiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(0L));
        when(requestValidator.validate(request)).thenReturn(List.of());
        var response = service.calculatePremium(request);
        assertNotNull(response.getAgreementPrice());
    }

    @Test
    public void shouldReturnResponseWithErrors() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithCorrectErrorCount() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = service.calculatePremium(request);
        assertEquals(1, response.getErrors().size());
    }

    @Test
    public void shouldReturnResponseWithCorrectError() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = service.calculatePremium(request);
        assertEquals("field", response.getErrors().get(0).getField());
        assertEquals("message", response.getErrors().get(0).getMessage());
        assertNull(response.getPersonFirstName());
    }

    @Test
    public void allFieldsMustBeEmptyWhenResponseContainsError() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = service.calculatePremium(request);
        assertNull(response.getPersonFirstName());
        assertNull(response.getPersonLastName());
        assertNull(response.getAgreementDateFrom());
        assertNull(response.getAgreementDateTo());
        assertNull(response.getAgreementPrice());
    }

    @Test
    public void shouldNOtBeInteractionWithDateTimeServiceWhenResponseContainsError() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));
        var response = service.calculatePremium(request);
        verifyNoInteractions(travelPremiumUnderwriting);
    }

    private TravelCalculatePremiumRequest createRequestWithAllFields() {
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");
        request.setPersonLastName("Peterson");
        request.setAgreementDateFrom(new Date(2025,12,10));
        request.setAgreementDateTo(new Date(2025,12,10));
        return request;
    }

}