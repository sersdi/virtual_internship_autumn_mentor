package org.javaguru.travel.insurance.rest.v1;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class TravelCalculatePremiumRequestLoggerV1 {

    void log(TravelCalculatePremiumRequestV1 request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            log.info("REQUEST: " + json);
        } catch (JsonProcessingException e) {
            log.error("Error to convert request to JSON", e);
        }
    }

}
