package org.javaguru.doc.generator.core.messagebroker;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class ProposalGenerationQueueListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PROPOSAL_GENERATION)
    public void receiveMessage(String message) throws IOException {
        log.info(message);
    }

}
