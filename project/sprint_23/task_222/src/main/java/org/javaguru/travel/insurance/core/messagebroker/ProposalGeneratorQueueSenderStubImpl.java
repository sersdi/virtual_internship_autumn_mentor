package org.javaguru.travel.insurance.core.messagebroker;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.springframework.amqp.AmqpException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile({"h2", "mysql-local", "mysql-container"})
class ProposalGeneratorQueueSenderStubImpl implements ProposalGeneratorQueueSender {

    @Override
    public void send(AgreementDTO agreement) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(agreement);
            log.info("PROPOSAL GENERATION message content: " + json);
        } catch (JsonProcessingException e) {
            log.error("Error to convert agreement to JSON", e);
        } catch (AmqpException e) {
            log.error("Error to sent proposal generation message", e);
        }
    }

}
