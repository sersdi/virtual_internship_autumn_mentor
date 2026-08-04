package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplAIOneTest {

    @Mock private  DateTimeService dateTimeService;

    @InjectMocks
    private  TravelCalculatePremiumServiceImpl service;;

    private  TravelCalculatePremiumRequest request;

    @BeforeEach
    public void setUp() {
        request = createRequestWithAllFields();
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(0L);
    }

    @Test
    public void testCalculatePremiumShouldSetAllProperties() {
        var response = service.calculatePremium(request);
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName(), "Имя должно быть скопировано");
        assertEquals(request.getPersonLastName(), response.getPersonLastName(), "Фамилия должна быть скопирована");
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom(), "Дата начала должна быть скопирована");
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo(), "Дата окончания должна быть скопирована");
    }

    private TravelCalculatePremiumRequest createRequestWithAllFields() {
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");
        request.setPersonLastName("Peterson");
        request.setAgreementDateFrom(new Date(2024,12,10));
        request.setAgreementDateTo(new Date(2024,12,10));
        return request;
    }
}
