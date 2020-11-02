package com.cognizant.collector.alm.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class JobScheduler {
    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private ConfigurableApplicationContext context;

    @Value("${scheduler.enable}")
    private boolean isSchedulerEnabled;

    BatchEvents batchEvents = null;
    String[] beanNamesForType = null;

    @Scheduled(cron = "${scheduler.cron}")
    public void cronScheduler() {
        validCheckToProcess();
        log.info("*******************************************cron <Start>********************************************************************");
        batchEvents.beforeJob();
        log.info("********************************************cron <End>*******************************************************************");
    }

    private void validCheckToProcess() {
        if (!isSchedulerEnabled) System.exit(0);
    }

    @PostConstruct
    private void postConstructor() {
        try {
            batchEvents = appContext.getBean(BatchEvents.class);
        } catch (NoSuchBeanDefinitionException e) {
            log.info("Collector processed successfully, but scheduler terminating due to below reasons");
            log.error("No Such beans found: {}", e.getLocalizedMessage());
            log.info("Beans are not found so we are stopping the scheduler, Please implement beans and rerun if you want to run in scheduler manner");
        }
    }
}
