package com.cognizant.collector.alm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by 784420 on 7/10/2019 1:55 PM
 */
@Component
public class StaticContextInitializer {
    @Autowired
    Environment environment;

    @PostConstruct
    public void init() {
        JsonUtil.init(environment);
    }

}
