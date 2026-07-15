package org.javaguru.doc.generator.core.messagebroker;
import lombok.extern.slf4j.Slf4j;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.doc.generator.core.api.dto.AgreementDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ProposalGenerationQueueListener {

    private final JsonStringToAgreementDtoConverter agreementDtoConverter;
    private final ProposalGenerator proposalGenerator;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PROPOSAL_GENERATION)
    public void receiveMessage(String message) throws IOException {
        try {
            log.info(message);
            AgreementDTO agreementDTO = agreementDtoConverter.convert(message);
            proposalGenerator.generateProposalAndStoreToFile(agreementDTO);
        } catch (Exception e) {
            log.error("FAIL to process message: ", e);
        }
    }

}
