package org.javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.DateTimeService;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementDateToInPresentValidation implements TravelRequestValidation {

    private final DateTimeService dateTimeService;
    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> execute (TravelCalculatePremiumRequest request) {
        Date dateTo = request.getAgreementDateTo();
        Date dateNow = dateTimeService.getCurrentDateTime();
        return (dateTo != null && dateTo.before(dateNow))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_3"))
                : Optional.empty();
    }
}
