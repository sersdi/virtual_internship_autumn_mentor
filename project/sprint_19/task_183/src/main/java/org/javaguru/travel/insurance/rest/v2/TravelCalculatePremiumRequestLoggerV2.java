package org.javaguru.travel.insurance.rest.v2;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class TravelCalculatePremiumRequestLoggerV2 {

    void log(TravelCalculatePremiumRequestV2 request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            log.info("REQUEST: " + json);
        } catch (JsonProcessingException e) {
            log.error("Error to convert request to JSON", e);
        }
    }

}
