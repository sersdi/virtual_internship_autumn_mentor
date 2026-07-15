package org.javaguru.travel.insurance.core.jobs;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class SimpleJob {

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void doJob() {
        log.info("SimpleJob started");
        log.info("SimpleJob finished");
    }

}
