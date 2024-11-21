package io.github.internetms52.service;

import io.github.internetms52.db.repository.EmailInstanceRepository;
import io.micronaut.serde.ObjectMapper;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@Singleton
public class EmailInstanceSearchService {
    private static final Logger logger = LoggerFactory.getLogger(EmailInstanceSearchService.class);

    EmailInstanceRepository emailInstanceRepository;
    ObjectMapper objectMapper;

    public EmailInstanceSearchService(EmailInstanceRepository emailInstanceRepository, ObjectMapper objectMapper) {
        this.emailInstanceRepository = emailInstanceRepository;
        this.objectMapper = objectMapper;
    }

    public void execute() {
        emailInstanceRepository.getEmailIdList().flatMap(emailId -> {
            return emailInstanceRepository.findByEmailId(emailId);
        }).flatMap(record -> {
            try {
                return Mono.just(objectMapper.writeValueAsString(record));
            } catch (Exception e) {
                logger.error("object mapper:{}", e.getMessage());
            }
            return Mono.empty();
        }).flatMap(json -> {
            System.out.println(json);
            return Mono.empty();
        }).ignoreElements().subscribe();
    }
}
