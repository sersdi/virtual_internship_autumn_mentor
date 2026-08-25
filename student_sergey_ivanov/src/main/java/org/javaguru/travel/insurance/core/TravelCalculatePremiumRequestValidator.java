package org.javaguru.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumRequestValidator {

    private final DateTimeService dateTimeService;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(errors::add);  // добавил валидацию имени
        validatePersonLastName(request).ifPresent(errors::add);  // добавил валидацию  фамилии
        validateAgreementDateFrom(request).ifPresent(errors::add); // добавил валидацию даты начала поездки
        validateAgreementDateTo(request).ifPresent(errors::add);   // добавил валидацию даты окончания поездки
        validateDateFromLessThenDateTo(request).ifPresent(errors::add); // добавил валидацию на проверку DateFromLessThenDateTo
        validateDateFromAndDateToInPresent(request).ifPresent(errors::add); // валидация на проверку даты в настоящем времени
        return errors;
    }

    private Optional<ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(new ValidationError("personFirstName", "Must not be empty!"))
                : Optional.empty();
        }

    private Optional<ValidationError> validatePersonLastName(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateFrom(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationError("agreementDateFrom","Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request){
        return (request.getAgreementDateTo() == null)
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateFromLessThenDateTo(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        return (dateFrom != null && dateTo != null
                && (dateFrom.equals(dateTo) || dateFrom.after(dateTo)))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be less then agreementDateTo!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateFromAndDateToInPresent(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        Date dateNow = dateTimeService.getCurrentDateTime();
        return (dateFrom.before(dateNow) || dateTo.before(dateNow))
                ? Optional.of(new ValidationError("agreementDateFrom or agreementDateTo", "Date must be in present!"))
                : Optional.empty();
    }


}
