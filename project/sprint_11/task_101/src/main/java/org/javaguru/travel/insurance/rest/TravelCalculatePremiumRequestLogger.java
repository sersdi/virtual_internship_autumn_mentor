package org.javaguru.travel.insurance.rest;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class TravelCalculatePremiumRequestLogger {

    void log(TravelCalculatePremiumRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            log.info("REQUEST: " + json);
        } catch (JsonProcessingException e) {
            log.error("Error to convert request to JSON", e);
        }
    }

}
