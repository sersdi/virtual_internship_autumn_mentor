package org.javaguru.travel.insurance.jobs;
import lombok.extern.slf4j.Slf4j;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreResult;
import org.javaguru.travel.insurance.core.services.TravelGetNotExportedAgreementUuidsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AgreementXmlExporterJob {

    @Value( "${agreement.xml.exporter.job.enabled:false}" )
    private boolean jobEnabled;

    @Value( "${agreement.xml.exporter.job.thread.count}" )
    private Integer threadCount;

    private final TravelGetNotExportedAgreementUuidsService notExportedAgreementUuidsService;
    private final AgreementXmlExporter agreementXmlExporter;

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void doJob() {
        if (jobEnabled) {
            executeJob();
        }
    }

    private void executeJob() {
        log.info("AgreementXmlExporterJob started");
        List<String> notExportedYetAgreementUuids = getNotExportedYetAgreementUuids();
        exportAgreements(notExportedYetAgreementUuids);
        log.info("AgreementXmlExporterJob finished");
    }

    private List<String> getNotExportedYetAgreementUuids() {
        TravelGetNotExportedAgreementUuidsCoreResult result = notExportedAgreementUuidsService.getAgreementUuids(
                new TravelGetNotExportedAgreementUuidsCoreCommand()
        );
        return result.getAgreementUuids();
    }

    private void exportAgreements(List<String> agreementUuids) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        Collection<Future<?>> futures = new LinkedList<>();
        agreementUuids.forEach(uuid -> futures.add(executor.submit(() -> agreementXmlExporter.exportAgreement(uuid))));
        waitUntilAllTasksWillBeExecuted(futures);
        executor.shutdownNow();
    }

    private static void waitUntilAllTasksWillBeExecuted(Collection<Future<?>> futures) {
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                log.info("AgreementXmlExporterJob exception", e);
            } catch (ExecutionException e) {
                log.info("AgreementXmlExporterJob exception", e);
            }
        }
    }

}
