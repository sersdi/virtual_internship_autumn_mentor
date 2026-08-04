package org.javaguru.travel.insurance.rest;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponse {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
    private BigDecimal agreementPrice;

    public BigDecimal getAgreementPrice(){
        return agreementPrice;
    }
    public void setAgreementPrice(BigDecimal agreementPrice){
        this.agreementPrice = agreementPrice;
    }
}
