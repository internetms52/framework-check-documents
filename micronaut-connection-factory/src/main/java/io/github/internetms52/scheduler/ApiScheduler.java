package io.github.internetms52.scheduler;

import io.github.internetms52.service.EmailInstanceSearchService;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ApiScheduler {
    private final Logger logger = LoggerFactory.getLogger(ApiScheduler.class);

    EmailInstanceSearchService emailInstanceSearchService;

    public ApiScheduler(EmailInstanceSearchService emailInstanceSearchService) {
        this.emailInstanceSearchService = emailInstanceSearchService;
    }

    @Scheduled(fixedDelay = "1m")
    public void run() {
        emailInstanceSearchService.execute();
    }
}
