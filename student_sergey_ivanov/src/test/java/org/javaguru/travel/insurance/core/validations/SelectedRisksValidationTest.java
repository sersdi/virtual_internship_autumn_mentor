package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.List;
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
        when(request.getSelectedRisks()).thenReturn(null);
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("selectedRisks", errorOpt.get().getErrorCode());
        assertEquals("Must not be empty!", errorOpt.get().getDescription());
    }

    @Test
    public void shouldReturnErrorWhenSelectedRisksIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of());
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("selectedRisks", errorOpt.get().getErrorCode());
        assertEquals("Must not be empty!", errorOpt.get().getDescription());
    }

    @Test
    public void shouldNotReturnErrorWhenSelectedRisksIsNotEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isEmpty());
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
