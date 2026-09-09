package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.ErrorCodeUtil;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonLastNameValidationTest {
    @Mock
    private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private PersonLastNameValidation validation;

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn(null);
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_8")).thenReturn("error description");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_8", errorOpt.get().getErrorCode());
        assertEquals("error description", errorOpt.get().getDescription());
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("");
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_8")).thenReturn("error description");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_8", errorOpt.get().getErrorCode());
        assertEquals("error description", errorOpt.get().getDescription());
    }

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIsPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("Pupkin");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isEmpty());
    }

}
