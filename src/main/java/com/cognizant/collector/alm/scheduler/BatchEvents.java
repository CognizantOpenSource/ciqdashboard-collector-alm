package com.cognizant.collector.alm.scheduler;

public interface BatchEvents {
    void beforeJob();
    void beforeTask();
    void afterTask();
    void afterJob();
}
