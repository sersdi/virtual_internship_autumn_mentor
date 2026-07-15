package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonFirstNameValidationTest {

    private PersonFirstNameValidation validation = new PersonFirstNameValidation();

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> errorOpt = validation.validatePersonFirstName(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("personFirstName", errorOpt.get().getField());
        assertEquals("Must not be empty!", errorOpt.get().getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        Optional<ValidationError> errorOpt = validation.validatePersonFirstName(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("personFirstName", errorOpt.get().getField());
        assertEquals("Must not be empty!", errorOpt.get().getMessage());
    }

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIsPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Vasja");
        Optional<ValidationError> errorOpt = validation.validatePersonFirstName(request);
        assertTrue(errorOpt.isEmpty());
    }

}