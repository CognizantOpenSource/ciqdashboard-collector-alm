package com.cognizant.collector.alm.config;


import com.cognizant.collector.alm.client.AlmClient;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class GlobalConfiguration {

    @Value("${almServer.url}")
    private String almServerUrl;
    @Value("${almServer.username}")
    private String almServerUsername;
    @Value("${almServer.password}")
    private String almServerPassword;

    @Bean
    public AlmClient almClient() {

        return Feign.builder()
                .requestInterceptor(new BasicAuthRequestInterceptor(almServerUsername, almServerPassword))
                .contract(new SpringMvcContract())
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .client(new OkHttpClient())
                .options(new Request.Options(20000, TimeUnit.MILLISECONDS, 20000, TimeUnit.MILLISECONDS, true))
                .logger(new Slf4jLogger(AlmClient.class))
                .logLevel(Logger.Level.FULL)
                .target(AlmClient.class, almServerUrl);

    }
}