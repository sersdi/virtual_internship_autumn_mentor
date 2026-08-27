package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.core.validations.*;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TravelCalculatePremiumRequestValidator {

    private final AgreementDateFromAndDateToInPresentValidation agreementDateFromAndDateToInPresentValidation;
    private final AgreementDateFromLessThanDateToValidation agreementDateFromLessThanDateToValidation;
    private final AgreementDateFromValidation agreementDateFromValidation;
    private final AgreementDateToValidation agreementDateToValidation;
    private final PersonFirstNameValidation personFirstNameValidation;
    private final PersonLastNameValidation personLastNameValidation;

    private TravelCalculatePremiumRequestValidator(AgreementDateFromAndDateToInPresentValidation agreementDateFromAndDateToInPresentValidation,
                                                  AgreementDateFromLessThanDateToValidation agreementDateFromLessThanDateToValidation,
                                                  AgreementDateFromValidation agreementDateFromValidation,
                                                  AgreementDateToValidation agreementDateToValidation,
                                                  PersonFirstNameValidation personFirstNameValidation,
                                                  PersonLastNameValidation personLastNameValidation)
    {
        this.agreementDateFromAndDateToInPresentValidation = agreementDateFromAndDateToInPresentValidation;
        this.agreementDateFromLessThanDateToValidation = agreementDateFromLessThanDateToValidation;
        this.agreementDateFromValidation = agreementDateFromValidation;
        this.agreementDateToValidation = agreementDateToValidation;
        this.personFirstNameValidation = personFirstNameValidation;
        this.personLastNameValidation = personLastNameValidation;
    }

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        personFirstNameValidation.validatePersonFirstName(request).ifPresent(errors::add);  // добавил валидацию имени
        personLastNameValidation.validatePersonLastName(request).ifPresent(errors::add);  // добавил валидацию  фамилии
        agreementDateFromValidation.validateAgreementDateFrom(request).ifPresent(errors::add); // добавил валидацию даты начала поездки
        agreementDateToValidation.validateAgreementDateTo(request).ifPresent(errors::add);   // добавил валидацию даты окончания поездки
        agreementDateFromLessThanDateToValidation.validateDateFromLessThenDateTo(request).ifPresent(errors::add); // добавил валидацию на проверку DateFromLessThenDateTo
        agreementDateFromAndDateToInPresentValidation.validateDateFromAndDateToInPresent(request).ifPresent(errors::add); // валидация на проверку даты в настоящем времени
        return errors;
    }

}
