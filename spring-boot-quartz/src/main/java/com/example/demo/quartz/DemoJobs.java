package com.example.demo.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@DisallowConcurrentExecution
public class DemoJobs implements Job {
    Logger logger = LoggerFactory.getLogger(DemoJobs.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("DemoJob Executed");
        logger.info("DemoJobs Executed");
    }

}
