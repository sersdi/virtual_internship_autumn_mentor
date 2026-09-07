package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SelectedRisksValidationTest {
    private final SelectedRisksValidation validation = new SelectedRisksValidation();

    @Test
    public void shouldReturnErrorWhenSelectedRisksIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelected_risks()).thenReturn(null);
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("selected_risks", errorOpt.get().getField());
        assertEquals("Must not be empty!", errorOpt.get().getMessage());
    }

//    @Test
//    public void shouldReturnErrorWhenSelectedRisksIsEmpty() {
//        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
//        when(request.getSelected_risks()).thenReturn("");
//        Optional<ValidationError> errorOpt = validation.execute(request);
//        assertTrue(errorOpt.isPresent());
//        assertEquals("selectedRisks", errorOpt.get().getField());
//        assertEquals("Must not be empty!", errorOpt.get().getMessage());
//    }


}
