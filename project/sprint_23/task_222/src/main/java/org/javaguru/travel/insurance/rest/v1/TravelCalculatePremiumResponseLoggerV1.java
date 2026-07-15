package org.javaguru.travel.insurance.rest.v1;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class TravelCalculatePremiumResponseLoggerV1 {

    void log(TravelCalculatePremiumResponseV1 response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(response);
            log.info("RESPONSE: " + json);
        } catch (JsonProcessingException e) {
            log.error("Error to convert response to JSON", e);
        }
    }

}
