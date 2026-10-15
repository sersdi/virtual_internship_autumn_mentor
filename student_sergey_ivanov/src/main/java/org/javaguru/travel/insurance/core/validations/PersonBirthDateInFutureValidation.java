package org.javaguru.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class PersonBirthDateInFutureValidation extends TravelRequestValidationImpl{

    private final DateTimeUtil dateTimeUtil;
    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate (TravelCalculatePremiumRequestV1 request) {
        Date personBirthDate = request.getPersonBirthDate();
        Date dateNow = dateTimeUtil.getCurrentDateTime();
        return (personBirthDate != null && personBirthDate.after(dateNow))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_12"))
                : Optional.empty();
    }
}
