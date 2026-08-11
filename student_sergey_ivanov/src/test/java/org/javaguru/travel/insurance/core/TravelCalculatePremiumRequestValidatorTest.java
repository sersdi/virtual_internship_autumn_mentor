package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorTest {

    private TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("personLastName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("personLastName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnNotErrorWhenAllRequestsAreValid() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateFromIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateTo", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsEqualsDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.get(0).getField());
        assertEquals("Must be less then agreementDateTo!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsAfterDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("10.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.get(0).getField());
        assertEquals("Must be less then agreementDateTo!", errors.get(0).getMessage());
    }

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIsPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}