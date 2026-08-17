package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {

    @Mock private DateTimeService  dateTimeService;

    @InjectMocks private TravelPremiumUnderwriting premiumUnderwriting;

    @Test
    public void shouldReturnResponseWithCorrectAgreementPrice(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("17.08.2026"));
        when(request.getAgreementDateTo()).thenReturn(createDate("20.08.2026"));
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(),request.getAgreementDateTo())).thenReturn(3L);
        BigDecimal premium = premiumUnderwriting.calculatePremium(request);
        assertEquals(new BigDecimal(3), premium);

    }

    private Date createDate(String dateStr){
        try{
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e){
            throw new RuntimeException(e);
        }

    }

}
