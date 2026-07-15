package org.javaguru.travel.insurance.jobs;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class AgreementXmlExporterJob {

    @Value( "${agreement.xml.exporter.job.enabled:false}" )
    private boolean jobEnabled;

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void doJob() {
        if (jobEnabled) {
            log.info("AgreementXmlExporterJob started");
            log.info("AgreementXmlExporterJob finished");
        }
    }

}
