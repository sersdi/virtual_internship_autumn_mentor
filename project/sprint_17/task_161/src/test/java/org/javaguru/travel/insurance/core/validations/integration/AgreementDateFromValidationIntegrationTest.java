package org.javaguru.travel.insurance.core.validations.integration;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreement;
import static org.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AgreementDateFromValidationIntegrationTest {

    @Autowired private TravelAgreementValidator validator;

    @Test
    public void shouldReturnErrorWhenDateFromIsNull() {
        AgreementDTO agreement = createAgreement()
                .withDateFrom(null)
                .withDateTo(createDate("01.01.2030"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPerson(createPersonDTO()
                        .withFirstName("Vasja")
                        .withLastName("Pupkin")
                        .withBirthDate(createDate("01.01.2000"))
                        .withMedicalRiskLimitLevel("LEVEL_10000")
                ).build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(1, errors.size());
        assertEquals("ERROR_CODE_2", errors.get(0).getErrorCode());
        assertEquals("Field agreementDateFrom must not be empty!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsInThePast() {
        AgreementDTO agreement = createAgreement()
                .withDateFrom(createDate("01.01.2020"))
                .withDateTo(createDate("01.01.2030"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPerson(createPersonDTO()
                        .withFirstName("Vasja")
                        .withLastName("Pupkin")
                        .withBirthDate(createDate("01.01.2000"))
                        .withMedicalRiskLimitLevel("LEVEL_10000")
                ).build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(1, errors.size());
        assertEquals("ERROR_CODE_1", errors.get(0).getErrorCode());
        assertEquals("Field agreementDateFrom must be in the future!", errors.get(0).getDescription());
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
