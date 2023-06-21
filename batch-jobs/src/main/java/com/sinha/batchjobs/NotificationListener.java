package com.sinha.batchjobs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener implements JobExecutionListener {

    Logger logger = LoggerFactory.getLogger(JobExecutionListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("Batch job completed....");
    }
}
