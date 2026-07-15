package org.javaguru.travel.insurance.core.validations.agreement;

import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmptyMedicalRiskLimitLevelValidationTest {

    private ValidationErrorFactory errorFactory;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        request = new TravelCalculatePremiumRequest();
        errorFactory = mock(ValidationErrorFactory.class);
    }

    @Test
    void shouldReturnValidationErrorWhenMedicalRiskLimitLevelEnabledAndNullOrBlank() {
        request.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        request.setMedicalRiskLimitLevel(null);
        ValidationError expectedError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_13")).thenReturn(expectedError);

        var validation = new EmptyMedicalRiskLimitLevelValidation(true, errorFactory);

        Optional<ValidationError> result = validation.validate(request);

        assertTrue(result.isPresent());
        assertEquals(expectedError, result.get());
    }

    @Test
    void shouldNotReturnValidationErrorWhenMedicalRiskLimitLevelEnabledAndIsNotBlank() {
        request.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        request.setMedicalRiskLimitLevel("LEVEL_10000");
        var validation = new EmptyMedicalRiskLimitLevelValidation(true, errorFactory);
        Optional<ValidationError> result = validation.validate(request);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotReturnValidationErrorWhenMedicalRiskLimitLevelNotEnabledAndIsBlank() {
        request.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        request.setMedicalRiskLimitLevel("");
        var validation = new EmptyMedicalRiskLimitLevelValidation(false, errorFactory);
        Optional<ValidationError> result = validation.validate(request);
        assertTrue(result.isEmpty());
    }

}