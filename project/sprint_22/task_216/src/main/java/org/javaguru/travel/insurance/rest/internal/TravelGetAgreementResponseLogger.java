package org.javaguru.travel.insurance.rest.internal;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.dto.internal.TravelGetAgreementResponse;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class TravelGetAgreementResponseLogger {

    void log(TravelGetAgreementResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(response);
            log.info("RESPONSE: " + json);
        } catch (JsonProcessingException e) {
            log.error("Error to convert response to JSON", e);
        }
    }

}
