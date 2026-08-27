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
public class AgreementDateFromAndDateToInPresentValidation {

    private final DateTimeService dateTimeService;

    public Optional<ValidationError> validateDateFromAndDateToInPresent(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        Date dateNow = dateTimeService.getCurrentDateTime();
        return ((dateFrom != null && dateTo != null) && (dateFrom.before(dateNow) || dateTo.before(dateNow)))
                ? Optional.of(new ValidationError("agreementDateFrom or agreementDateTo", "Date must be in present!"))
                : Optional.empty();
    }

}
