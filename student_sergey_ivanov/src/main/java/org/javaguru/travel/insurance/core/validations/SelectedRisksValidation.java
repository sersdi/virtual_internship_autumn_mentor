package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;

import java.util.Optional;

public class SelectedRisksValidation implements TravelRequestValidation{

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getSelected_risks() == null || request.getSelected_risks().isEmpty())
                ? Optional.of(new ValidationError("selected_risks", "Must not be empty!"))
                : Optional.empty();
    }


}
