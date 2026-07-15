package org.javaguru.doc.generator.core.messagebroker.proposalack;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.doc.generator.core.api.dto.AgreementDTO;
import org.javaguru.doc.generator.core.api.dto.ProposalGenerationAcknowledgment;
import org.javaguru.doc.generator.core.messagebroker.RabbitMQConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ProposalGenerationAckQueueSenderImpl implements ProposalGenerationAckQueueSender {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(AgreementDTO agreement, String proposalFilePath) {
        ProposalGenerationAcknowledgment ackMessage = new ProposalGenerationAcknowledgment();
        ackMessage.setAgreementUuid(agreement.getUuid());
        ackMessage.setProposalFilePath(proposalFilePath);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(ackMessage);
            log.info("PROPOSAL GENERATION ACK message content: " + json);
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_PROPOSAL_GENERATION_ACK, json);
        } catch (JsonProcessingException e) {
            log.error("Error to convert proposal generation ack to JSON", e);
        } catch (AmqpException e) {
            log.error("Error to sent proposal generation ack message", e);
        }
    }

}
