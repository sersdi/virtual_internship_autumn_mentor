package org.javaguru.doc.generator.core.messagebroker;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

@Component
@Slf4j
public class ProposalGenerationQueueListener {

    @Value( "${proposals.directory.path}" )
    private String proposalsDirectoryPath;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PROPOSAL_GENERATION)
    public void receiveMessage(String message) throws IOException {
        log.info(message);

        Path path = Path.of(proposalsDirectoryPath + "/proposal.txt");

        try {
            Files.write(path, Collections.singleton(message), StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
