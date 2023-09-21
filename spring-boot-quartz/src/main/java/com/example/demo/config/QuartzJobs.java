package com.example.demo.config;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import com.example.demo.quartz.DemoJobs;

@Configuration
public class QuartzJobs {
    private static final String CRON_EVERY_MINUTE = "0 */1 * * * ?";

    @Bean(name = "demoJobStats")
    public JobDetailFactoryBean demoJobDetail() {
        return QuartzConfig.createJobDetail(DemoJobs.class, "contractAutomation");
    }

    @Bean(name = "demoJobTrigger")
    public CronTriggerFactoryBean triggerDemo(@Qualifier("demoJobStats") JobDetail jobDetail) {
        return QuartzConfig.createCronTrigger(jobDetail, CRON_EVERY_MINUTE, "demoJobTrigger");
    }

}
