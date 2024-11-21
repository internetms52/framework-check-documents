package io.github.internetms52.db.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface EmailInstanceRepository extends ReactorPageableRepository<EmailInstanceRecord, Long> {
    @Query("select * from email_instance LIMIT :limit")
    Flux<EmailInstanceRecord> findMailInstance(int limit);

    @Query("select * from email_instance")
    Flux<EmailInstanceRecord> findAllMailInstances();

    @Query("select * from email_instance where email_id=:emailId")
    Mono<EmailInstanceRecord> findByEmailId(String emailId);

    @Query("select * from email_instance where email_id in (:idList)")
    Flux<EmailInstanceRecord> findByEmailIdList(List<String> idList);

    @Query("select * from email_instance where category=:category and email_id in (:idList)")
    Flux<EmailInstanceRecord> findByEmailIdListAndCategory(String category, List<String> idList);

    @Query("SELECT count(email_id) from public.email_instance where category=:category")
    Mono<Integer> getInstanceCountByCategory(String category);

    @Query("SELECT email_id from public.email_instance where category=:category")
    Flux<String> getEmailIdListByCategory(String category);

    @Query("SELECT email_id from public.email_instance")
    Flux<String> getEmailIdList();
}
