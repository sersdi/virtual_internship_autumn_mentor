package org.javaguru.blacklist.rest;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.blacklist.dto.BlackListedPersonCheckRequest;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class BlackListedPersonRequestLogger {

    void log(BlackListedPersonCheckRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            log.info("REQUEST: " + json);
        } catch (JsonProcessingException e) {
            log.error("Error to convert request to JSON", e);
        }
    }

}
